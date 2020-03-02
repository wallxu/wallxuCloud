package com.wallxu.sell.service.impl;

import com.wallxu.sell.bean.OrderDetail;
import com.wallxu.sell.dto.OrderDTO;
import com.wallxu.sell.service.OrderMasterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;
    private final String buyerOperId = "wallxu@2012";

    @Test
    public void save() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北京");
        orderDTO.setBuyerName("达萨罗");
        orderDTO.setBuyerPhone("15845688789");
        orderDTO.setBuyerOpenid(buyerOperId);

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1535697504931869567");
        orderDetail.setProductQuantity(2);

        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        orderMasterService.save(orderDTO);

    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> list = orderMasterService.findList(buyerOperId, pageRequest);

        Assert.assertNotNull(list);
    }

    @Test
    public void findOrderDetailByOrderId() {
        OrderDTO orderDetailByOrderId = orderMasterService.findOrderDetailByOrderId("1536564867267213264");
        Assert.assertNotNull(orderDetailByOrderId);
    }

    @Test
    public void cancel() {
    }
}