package com.wallxu.sell.controller;


import com.wallxu.sell.VO.ResultVO;
import com.wallxu.sell.bean.ProductCategory;
import com.wallxu.sell.service.CategoryService;
import com.wallxu.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/seller/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //新增、修改
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProductCategory productCategory,
                       HttpServletRequest httpRequest,
                       Model model){

        ProductCategory saveCategory = categoryService.save(productCategory);

        model.addAttribute("url", httpRequest.getHeader("Referer"));
        if (saveCategory != null && saveCategory.getCategoryId() > 0){
            return "/common/success";
        }
        return "/common/error";
    }

    //查询一个
    @RequestMapping(value = "/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model){
        if (categoryId != null && categoryId > 0){
            ProductCategory productCategory = categoryService.findById(categoryId);
            model.addAttribute("category", productCategory);
        }
        return "/category/index";
    }


    //新增页
    @RequestMapping(value = "/add")
    public String add(){
        return "/category/index";
    }


    //查询品种列表
    @RequestMapping(value = "/list1")
    public ResultVO list(){
        List<ProductCategory> categoryList = categoryService.findAll();
        return ResultVOUtil.success(categoryList);
    }

    //查询品种列表
    @RequestMapping(value = "/list")
    public String list(Model model){
        List<ProductCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "/category/list";
    }
}
