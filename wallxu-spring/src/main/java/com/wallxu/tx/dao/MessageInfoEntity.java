package com.wallxu.tx.dao;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * MessageInfo实体
 * @author: liuxm
 * @email:  liuxm7@ziroom.com
 * @date:   2017-11-28 16:11:58
 * @since 1.0.0
 */
public class MessageInfoEntity {

	private static final long serialVersionUID = 1L;

	//ID
	private Long id;
	//标题
	private String title;
	//内容
	private String content;
	//创建人
	private Long createUserId;
	//创建时间
	private String createTimeStr;
	//消息类型
	private Integer type;
	//业务关联ID
	private Long refId;
	//业务关联CODE
	private String refCode;
	//接受人
	private Long recipient;
	//接受人
	private String recipientCode;
	//消息状态
	private Integer readStatus;
	//读取时间
	private Date readDate;
	//URL
	private String url;
	//来源（APP类型）
	private Integer source;
	//参数
	private String param;
	//创建人
	private String createUser;
	//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	//最后修改人
	private String lastModifyUser;
	//最后修改时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastModifyTime;
	//是否删除
	private Integer isDel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Long getRecipient() {
		return recipient;
	}

	public void setRecipient(Long recipient) {
		this.recipient = recipient;
	}

	public String getRecipientCode() {
		return recipientCode;
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}