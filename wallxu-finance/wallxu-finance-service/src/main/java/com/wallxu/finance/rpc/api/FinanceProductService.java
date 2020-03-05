package com.wallxu.finance.rpc.api;

import com.wallxu.finance.dao.entity.FinanceProduct;

/**
 * Created by Administrator on 2018/8/14.
 */
public interface FinanceProductService {

    public FinanceProduct addOrUpdate(FinanceProduct financeProduct);
}
