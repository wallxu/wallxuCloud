package com.wallxu.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * 返回结果
 * @author Administrator
 *
 */
public class Result<T> implements Serializable{

	private boolean success;//是否成功
	
	private String message;//返回信息

	private List<T> data;

	
	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result(boolean success) {
		super();
		this.success = success;
		if (success){
			this.message = "操作成功";
		}else {
			this.message = "操作失败";
		}

	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" +
				"success=" + success +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
