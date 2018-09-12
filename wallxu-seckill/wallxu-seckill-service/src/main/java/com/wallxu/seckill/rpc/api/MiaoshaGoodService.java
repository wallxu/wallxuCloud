package com.wallxu.seckill.rpc.api;

import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbGoodsVo;
import com.wallxu.seckill.dao.domain.TbMiaoshaGood;

import java.util.List;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface MiaoshaGoodService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbMiaoshaGood> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbMiaoshaGood miaoshaGood);
	
	
	/**
	 * 修改
	 */
	public void update(TbMiaoshaGood miaoshaGood);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbMiaoshaGood findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbMiaoshaGood miaoshaGood, int pageNum,int pageSize);

	//查询所有秒杀商品
	public List<TbGoodsVo> getMiaoshaGoods();

	public TbGoodsVo getMiaoshaGoodsDetail(Long goodsId);

	int reduceNum(long goodsId);
}
