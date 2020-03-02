package com.wallxu.seckill.dao.domain;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/19.
 */
public class TbGoodsVo extends TbGoods {
    private double miaoshaPrice;
    private int stockCount;
    private long remainSeconds; //秒杀剩余时间
    private int miaoshaStatus;  //秒杀状态
    private Date startDate; //秒杀开始时间
    private Date endDate;   //秒杀结束时间


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(long remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public double getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(double miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
}
