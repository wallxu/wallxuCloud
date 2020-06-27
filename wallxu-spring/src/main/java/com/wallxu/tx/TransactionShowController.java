package com.wallxu.tx;

import com.wallxu.tx.service.TransactionShowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: xukf
 * @email: xukf1@ziroom.com
 * @date: 2020/5/21 16:15
 * @since 1.0.0
 */
public class TransactionShowController {
	private static final Logger log = LoggerFactory.getLogger(TransactionShowServiceImpl.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction.xml");

//		log.info( applicationContext.getBeanDefinitionNames().toString());
//		DataSourceTransactionManager dataSourceTransactionManager = applicationContext.getBean("dataSourceTransactionManager", DataSourceTransactionManager.class);

		//service
		TransactionShowServiceImpl transactionShowService = applicationContext.getBean("transactionShowServiceImpl", TransactionShowServiceImpl.class);

		transactionShowService.saveRectifyMessage("111111111111111111111", 10, 1);

	}
}