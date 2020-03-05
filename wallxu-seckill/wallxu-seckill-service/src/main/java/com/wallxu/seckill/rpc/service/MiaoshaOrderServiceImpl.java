package com.wallxu.seckill.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.constant.CommonConstant;
import com.wallxu.common.constant.RedisKey;
import com.wallxu.common.utils.RedisUtil;
import com.wallxu.common.utils.SnowflakeIdWorker;
import com.wallxu.seckill.dao.domain.TbMiaoshaOrder;
import com.wallxu.seckill.dao.domain.TbMiaoshaOrderExample;
import com.wallxu.seckill.dao.domain.TbOrderInfo;
import com.wallxu.seckill.dao.mapper.TbMiaoshaOrderMapper;
import com.wallxu.seckill.mq.MyMessageProducer;
import com.wallxu.seckill.rpc.api.GoodsService;
import com.wallxu.seckill.rpc.api.MiaoshaGoodService;
import com.wallxu.seckill.rpc.api.MiaoshaOrderService;
import com.wallxu.seckill.rpc.api.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Transactional
@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {

	private static final Logger log = LoggerFactory.getLogger(MiaoshaOrderService.class);

	//内存里记录，商品有没有卖超
	private static Map<Long, Boolean> goodsIsOver = new HashMap<Long, Boolean>();

	@Autowired
	private TbMiaoshaOrderMapper miaoshaOrderMapper;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private MiaoshaGoodService miaoshaGoodService;

	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private MyMessageProducer messageProducer;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbMiaoshaOrder> findAll() {
		return miaoshaOrderMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbMiaoshaOrder> page=   (Page<TbMiaoshaOrder>) miaoshaOrderMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbMiaoshaOrder miaoshaOrder) {
		miaoshaOrderMapper.insert(miaoshaOrder);
	}


	/**
	 * 修改
	 */
	@Override
	public void update(TbMiaoshaOrder miaoshaOrder){
		miaoshaOrderMapper.updateByPrimaryKey(miaoshaOrder);
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbMiaoshaOrder findOne(Long id){
		return miaoshaOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			miaoshaOrderMapper.deleteByPrimaryKey(id);
		}
	}


		@Override
	public PageResult findPage(TbMiaoshaOrder miaoshaOrder, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbMiaoshaOrderExample example=new TbMiaoshaOrderExample();
		TbMiaoshaOrderExample.Criteria criteria = example.createCriteria();

		if(miaoshaOrder!=null){

		}

		Page<TbMiaoshaOrder> page= (Page<TbMiaoshaOrder>)miaoshaOrderMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void doMiaosha(long userId, long goodsId) {

		//先判断内存里是否有数量
		Boolean over = goodsIsOver.get(goodsId);
		if (over == null) {
			//内存里没数，先设true有库存
			goodsIsOver.put(goodsId, true);
		} else if (over == false) {
			log.error("商品秒杀完了");
			return ;
		}

		//内存有库存，继续处理
		//先预减库存
		Integer stock = Integer.valueOf(RedisUtil.get(RedisKey.GOODS_STOCK_COUNT + "_" + goodsId));

		if (stock < 1) {
			//没有库存了，写入内存
			goodsIsOver.put(goodsId, false);
			return ;
		}

		//判断用户是否秒杀过
		TbMiaoshaOrderExample tbMiaoshaOrderExample = new TbMiaoshaOrderExample();
		TbMiaoshaOrderExample.Criteria criteria = tbMiaoshaOrderExample.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		criteria.andUserIdEqualTo(userId);

		List<TbMiaoshaOrder> tbMiaoshaOrders = miaoshaOrderMapper.selectByExample(tbMiaoshaOrderExample);
		if (tbMiaoshaOrders != null && tbMiaoshaOrders.size() > 0) {
			log.error("此用户已秒杀过");
			return ;
		}


		//redis减库存,库存减1
		RedisUtil.decr(RedisKey.GOODS_STOCK_COUNT + "_" + goodsId);

		TbMiaoshaOrder tbMiaoshaOrder = new TbMiaoshaOrder();
		tbMiaoshaOrder.setGoodsId(goodsId);
		tbMiaoshaOrder.setUserId(userId);
		//mq异步处理下单请求
		//秒杀中
		RedisUtil.set(RedisKey.MIAOSHA_PRIFIX + "_" + userId + "_" + goodsId, CommonConstant.MIAOSHA_ING);
		messageProducer.sendMsg(tbMiaoshaOrder);
		//异步下单成功

		/*
		//判断用户是否秒杀过
		TbMiaoshaOrderExample tbMiaoshaOrderExample = new TbMiaoshaOrderExample();
		TbMiaoshaOrderExample.Criteria criteria = tbMiaoshaOrderExample.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		criteria.andUserIdEqualTo(userId);

		List<TbMiaoshaOrder> tbMiaoshaOrders = miaoshaOrderMapper.selectByExample(tbMiaoshaOrderExample);
		if(tbMiaoshaOrders != null && tbMiaoshaOrders.size() > 0){
			log.error("此用户已秒杀过");
			return 0;
		}


		//商品减库存
		int update = goodsService.reduceNum(goodsId);

		if(update < 1) {
			log.error("商品减库存失败");
			return 0;
		}

		//秒杀减库存
		update = 0;
		update = miaoshaGoodService.reduceNum(goodsId);
		if(update < 1) {
			log.error("秒杀商品减库存失败");
			return 0;
		}

		TbOrderInfo tbOrderInfo = new TbOrderInfo();
		tbOrderInfo.setUserId(userId);	 //用户编号
		tbOrderInfo.setGoodsId(goodsId); //商品编号
		tbOrderInfo.setDeliveryAddId(1l);

		long orderId = new SnowflakeIdWorker(2,2).nextId();

		//下商品订单
		int insert = orderInfoService.createOrder(userId, goodsId, orderId);

		if(insert < 0) {
			log.error("生成商品订单失败");
			return 0;
		}

		TbMiaoshaOrder tbMiaoshaOrder = new TbMiaoshaOrder();
		tbMiaoshaOrder.setUserId(userId); //用户
		tbMiaoshaOrder.setGoodsId(goodsId); //商品编号
		tbMiaoshaOrder.setOrderId(orderId); //订单编号
		//秒杀商品订单
		insert = 0;
		insert = miaoshaOrderMapper.insert(tbMiaoshaOrder);

		if(insert < 1) {
			log.error("插入秒杀订单表失败");
			return 0;
		}
		return orderId;*/
	}



	@Override
	public long miaoshaResult(long userId, long goodsId) {

		long orderId = -1;

		//-1秒杀失败
		String res = RedisUtil.get(RedisKey.MIAOSHA_PRIFIX+ "_" +userId + "_" +goodsId);
		if("-1".equals(res)){
			//秒杀失败了
		}else if("0".equals(res)){
			//秒杀中
			orderId = 0;
		}else if("1".equals(res)){
			//秒杀成功了，返回订单编号
			TbMiaoshaOrderExample tbMiaoshaOrderExample = new TbMiaoshaOrderExample();
			TbMiaoshaOrderExample.Criteria criteria = tbMiaoshaOrderExample.createCriteria();
			criteria.andGoodsIdEqualTo(goodsId);
			criteria.andUserIdEqualTo(userId);

			List<TbMiaoshaOrder> tbMiaoshaOrders = miaoshaOrderMapper.selectByExample(tbMiaoshaOrderExample);
			orderId = tbMiaoshaOrders.get(0).getOrderId();
		}

		return orderId;
	}


	@Override
	public void MqMiaosha(TbMiaoshaOrder tbMiaoshaOrder) {

		long goodsId = tbMiaoshaOrder.getGoodsId();
		long userId = tbMiaoshaOrder.getUserId();
		boolean rs = true;

		//判断用户是否秒杀过
		TbMiaoshaOrderExample tbMiaoshaOrderExample = new TbMiaoshaOrderExample();
		TbMiaoshaOrderExample.Criteria criteria = tbMiaoshaOrderExample.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		criteria.andUserIdEqualTo(userId);

		List<TbMiaoshaOrder> tbMiaoshaOrders = miaoshaOrderMapper.selectByExample(tbMiaoshaOrderExample);
		if(tbMiaoshaOrders != null && tbMiaoshaOrders.size() > 0){
			rs = false;
			log.error("此用户已秒杀过");
		}


		//商品减库存
		int update = goodsService.reduceNum(goodsId);

		if(update < 1) {
			rs = false;
			log.error("商品减库存失败");
		}

		//秒杀减库存
		update = 0;
		update = miaoshaGoodService.reduceNum(goodsId);
		if(update < 1) {
			rs = false;
			log.error("秒杀商品减库存失败");
		}

		TbOrderInfo tbOrderInfo = new TbOrderInfo();
		tbOrderInfo.setUserId(userId);	 //用户编号
		tbOrderInfo.setGoodsId(goodsId); //商品编号
		tbOrderInfo.setDeliveryAddId(1l);

		long orderId = new SnowflakeIdWorker(2,2).nextId();

		//下商品订单
		int insert = orderInfoService.createOrder(userId, goodsId, orderId);

		if(insert < 0) {
			log.error("生成商品订单失败");
			rs = false;
		}

		tbMiaoshaOrder.setOrderId(orderId); //订单编号
		//秒杀商品订单
		insert = 0;
		insert = miaoshaOrderMapper.insert(tbMiaoshaOrder);

		if(insert < 1) {
			log.error("插入秒杀订单表失败");
			rs = false;
		}
		if(rs){
			//秒杀成功
			RedisUtil.set(RedisKey.MIAOSHA_PRIFIX+ "_" +userId + "_" +goodsId, CommonConstant.MIAOSHA_SUCESS);
		}else {
			//秒杀失败
			//库存加回去
			RedisUtil.incr(RedisKey.GOODS_STOCK_COUNT + "_" + goodsId);
			RedisUtil.set(RedisKey.MIAOSHA_PRIFIX+ "_" +userId + "_" +goodsId, CommonConstant.MIAOSHA_FAIL);
		}
	}

}
