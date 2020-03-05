package com.wallxu.finance.rpc.service;

import com.wallxu.common.utils.DateFormatHelper;
import com.wallxu.finance.dao.entity.FinanceProduct;
import com.wallxu.finance.dao.entity.enums.ProductStatus;
import com.wallxu.finance.dao.repository.FinanceProductRepository;
import com.wallxu.finance.rpc.api.FinanceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/14.
 */
@Service(value = "financeProductService")
public class FinanceProductServiceImpl implements FinanceProductService {

    @Autowired
    private FinanceProductRepository financeProductRepository;

    //保存的对象，有id就是修改，没有就是新增
    @Override
    public FinanceProduct addOrUpdate(FinanceProduct financeProduct) {
        if (financeProduct.getId() == null) {
            //新增，处理数据
            financeProduct.setStatus(ProductStatus.AUDITING.getDesc());
            financeProduct.setCreateTime(DateFormatHelper.date2str(new Date()));
            financeProduct.setCreateUser("");
        }else {
            financeProduct.setUpdateTime(DateFormatHelper.date2str(new Date()));
            financeProduct.setUpdateUser("");
        }
        return financeProductRepository.save(financeProduct);
    }
}
