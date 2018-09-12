package com.wallxu.finance.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_finance_verification_order")
public class FinanceVerificationOrder implements Serializable{
  @Id
  private String orderId;
  private String chanId;
  private String productId;
  private String chanUserId;
  private String orderType;
  private String outerOrderId;
  private Double amount;
  @Column(name = "create_at")
  private String createTime;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getChanId() {
    return chanId;
  }

  public void setChanId(String chanId) {
    this.chanId = chanId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getChanUserId() {
    return chanUserId;
  }

  public void setChanUserId(String chanUserId) {
    this.chanUserId = chanUserId;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getOuterOrderId() {
    return outerOrderId;
  }

  public void setOuterOrderId(String outerOrderId) {
    this.outerOrderId = outerOrderId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
