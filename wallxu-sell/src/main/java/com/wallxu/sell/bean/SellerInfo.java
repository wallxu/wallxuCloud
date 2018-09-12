package com.wallxu.sell.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 廖师兄
 * 2017-07-23 23:02
 */
@Data
@Entity
@Table(name = "sell_seller_info")
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
