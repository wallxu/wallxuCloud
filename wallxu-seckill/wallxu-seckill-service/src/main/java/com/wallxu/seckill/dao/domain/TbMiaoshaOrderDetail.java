package com.wallxu.seckill.dao.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/7.
 */
public class TbMiaoshaOrderDetail implements Serializable{

    TbOrderInfo orderInfo;
    TbGoods goods;

    public TbOrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(TbOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }
}
