package com.wallxu.seckill.rpc.api;

import com.wallxu.common.base.PageResult;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;

import java.util.List;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface MiaoshaUserService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbMiaoshaUser> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbMiaoshaUser miaoshaUser);
	
	
	/**
	 * 修改
	 */
	public void update(TbMiaoshaUser miaoshaUser);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbMiaoshaUser findOne(Long id);
	
	
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
	public PageResult findPage(TbMiaoshaUser miaoshaUser, int pageNum,int pageSize);

	//登录
	public boolean login(TbMiaoshaUser tbMiaoshaUser);
	
}
