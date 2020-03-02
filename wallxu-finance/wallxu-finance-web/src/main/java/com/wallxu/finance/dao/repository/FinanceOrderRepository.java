package com.wallxu.finance.dao.repository;

import com.wallxu.finance.dao.entity.FinanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/8/14.
 */
public interface FinanceOrderRepository extends JpaRepository<FinanceOrder, String> {
}
