package com.wallxu.tx;

import org.springframework.transaction.annotation.Transactional;


//@Service
public class UserService {
	
//	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void insertUser(UserEntity userEntity){
		userDao.insert(userEntity);
		System.out.println("插入完成..." + userEntity);
//		int i = 10/0;
	}

	@Transactional(rollbackFor = Exception.class)
	public void update(UserEntity userEntity){
		userDao.update(userEntity);
		System.out.println("更新完成..." + userEntity);
		int i = 10/0;
	}

	public UserEntity findByUsername(String userName){
		return userDao.findByUsername(userName);
	}
}
