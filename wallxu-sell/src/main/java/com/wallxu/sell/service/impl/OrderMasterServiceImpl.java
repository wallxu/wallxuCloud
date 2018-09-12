package com.wallxu.sell.service.impl;

import com.wallxu.sell.bean.OrderDetail;
import com.wallxu.sell.bean.OrderMaster;
import com.wallxu.sell.bean.ProductInfo;
import com.wallxu.sell.converter.OrderMaster2OrderDTOConverter;
import com.wallxu.sell.dao.OrderDetailRepository;
import com.wallxu.sell.dao.OrderMasterRepository;
import com.wallxu.sell.dto.CartDTO;
import com.wallxu.sell.dto.OrderDTO;
import com.wallxu.sell.enums.OrderStatusEnum;
import com.wallxu.sell.enums.PayStatusEnum;
import com.wallxu.sell.enums.ResultEnum;
import com.wallxu.sell.exception.SellException;
import com.wallxu.sell.service.OrderMasterService;
import com.wallxu.sell.service.ProductService;
import com.wallxu.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //创建订单
    @Override
    @Transactional
    public String save(OrderDTO orderDTO) {

        //订单
        OrderMaster orderMaster = new OrderMaster();
        String orderId = KeyUtil.genUniqueKey();

        //总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //bean拷贝
        BeanUtils.copyProperties(orderDTO, orderMaster);


        //订单详情
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //根据商品编号查询商品信息
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            if (productInfo == null){
                //商品不存在呢
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }

            //更新OrderDetail 字段数据
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductIcon(productInfo.getProductIcon());

            //计算总价格
            orderAmount = productInfo.getProductPrice().multiply(
                    new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //保存订单详情
            orderDetailRepository.save(orderDetail);
        }

        //订单编号
        orderMaster.setOrderId(orderId);
        //订单总金额
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //保存订单
        orderMasterRepository.save(orderMaster);


        //减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                e ->new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderId;
    }

    //查询所有订单
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }


    //查询订单详情
    @Override
    public OrderDTO findOrderDetailByOrderId(String orderId) {
        OrderDTO orderDTO  = new OrderDTO();

        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
        BeanUtils.copyProperties(orderMaster, orderDTO);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    //取消订单
    @Override
    @Transactional
    public boolean cancel(OrderDTO orderDTO, String openId) {
        if (!orderDTO.getBuyerOpenid().equals(openId)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR.getMessage());
        }

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);

        if(orderMasterRepository.updateOrderSatus(orderDTO.getOrderId(), OrderStatusEnum.CANCEL.getCode())){
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL.getMessage());
        }


        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY.getMessage());
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付, 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO;
        }

        return true;
    }

    //完成订单
    @Transactional
    @Override
    public boolean finish(OrderDTO orderDTO, String openId) {
        if (!orderDTO.getBuyerOpenid().equals(openId)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR.getMessage());
        }

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);

        if(orderMasterRepository.updateOrderSatus(orderDTO.getOrderId(), OrderStatusEnum.FINISHED.getCode())){
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    @Override
    @Transactional
    public boolean paid(OrderDTO orderDTO, String openId) {
        if (!orderDTO.getBuyerOpenid().equals(openId)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR.getMessage());
        }


        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR.getMessage());
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR.getMessage());
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL.getMessage());
        }

        return true;
    }
}
