package com.wallxu.sell.service;

import com.wallxu.sell.bean.ProductInfo;
import com.wallxu.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public ProductInfo save(ProductInfo productInfo);

    public List<ProductInfo> findAll();

    public ProductInfo findById(String productId);

    public List<ProductInfo> findUPAll();

    public List<ProductInfo> findByCategoryId(Integer categoryId);

    public Page<ProductInfo> findAll(Pageable pageable);

    public void offSale(String productId);

    public void onSale(String productId);

    public void decreaseStock(List<CartDTO> cartDTOList);

    public void increaseStock(List<CartDTO> cartDTOList);
}
