package com.wallxu.sell.controller;

import com.wallxu.sell.bean.OrderForm;
import com.wallxu.sell.converter.OrderForm2OrderDTOConverter;
import com.wallxu.sell.dto.OrderDTO;
import com.wallxu.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/seller/order")
public class OrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    //创建订单
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(OrderForm orderForm,
                         HttpServletRequest httpRequest,
                         Model model){

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        String orderId = orderMasterService.save(orderDTO);

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        if (orderId != null && orderId.trim().length() > 0){
            return "/common/success";
        }
        return "/common/error";
    }


    //订单列表
    //查询商品列表
    @RequestMapping(value = "/list")
    public String list(Model model,
                       String buyerOpenid,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size){

        PageRequest pageRequest = PageRequest.of(page -1, size);

        buyerOpenid = "wallxu@2012";
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(buyerOpenid, pageRequest);
        model.addAttribute("orderDTOPage", orderDTOPage);
        model.addAttribute("currentPage", page == null? 1:page);
        model.addAttribute("size", size);
        return "/order/list";
    }


    //查询订单详情
    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(value = "orderId", required = false) String orderId, Model model,
                         @RequestParam(value = "openId", required = false) String openId){
        if (orderId != null && orderId.trim().length() > 0){
            OrderDTO orderDTO = orderMasterService.findOrderDetailByOrderId(orderId);
            model.addAttribute("orderDTO", orderDTO);
        }
        return "/order/detail";
    }


    //取消订单
    @RequestMapping(value = "/cancel")
    public String cancel(@RequestParam(value = "orderId", required = false) String orderId, HttpServletRequest httpRequest, Model model,
                         @RequestParam(value = "openId", required = false) String openId){

        model.addAttribute("url", httpRequest.getHeader("Referer"));

        openId = "wallxu@2012";
        OrderDTO orderDTO = orderMasterService.findOrderDetailByOrderId(orderId);
        boolean b = orderMasterService.cancel(orderDTO, openId);
        if (b){
            return "/common/success";
        }
        return "/common/error";
    }

    //完成订单
    @RequestMapping(value = "/finish")
    public String finish(@RequestParam(value = "orderId", required = false) String orderId, HttpServletRequest httpRequest, Model model,
                         @RequestParam(value = "openId", required = false) String openId){

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        openId = "wallxu@2012";
        OrderDTO orderDTO = orderMasterService.findOrderDetailByOrderId(orderId);
        boolean b = orderMasterService.finish(orderDTO, openId);
        if (b){
            return "/common/success";
        }
        return "/common/error";
    }


}
