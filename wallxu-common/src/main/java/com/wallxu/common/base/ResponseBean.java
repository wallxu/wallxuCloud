package com.wallxu.common.base;

import com.wallxu.common.constant.ErrorCode;

import java.io.Serializable;
import java.util.List;

public class ResponseBean<T> implements Serializable{
	private int code;//响应码
	private String msg;//描述信息
	private List<T> data;

	public int getCode() {
		return code;
	}

	public ResponseBean() {
		super();
	}

	public ResponseBean(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}


	public ResponseBean(ErrorCode errCode) {
		super();
		this.code = errCode.getCode();
		this.msg = errCode.getMessage();
	}

	public ResponseBean(int code, String msg, List<T> data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResponseBean(ErrorCode errCode, List<T> data) {
		super();
		this.code = errCode.getCode();
		this.msg = errCode.getMessage();
		this.data = data;
	}

	private void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public void setRespCode(ErrorCode errCode){
		setCode(errCode.getCode());
		setMsg(errCode.getMessage());

	}
	
	public void setRespCode(String msg){
		setCode(-1);
		setMsg(msg);
	}
}
