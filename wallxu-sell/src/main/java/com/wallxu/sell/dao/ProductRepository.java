package com.wallxu.sell.dao;

import com.wallxu.sell.bean.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, String> {

    @Modifying
    @Transactional
    @Query("update ProductInfo p set p.productStatus = :status where p.productId = :productId ")
    void updateProductStatus(@Param("productId") String productId, @Param("status") Integer status);
}
