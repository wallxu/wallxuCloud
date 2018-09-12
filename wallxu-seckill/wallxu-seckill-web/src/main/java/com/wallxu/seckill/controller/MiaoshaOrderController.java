package com.wallxu.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.base.ResponseBean;
import com.wallxu.common.base.Result;
import com.wallxu.common.constant.ErrorCode;
import com.wallxu.seckill.annotation.UserInfo;
import com.wallxu.seckill.dao.domain.*;
import com.wallxu.seckill.rpc.api.GoodsService;
import com.wallxu.seckill.rpc.api.MiaoshaOrderService;
import com.wallxu.seckill.rpc.api.OrderInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/miaoshaOrder")
public class MiaoshaOrderController {

	@Reference
	private MiaoshaOrderService miaoshaOrderService;

	@Reference
	private GoodsService goodsService;

	@Reference
	private OrderInfoService orderInfoService;


	//秒杀
	@RequestMapping(value = "/do_miaosha", method= RequestMethod.POST)
	@ResponseBody
	public ResponseBean doMiaosha(@UserInfo TbMiaoshaUser tbMiaoshaUser,
								  @RequestParam(value = "goodsId") long goodsId){

		long userId = Long.valueOf(tbMiaoshaUser.getMobile());

		miaoshaOrderService.doMiaosha(userId, goodsId);
		return new ResponseBean(ErrorCode.SUCCESS);
	}


	//秒杀结果查询
	@RequestMapping("/result")
	@ResponseBody
	public ResponseBean miaoshaResult(@UserInfo TbMiaoshaUser tbMiaoshaUser,
								  @RequestParam(value = "goodsId") long goodsId){

		long userId = Long.valueOf(tbMiaoshaUser.getMobile());

		//查询秒杀成功后的，秒杀单号
		long orderId = miaoshaOrderService.miaoshaResult(userId, goodsId);
		return new ResponseBean((int)orderId, "");
	}

	//秒杀订单详情
	@RequestMapping("/detail")
	@ResponseBody
	public ResponseBean detail(
							@RequestParam(value = "orderId") long orderId){

//		long userId = Long.valueOf(tbMiaoshaUser.getMobile());

		TbMiaoshaOrderDetail tbMiaoshaOrderDetail = new TbMiaoshaOrderDetail();
		//秒杀成功，
		//查询出出来，秒杀订单
		TbOrderInfo orderInfo = orderInfoService.findOne(orderId);
		System.out.println(orderInfo);
		TbGoods goods = goodsService.findOne(orderInfo.getGoodsId());
		System.out.println(goods);
		tbMiaoshaOrderDetail.setGoods(goods);
		tbMiaoshaOrderDetail.setOrderInfo(orderInfo);
		List data = new ArrayList();
		data.add(tbMiaoshaOrderDetail);
		return new ResponseBean(ErrorCode.SUCCESS, data);

	}



//	@RequestMapping("/do_miaosha")
//	public String doMiaosha(@UserInfo TbMiaoshaUser tbMiaoshaUser,
//							@RequestParam(value = "goodsId") long goodsId, Model model){
//
//		long userId = Long.valueOf(tbMiaoshaUser.getMobile());
//
//		long orderId = miaoshaOrderService.doMiaosha(userId, goodsId);
//
//		if(orderId > 1){
//			//秒杀成功，
//			//查询出出来，秒杀订单
//			TbOrderInfo orderInfo = orderInfoService.findOne(orderId);
//			TbGoods goods = goodsService.findOne(goodsId);
//			model.addAttribute("orderInfo", orderInfo);
//			model.addAttribute("goods", goods);
//			return "order_detail";
//
//		}else{
//			//秒杀失败
//			model.addAttribute("errmsg", "秒杀失败");
//			return "/miaosha_fail";
//		}
//	}

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<TbMiaoshaOrder> findAll(){
		return miaoshaOrderService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	@ResponseBody
	public PageResult findPage(int page, int rows){
		return miaoshaOrderService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param miaoshaOrder
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestBody TbMiaoshaOrder miaoshaOrder){
		try {
			miaoshaOrderService.add(miaoshaOrder);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param miaoshaOrder
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody TbMiaoshaOrder miaoshaOrder){
		try {
			miaoshaOrderService.update(miaoshaOrder);
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
	public TbMiaoshaOrder findOne(Long id){
		return miaoshaOrderService.findOne(id);		
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
			miaoshaOrderService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param miaoshaOrder
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public PageResult search(@RequestBody TbMiaoshaOrder miaoshaOrder, int page, int rows  ){
		return miaoshaOrderService.findPage(miaoshaOrder, page, rows);		
	}
	
}
