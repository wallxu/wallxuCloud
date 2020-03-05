package com.wallxu.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.base.Result;
import com.wallxu.seckill.dao.domain.TbOrderInfo;
import com.wallxu.seckill.rpc.api.OrderInfoService;
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
@RequestMapping("/orderInfo")
public class OrderInfoController {

	@Reference
	private OrderInfoService orderInfoService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<TbOrderInfo> findAll(){			
		return orderInfoService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	@ResponseBody
	public PageResult findPage(int page, int rows){
		return orderInfoService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestBody TbOrderInfo orderInfo){
		try {
			orderInfoService.add(orderInfo);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody TbOrderInfo orderInfo){
		try {
			orderInfoService.update(orderInfo);
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
	public TbOrderInfo findOne(Long id){
		return orderInfoService.findOne(id);		
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
			orderInfoService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param orderInfo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public PageResult search(@RequestBody TbOrderInfo orderInfo, int page, int rows  ){
		return orderInfoService.findPage(orderInfo, page, rows);		
	}
	
}
