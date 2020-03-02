package com.wallxu.seckill.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbGoods;
import com.wallxu.seckill.dao.domain.TbGoodsExample;
import com.wallxu.seckill.dao.mapper.TbGoodsMapper;
import com.wallxu.seckill.rpc.api.GoodsService;
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
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper tbGoodsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbGoods> findAll() {
		return tbGoodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGoods> page=   (Page<TbGoods>) tbGoodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbGoods goods) {
		tbGoodsMapper.insert(goods);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbGoods goods){
		tbGoodsMapper.updateByPrimaryKey(goods);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbGoods findOne(Long id){
		return tbGoodsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			tbGoodsMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsExample example=new TbGoodsExample();
		TbGoodsExample.Criteria criteria = example.createCriteria();
		
		if(goods!=null){			
						if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getGoodsTitle()!=null && goods.getGoodsTitle().length()>0){
				criteria.andGoodsTitleLike("%"+goods.getGoodsTitle()+"%");
			}
			if(goods.getGoodsImg()!=null && goods.getGoodsImg().length()>0){
				criteria.andGoodsImgLike("%"+goods.getGoodsImg()+"%");
			}
	
		}
		
		Page<TbGoods> page= (Page<TbGoods>)tbGoodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public int reduceNum(long goodsId) {

		return tbGoodsMapper.reduceNum(goodsId);
	}

}
