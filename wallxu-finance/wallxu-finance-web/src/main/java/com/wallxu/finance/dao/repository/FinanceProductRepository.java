package com.wallxu.finance.dao.repository;

import com.wallxu.finance.dao.entity.FinanceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/8/14.
 */
public interface FinanceProductRepository extends JpaRepository<FinanceProduct,String>, JpaSpecificationExecutor<FinanceProduct> {
}
