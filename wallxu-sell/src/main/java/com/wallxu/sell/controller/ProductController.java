package com.wallxu.sell.controller;

import com.wallxu.sell.bean.ProductCategory;
import com.wallxu.sell.bean.ProductInfo;
import com.wallxu.sell.service.CategoryService;
import com.wallxu.sell.service.ProductService;
import com.wallxu.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
 

@Controller
@RequestMapping(value = "/seller/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    //跳转新增页面
    @RequestMapping(value = "/add")
    public String add(Model model){
        List<ProductCategory> productCategory = categoryService.findAll();
        model.addAttribute("categoryList", productCategory);
        return "/product/index";
    }



    //新增、修改
    @RequestMapping(value = "/save")
    public String save(ProductInfo productInfo,
                       HttpServletRequest httpRequest,
                       Model model){

        //如果productId为空, 说明是新增
        if (!StringUtils.isEmpty(productInfo.getProductId())) {
//            productInfo = productService.findById(productInfo.getProductId());
        } else {
            productInfo.setProductId(KeyUtil.genUniqueKey());
        }

        productService.save(productInfo);

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        return "/common/success";
    }


    //查询一个
    @RequestMapping(value = "/index")
    public String index(@RequestParam(value = "productId", required = false) String productId, Model model){
        if (productId != null && productId.trim().length() > 0){
            ProductInfo productInfo = productService.findById(productId);
            model.addAttribute("productInfo", productInfo);
        }
        return "/product/index";
    }

    //查询商品列表
    @RequestMapping(value = "/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size){

        PageRequest pageRequest = PageRequest.of(page -1, size);

        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        model.addAttribute("productInfoPage", productInfoPage);
        model.addAttribute("currentPage", page == null? 1:page);
        return "/product/list";
    }


    //下架
    @RequestMapping(value = "/off_sale")
    public String offSale(@RequestParam(value = "productId") String productId, HttpServletRequest httpRequest,
                          Model model){

        productService.offSale(productId);

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        return "/common/success";
    }


    //上架
    @RequestMapping(value = "/on_sale")
    public String onSale(@RequestParam(value = "productId") String productId, HttpServletRequest httpRequest,
                         Model model){

        productService.onSale(productId);

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        return "/common/success";
    }



}
