package com.wallxu.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.base.Result;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;
import com.wallxu.seckill.rpc.api.MiaoshaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/miaoshaUser")
public class MiaoshaUserController {

	@Reference
	private MiaoshaUserService miaoshaUserService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<TbMiaoshaUser> findAll(){
		return miaoshaUserService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	@ResponseBody
	public PageResult findPage(int page, int rows){
		return miaoshaUserService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param miaoshaUser
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestBody TbMiaoshaUser miaoshaUser){
		try {
			miaoshaUserService.add(miaoshaUser);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param miaoshaUser
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody TbMiaoshaUser miaoshaUser){
		try {
			miaoshaUserService.update(miaoshaUser);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	@ResponseBody
	public TbMiaoshaUser findOne(Long id){
		return miaoshaUserService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long [] ids){
		try {
			miaoshaUserService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param miaoshaUser
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public PageResult search(@RequestBody TbMiaoshaUser miaoshaUser, int page, int rows  ){
		return miaoshaUserService.findPage(miaoshaUser, page, rows);		
	}
	
}
