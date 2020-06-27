package com.wallxu.tx.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xukf
 * @email: xukf1@ziroom.com
 * @date: 2020/5/21 16:17
 * @since 1.0.0
 */
@Service
public class TransactionShowServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(TransactionShowServiceImpl.class);

	@Resource(name = "messageInfoService")
	private MessageInfoService messageInfoService;

    @Resource(name = "transactionShowService")
    private TransactionShowService transactionShowService;

	/**
	 * 推送app消息
	 */
	public void saveRectifyMessage(String orderCode, Integer rectifyDetailId, Integer messageType) {
		log.info("saveRectifyMessage messageType:{}", messageType.toString());
		String messageName = "整改待办";

		//1、保存消息
		try {

			//消息内容
			String messageContent = orderCode + "&" + messageName;
			JSONObject param = new JSONObject();
			//订单编号
			param.put("orderCode", orderCode);
			param.put("dispatchOrderCode", "");
			//保存消息
			Long messageId = messageInfoService.saveAndReturnMessageInfoId(messageName, messageContent, param.toJSONString(), messageType,
					messageType, null,  orderCode, "system");


			log.info("保存成功");
		} catch (Exception e) {
			log.error("订单:{}的整改记录:{}的:{}发送失败", orderCode, rectifyDetailId, messageName, e);
		}

		//2、保存业务数据
        transactionShowService.saveData();

	}

}
