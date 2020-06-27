package com.wallxu.tx.service;

import org.springframework.stereotype.Service;

/**
 * TransactionShow服务类
 *
 * @author wallxu
 */
@Service(value = "transactionShowService")
public class TransactionShowService {

	/**
	 * 保存数据
	 */
//	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Long saveData() {

		//保存数据成功
		 return 1L;
	}

}
