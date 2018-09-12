package com.wallxu.sell.service;

import com.wallxu.sell.bean.ProductCategory;

import java.util.List;


public interface CategoryService {

    public ProductCategory save(ProductCategory productCategory);

    public List<ProductCategory> findAll();

    public ProductCategory findById(Integer categoryId);

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
