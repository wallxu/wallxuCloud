package com.wallxu.sell.dao;

import com.wallxu.sell.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    @Modifying
    @Transactional
    @Query("update OrderMaster o set o.payStatus = :status where o.orderId = :orderId ")
    boolean updateOrderSatus(@Param("orderId") String orderId, @Param("status") Integer status);

    OrderMaster findByOrderIdAndOrderStatus(String orderId, Integer code);

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
