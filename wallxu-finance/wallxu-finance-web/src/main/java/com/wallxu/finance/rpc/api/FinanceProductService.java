package com.wallxu.finance.rpc.api;

import com.wallxu.finance.dao.entity.FinanceProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/14.
 */
public interface FinanceProductService {

    public FinanceProduct addOrUpdate(FinanceProduct financeProduct);

    FinanceProduct findOne(String id);

    Page<FinanceProduct> query(List<String> idList, Long maxRewardRate, Long minRewardRate, List<String> statusList, Pageable pageable);
}
