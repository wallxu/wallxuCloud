package com.wallxu.finance.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tb_finance_product")
public class FinanceProduct implements Serializable{
  @Id
  private String id;
  @NotNull(message = "品种名称不能为空")
  private String name;
  private Double thresholdAmount;
  private Double stepAmount;
  private Long lockTerm;
  @Min(value = 1, message = "收益率要大于零")
  @Max(value = 30, message = "收益率要小于三十")
  private Double rewardRate;
  private String status;
  private String memo; //备注
  @Column(name = "create_at")
  private String createTime;
  private String createUser;
  @Column(name = "update_at")
  private String updateTime;
  private String updateUser;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getThresholdAmount() {
    return thresholdAmount;
  }

  public void setThresholdAmount(Double thresholdAmount) {
    this.thresholdAmount = thresholdAmount;
  }

  public Double getStepAmount() {
    return stepAmount;
  }

  public void setStepAmount(Double stepAmount) {
    this.stepAmount = stepAmount;
  }

  public Long getLockTerm() {
    return lockTerm;
  }

  public void setLockTerm(Long lockTerm) {
    this.lockTerm = lockTerm;
  }

  public Double getRewardRate() {
    return rewardRate;
  }

  public void setRewardRate(Double rewardRate) {
    this.rewardRate = rewardRate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }
}
