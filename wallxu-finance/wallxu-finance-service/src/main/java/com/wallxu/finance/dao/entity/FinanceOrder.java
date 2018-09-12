package com.wallxu.finance.dao.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_finance_order")
public class FinanceOrder implements Serializable{
  @Id
  private String orderId;
  private String chanId;
  private String productId;
  private String chanUserId;
  private String orderType;
  private String orderStatus;
  private String outerOrderId;
  private Double amount;
  private String memo;
  @Column(name = "create_at")
  private String createTime;
  @Column(name = "update_at")
  private String updateTime;

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

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
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

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }
}
