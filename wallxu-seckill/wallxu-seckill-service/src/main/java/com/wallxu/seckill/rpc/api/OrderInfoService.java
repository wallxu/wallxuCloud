package com.wallxu.seckill.rpc.api;

import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbOrderInfo;

import java.util.List;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderInfoService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbOrderInfo> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbOrderInfo orderInfo);
	
	
	/**
	 * 修改
	 */
	public void update(TbOrderInfo orderInfo);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbOrderInfo findOne(Long id);
	
	
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
	public PageResult findPage(TbOrderInfo orderInfo, int pageNum,int pageSize);

    int createOrder(long userId, long goodsId, long orderId);
}
