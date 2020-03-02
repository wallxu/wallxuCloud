package com.wallxu.sell.service.impl;

import com.wallxu.sell.bean.ProductInfo;
import com.wallxu.sell.dao.ProductRepository;
import com.wallxu.sell.dto.CartDTO;
import com.wallxu.sell.enums.ProductStatusEnum;
import com.wallxu.sell.enums.ResultEnum;
import com.wallxu.sell.exception.SellException;
import com.wallxu.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductInfo findById(String productId) {
        return productRepository.findById(productId).get();
    }

    //所有上架的商品
    @Override
    public List<ProductInfo> findUPAll() {
        return this.findByCategoryId(ProductStatusEnum.UP.getCode());
    }

    //按商品类型查询
    @Override
    public List<ProductInfo> findByCategoryId(Integer categoryId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(categoryId);

        Example<ProductInfo> example = Example.of(productInfo);
        return productRepository.findAll(example);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //下架
    @Override
    public void offSale(String productId) {
        productRepository.updateProductStatus(productId, ProductStatusEnum.DOWN.getCode());
    }

    //上架
    @Override
    public void onSale(String productId) {
        productRepository.updateProductStatus(productId, ProductStatusEnum.UP.getCode());
    }

    //减少库存
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productRepository.getOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }

            if (productInfo.getProductStock() < cartDTO.getProductQuantity()){
                //商品库存不足
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR.getMessage());
            }

            productInfo.setProductStock(productInfo.getProductStock() - cartDTO.getProductQuantity());
            productRepository.save(productInfo);
        }

    }

    //增加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }
}
