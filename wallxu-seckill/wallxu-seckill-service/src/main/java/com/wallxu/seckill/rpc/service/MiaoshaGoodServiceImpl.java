package com.wallxu.seckill.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbGoodsVo;
import com.wallxu.seckill.dao.domain.TbMiaoshaGood;
import com.wallxu.seckill.dao.domain.TbMiaoshaGoodExample;
import com.wallxu.seckill.dao.mapper.TbMiaoshaGoodMapper;
import com.wallxu.seckill.rpc.api.MiaoshaGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class MiaoshaGoodServiceImpl implements MiaoshaGoodService {

	@Autowired
	private TbMiaoshaGoodMapper miaoshaGoodMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbMiaoshaGood> findAll() {
		return miaoshaGoodMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbMiaoshaGood> page = (Page<TbMiaoshaGood>) miaoshaGoodMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbMiaoshaGood miaoshaGood) {
		miaoshaGoodMapper.insert(miaoshaGood);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbMiaoshaGood miaoshaGood){
		miaoshaGoodMapper.updateByPrimaryKey(miaoshaGood);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbMiaoshaGood findOne(Long id){
		return miaoshaGoodMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			miaoshaGoodMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbMiaoshaGood miaoshaGood, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbMiaoshaGoodExample example=new TbMiaoshaGoodExample();
		TbMiaoshaGoodExample.Criteria criteria = example.createCriteria();
		
		if(miaoshaGood!=null){			
				
		}
		
		Page<TbMiaoshaGood> page= (Page<TbMiaoshaGood>)miaoshaGoodMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	//查询所有秒杀商品
	@Override
	public List<TbGoodsVo> getMiaoshaGoods() {
		return miaoshaGoodMapper.getMiaoshaGoods();
	}

	//查询秒杀商品详情
	@Override
	public TbGoodsVo getMiaoshaGoodsDetail(Long goodsId) {
		//查询详细信息
		TbGoodsVo tbGoodsVo = miaoshaGoodMapper.getMiaoshaGoodsDetail(goodsId);
		long remainseconds = -1; //>0 秒杀未开始，=0秒杀中 <0秒杀结束
		int miaoshaStatus = 2;  //0秒杀未开始 1秒杀中 2秒杀结束


		Date nowDate = new Date();
		Date startDate = tbGoodsVo.getStartDate();
		Date endDate = tbGoodsVo.getEndDate();

		if (nowDate.before(startDate)) {
			//未开始
			miaoshaStatus = 0;
			//秒杀开始剩余时间
			remainseconds = (startDate.getTime() - nowDate.getTime()) / 1000;

		}else if(nowDate.before(endDate)){
			//秒杀进行中
			remainseconds = 0;
			miaoshaStatus = 1;

		}else {
			//秒杀结束
			miaoshaStatus = 2;
			remainseconds = -1;
		}
		tbGoodsVo.setRemainSeconds(remainseconds);
		tbGoodsVo.setMiaoshaStatus(miaoshaStatus);
		return tbGoodsVo;
	}

	@Override
	public int reduceNum(long goodsId) {
		return miaoshaGoodMapper.reduceNum(goodsId);
	}

}
