package com.wallxu.seckill.rpc.api;

import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbMiaoshaOrder;

import java.util.List;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface MiaoshaOrderService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbMiaoshaOrder> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbMiaoshaOrder miaoshaOrder);
	
	
	/**
	 * 修改
	 */
	public void update(TbMiaoshaOrder miaoshaOrder);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbMiaoshaOrder findOne(Long id);
	
	
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
	public PageResult findPage(TbMiaoshaOrder miaoshaOrder, int pageNum,int pageSize);

	//秒杀
    void doMiaosha(long userId, long goodsId);

    long miaoshaResult(long userId, long goodsId);

	void MqMiaosha(TbMiaoshaOrder tbMiaoshaOrder);
}
