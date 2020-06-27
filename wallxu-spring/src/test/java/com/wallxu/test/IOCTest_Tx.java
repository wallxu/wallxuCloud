package com.wallxu.test;

import com.wallxu.tx.TxConfig;
import com.wallxu.tx.UserEntity;
import com.wallxu.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(TxConfig.class);
	
		UserService userService = applicationContext.getBean(UserService.class);

		//插入数据
		UserEntity userEntity = new UserEntity();
		String userName = "wangwang2";
		userEntity.setUsername(userName);
		userEntity.setAge(20);
		userService.insertUser(userEntity);

		//查询数据
		UserEntity user = userService.findByUsername(userName);

		//更新数据
		user.setAge(30);
		userService.update(user);

		applicationContext.close();
	}

}
