package com.wallxu.tx.service;

import com.wallxu.tx.dao.MessageInfoDao;
import com.wallxu.tx.dao.MessageInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * MessageInfo服务类
 *
 * @author wallxu
 */
@Service(value = "messageInfoService")
public class MessageInfoService {

	@Resource(name ="messageInfoDao")
	private MessageInfoDao messageInfoDao;

	/**
	 * 保存消息
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Long saveAndReturnMessageInfoId(String title, String messageContent, String paramJsonStr, Integer messageType,
											Integer source, String reccipientCode, String refCode, String createUser) throws Exception {
		Long l = 0L;

		MessageInfoEntity messageInfo = new MessageInfoEntity();
		messageInfo.setTitle(title);
		messageInfo.setContent(messageContent);
		messageInfo.setParam(paramJsonStr);
		messageInfo.setType(messageType);
		messageInfo.setSource(source);
		messageInfo.setRecipientCode(reccipientCode);
		messageInfo.setRefCode(refCode);
		messageInfo.setCreateUser(createUser);
		messageInfo.setCreateTime(new Date());
		messageInfo.setReadStatus(0);
		//保存消息
		 return messageInfoDao.saveMessageInfo(messageInfo);

	}

}
