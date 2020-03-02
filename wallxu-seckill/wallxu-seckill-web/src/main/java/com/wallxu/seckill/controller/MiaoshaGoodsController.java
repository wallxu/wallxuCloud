package com.wallxu.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.base.ResponseBean;
import com.wallxu.common.base.Result;
import com.wallxu.common.constant.ErrorCode;
import com.wallxu.common.constant.RedisKey;
import com.wallxu.common.utils.RedisUtil;
import com.wallxu.seckill.annotation.UserInfo;
import com.wallxu.seckill.dao.domain.TbGoodsVo;
import com.wallxu.seckill.dao.domain.TbMiaoshaGood;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;
import com.wallxu.seckill.rpc.api.MiaoshaGoodService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
/**
 * controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/miaoshaGoods")
//实现InitializingBean，加载完bean后初始化数据
public class MiaoshaGoodsController implements InitializingBean {

	@Reference
	private MiaoshaGoodService miaoshaGoodService;

	/**秒杀商品详情
	 */
	@RequestMapping("/to_detail/{goodsId}")
	@ResponseBody
	public ResponseBean toDetail(@PathVariable(value = "goodsId") Long goodsId){
		TbGoodsVo tbGoodsVo = miaoshaGoodService.getMiaoshaGoodsDetail(goodsId);

		List data = new ArrayList();
		data.add(tbGoodsVo);
		return new ResponseBean<TbGoodsVo>(ErrorCode.SUCCESS, data);
	}



	////手动渲染模板================版本一============start=======================================
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;

	/**秒杀商品列表
	 */
	@RequestMapping(value = "/to_list", produces="text/html")
	@ResponseBody
	public String toList(@UserInfo TbMiaoshaUser tbMiaoshaUser, Model model, HttpServletRequest request,
						 HttpServletResponse response){
		String goodsListHtml = RedisUtil.get(RedisKey.GOODS_LIST);
		if (goodsListHtml != null && goodsListHtml.trim().length() > 0) {
			 //缓存中有数据
			return goodsListHtml;
		}

		//缓存中没有，从数据库取
		List<TbGoodsVo> goodsList = miaoshaGoodService.getMiaoshaGoods();

		model.addAttribute("goodsList",goodsList);

		//然后进行手动渲染
		WebContext wc = new WebContext(request,response,request.getServletContext(),
				request.getLocale(),model.asMap());
		//goods_list 指的是前端页面模版（即你要将数据返回到哪个页面）
		goodsListHtml = thymeleafViewResolver.getTemplateEngine().process("goods_list", wc);
		//存入redis
		if(!StringUtils.isEmpty(goodsListHtml)){
			RedisUtil.set(RedisKey.GOODS_LIST, goodsListHtml, RedisKey.expireTime);
		}

		return goodsListHtml;
	}
//
//	/**秒杀商品详情
//	 */
//	@RequestMapping("/to_detail")
//	@ResponseBody
//	public String toDetail(@UserInfo TbMiaoshaUser tbMiaoshaUser, Model model, HttpServletRequest request, HttpServletResponse response,
//						   @RequestParam(value = "goodsId") Long goodsId){
//
//		String goodsDetailHtml = RedisUtil.get(RedisKey.GOODS_DETAIL + "_" +goodsId);
//		if (goodsDetailHtml != null && goodsDetailHtml.trim().length() > 0) {
//			//缓存中有数据
//			return goodsDetailHtml;
//		}
//
//		//缓存中没有，从数据库取
//		TbGoodsVo tbGoodsVo = miaoshaGoodService.getMiaoshaGoodsDetail(goodsId);
//
//		model.addAttribute("goods", tbGoodsVo);
//		model.addAttribute("remainSeconds", tbGoodsVo.getRemainSeconds());
//		model.addAttribute("miaoshaStatus", tbGoodsVo.getMiaoshaStatus());
//
//		//然后进行手动渲染
//		WebContext wc = new WebContext(request,response,request.getServletContext(),
//				request.getLocale(), model.asMap());
//		//goods_list 指的是前端页面模版（即你要将数据返回到哪个页面）
//		goodsDetailHtml = thymeleafViewResolver.getTemplateEngine().process("goods_detail", wc);
//		//存入redis
//		if(!StringUtils.isEmpty(goodsDetailHtml)){
//			RedisUtil.set(RedisKey.GOODS_DETAIL + "_" +goodsId, goodsDetailHtml, RedisKey.expireTime);
//		}
//
//		return goodsDetailHtml;
//	}
	////手动渲染模板==============版本一==============end=======================================



	// 走template模板==============版本二=====================start========================
	/**秒杀商品列表
	 */
//	@RequestMapping("/to_list")
//	public String toList(Model model){
//
//		List<TbGoodsVo> goodsList = miaoshaGoodService.getMiaoshaGoods();
//		model.addAttribute("goodsList",goodsList);
//		return "/goods_list";
//	}

	/**秒杀商品详情
	 */
//	@RequestMapping("/to_detail")
//	public String toDetail(Model model, @RequestParam(value = "goodsId") Long goodsId){
//		TbGoodsVo tbGoodsVo = miaoshaGoodService.getMiaoshaGoodsDetail(goodsId);
//
//		model.addAttribute("goods", tbGoodsVo);
//		model.addAttribute("remainSeconds", tbGoodsVo.getRemainSeconds());
//		model.addAttribute("miaoshaStatus", tbGoodsVo.getMiaoshaStatus());
//
//		return "/goods_detail";
//	}
// 走template模板==============版本二=====================end========================


	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<TbMiaoshaGood> findAll(){			
		return miaoshaGoodService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	@ResponseBody
	public PageResult  findPage(int page,int rows){			
		return miaoshaGoodService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param miaoshaGood
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestBody TbMiaoshaGood miaoshaGood){
		try {
			miaoshaGoodService.add(miaoshaGood);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param miaoshaGood
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody TbMiaoshaGood miaoshaGood){
		try {
			miaoshaGoodService.update(miaoshaGood);
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
	public TbMiaoshaGood findOne(Long id){
		return miaoshaGoodService.findOne(id);		
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
			miaoshaGoodService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param miaoshaGood
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public PageResult search(@RequestBody TbMiaoshaGood miaoshaGood, int page, int rows  ){
		return miaoshaGoodService.findPage(miaoshaGood, page, rows);		
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        //将每个商品数量，放到redis里预存
		List<TbGoodsVo> goodsList = miaoshaGoodService.getMiaoshaGoods();
		if (goodsList != null) {
			goodsList.forEach(good->{
				RedisUtil.set(RedisKey.GOODS_STOCK_COUNT + "_" + good.getId(), good.getStockCount()+"", RedisKey.expireTime);
			});
		}


    }
}
