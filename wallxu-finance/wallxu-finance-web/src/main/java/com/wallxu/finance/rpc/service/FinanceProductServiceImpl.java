package com.wallxu.finance.rpc.service;

import com.wallxu.common.utils.DateFormatHelper;
import com.wallxu.finance.dao.entity.FinanceProduct;
import com.wallxu.finance.dao.entity.enums.ProductStatus;
import com.wallxu.finance.dao.repository.FinanceProductRepository;
import com.wallxu.finance.rpc.api.FinanceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//        if (financeProduct.getId() == null) {
            //新增，处理数据
            financeProduct.setStatus(ProductStatus.AUDITING.getDesc());
            financeProduct.setCreateTime(DateFormatHelper.date2str(new Date()));
            financeProduct.setCreateUser("");
//        }else {
//            financeProduct.setUpdateTime(DateFormatHelper.date2str(new Date()));
//            financeProduct.setUpdateUser("");
//        }
        return financeProductRepository.save(financeProduct);
    }

    @Override
    public FinanceProduct findOne(String id) {
        return financeProductRepository.findById(id).get();
    }

    //多条件查询
    @Override
    public Page<FinanceProduct> query(List<String> idList, Long maxRewardRate, Long minRewardRate, List<String> statusList, Pageable pageable) {

        Specification<FinanceProduct> specification = new Specification<FinanceProduct>() {
            @Override
            public Predicate toPredicate(Root<FinanceProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //获取条件字段
                Expression<String> idCol = root.get("id");
                Expression<Long> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");

                List<Predicate> predicates = new ArrayList<Predicate>();
                //id
                if (idList != null) {
                    predicates.add(idCol.in(idList));
                }

                //status
                if (statusList != null) {
                    predicates.add(statusCol.in(statusList));
                }
                //收益率范围
                if (maxRewardRate != null ) {
                    predicates.add(criteriaBuilder.le(rewardRateCol, maxRewardRate));
                }

                if (minRewardRate != null ) {
                    predicates.add(criteriaBuilder.ge(rewardRateCol, minRewardRate));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        return financeProductRepository.findAll(specification, pageable);
    }
}
