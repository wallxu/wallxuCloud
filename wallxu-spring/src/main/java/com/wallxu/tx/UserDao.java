package com.wallxu.tx;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

/**
 * @author wallxu
 */
//@Repository
public class UserDao {

//	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 插入
	 * @param userEntity
	 * @return: void
	 * @author: xukf
	 * @date: 2020/5/22 16:28
	 * @since 1.0.0
	 */
	public void insert(UserEntity userEntity){
		String sql = "INSERT INTO `tb_user`(username,age) VALUES(?,?)";
		String username = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getAge());
	}


	/**
	 * 更新
	 * @param userEntity
	 * @return: int
	 * @author: xukf
	 * @date: 2020/5/22 16:28
	 * @since 1.0.0
	 */
	public int update(UserEntity userEntity){
		if(userEntity.getId() == null){
			return 0;
		}
		String sql = "update `tb_user` set username = ?, age =? where id =?";
		return jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getAge(), userEntity.getId());
	}

	/**
	 * 查询
	 * @param userName
	 * @return: com.wallxu.tx.UserEntity
	 * @author: xukf
	 * @date: 2020/5/22 16:28
	 * @since 1.0.0
	 */
	public UserEntity findByUsername(String userName){
		if(userName == null){
			return null;
		}
		String sql = "select * from  `tb_user` where username = ? ";

		List<UserEntity> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserEntity.class), userName);
		System.out.println("查询结果：" + list);
		if(list != null && list.size()>0){
			return list.get(0);
		}

		return null;
	}
}
