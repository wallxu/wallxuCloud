package com.wallxu.seckill.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbOrderInfo;
import com.wallxu.seckill.dao.domain.TbOrderInfoExample;
import com.wallxu.seckill.dao.mapper.TbOrderInfoMapper;
import com.wallxu.seckill.rpc.api.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private TbOrderInfoMapper orderInfoMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbOrderInfo> findAll() {
		return orderInfoMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbOrderInfo> page=   (Page<TbOrderInfo>) orderInfoMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbOrderInfo orderInfo) {
		orderInfoMapper.insert(orderInfo);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbOrderInfo orderInfo){
		orderInfoMapper.updateByPrimaryKey(orderInfo);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbOrderInfo findOne(Long id){
		return orderInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			orderInfoMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbOrderInfo orderInfo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbOrderInfoExample example=new TbOrderInfoExample();
		TbOrderInfoExample.Criteria criteria = example.createCriteria();
		
		if(orderInfo!=null){			
						if(orderInfo.getGoodsName()!=null && orderInfo.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+orderInfo.getGoodsName()+"%");
			}
	
		}
		
		Page<TbOrderInfo> page= (Page<TbOrderInfo>)orderInfoMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	//生成商品订单
	@Override
	public int createOrder(long userId, long goodsId, long orderId) {
		return orderInfoMapper.createOrder(userId, goodsId, orderId);
	}

}
