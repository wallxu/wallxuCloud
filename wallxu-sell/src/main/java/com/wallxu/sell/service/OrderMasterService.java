package com.wallxu.sell.service;

import com.wallxu.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderMasterService {
    public String save(OrderDTO orderDTO);

    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    public OrderDTO findOrderDetailByOrderId(String orderId);

    //取消订单
    public boolean cancel(OrderDTO orderDTO, String openId);

    //完成订单
    public boolean finish(OrderDTO orderDTO, String openId);

    //支付订单
    public boolean paid(OrderDTO orderDTO, String openId);
}
