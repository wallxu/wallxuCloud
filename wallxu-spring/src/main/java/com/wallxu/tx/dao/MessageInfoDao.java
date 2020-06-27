package com.wallxu.tx.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author: xukf
 * @email: xukf1@ziroom.com
 * @date: 2020/5/22 10:32
 * @since 1.0.0
 */
@Repository("messageInfoDao")
public class MessageInfoDao {

	/**
	 * 查询消息
	 *
	 */
	public List<MessageInfoEntity> findList(MessageInfoEntity searchReq) throws Exception {
		throw new Exception("获取MessageInfo失败");
	}

	/**
	 * 保存消息
	 */
	public Long saveMessageInfo(MessageInfoEntity messageInfo) throws Exception {
		throw new Exception("保存消息失败");
	}

	public Integer updateMessageInfo(MessageInfoEntity request) throws Exception {
		throw new Exception("更新消息失败");
	}

}
