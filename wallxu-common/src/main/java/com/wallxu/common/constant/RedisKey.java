package com.wallxu.common.constant;

/**
 * Created by Administrator on 2018/8/1.
 */
public class RedisKey {
    public static final String GOODS_LIST = "goods_list"; //商品列表
    public static final String GOODS_DETAIL = "goods_detail"; //商品详情
    public static final int expireTime = 60 * 60 * 24; //商品缓存时间

    public static final String GOODS_STOCK_COUNT = "goods_stock_count"; //商品库存
    public static final String MIAOSHA_PRIFIX = "do_miaosha"; //秒杀状态


}
