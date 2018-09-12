package com.wallxu.finance.dao.repository;

import com.wallxu.finance.dao.entity.FinanceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/8/14.
 */
@Repository
public interface FinanceProductRepository extends JpaRepository<FinanceProduct,String>{
}
