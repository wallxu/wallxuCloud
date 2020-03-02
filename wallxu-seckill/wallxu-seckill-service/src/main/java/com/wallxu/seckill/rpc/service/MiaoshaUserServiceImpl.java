package com.wallxu.seckill.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wallxu.common.base.PageResult;
import com.wallxu.common.constant.ErrorCode;
import com.wallxu.common.exception.GlobalException;
import com.wallxu.common.jwt.JwtToken;
import com.wallxu.common.utils.MD5Util;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;
import com.wallxu.seckill.dao.domain.TbMiaoshaUserExample;
import com.wallxu.seckill.dao.mapper.TbMiaoshaUserMapper;
import com.wallxu.seckill.rpc.api.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

	@Autowired
	private TbMiaoshaUserMapper miaoshaUserMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbMiaoshaUser> findAll() {
		return miaoshaUserMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbMiaoshaUser> page=   (Page<TbMiaoshaUser>) miaoshaUserMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbMiaoshaUser miaoshaUser) {
		miaoshaUserMapper.insert(miaoshaUser);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbMiaoshaUser miaoshaUser){
		miaoshaUserMapper.updateByPrimaryKey(miaoshaUser);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbMiaoshaUser findOne(Long id){
		return miaoshaUserMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			miaoshaUserMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbMiaoshaUser miaoshaUser, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbMiaoshaUserExample example=new TbMiaoshaUserExample();
		TbMiaoshaUserExample.Criteria criteria = example.createCriteria();
		
		if(miaoshaUser!=null){			
						if(miaoshaUser.getNickname()!=null && miaoshaUser.getNickname().length()>0){
				criteria.andNicknameLike("%"+miaoshaUser.getNickname()+"%");
			}
			if(miaoshaUser.getPassword()!=null && miaoshaUser.getPassword().length()>0){
				criteria.andPasswordLike("%"+miaoshaUser.getPassword()+"%");
			}
			if(miaoshaUser.getSalt()!=null && miaoshaUser.getSalt().length()>0){
				criteria.andSaltLike("%"+miaoshaUser.getSalt()+"%");
			}
			if(miaoshaUser.getHead()!=null && miaoshaUser.getHead().length()>0){
				criteria.andHeadLike("%"+miaoshaUser.getHead()+"%");
			}
	
		}
		
		Page<TbMiaoshaUser> page= (Page<TbMiaoshaUser>)miaoshaUserMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public boolean login(TbMiaoshaUser tbMiaoshaUser) {

		//id字段查询用户 手机号
		TbMiaoshaUser miaoshaUser = miaoshaUserMapper.selectByPrimaryKey(Long.valueOf(tbMiaoshaUser.getMobile()));

		if (miaoshaUser == null) {
			//用户不存在
			throw new GlobalException(ErrorCode.USER_IS_NULL);
		}
		//第一次md5加密后
		String loginPassword = tbMiaoshaUser.getPassword();

		//第二次md5加密后
		String dbPassword = MD5Util.md5(loginPassword, miaoshaUser.getSalt());
		//判断用户密码
		if (!dbPassword.equals(miaoshaUser.getPassword())){
			//密码正确错误
			throw new GlobalException(ErrorCode.USER_LOGIN_FAIL);
		}
		//密码正确，登录成功,生成token
		JwtToken.createToken();
		return true;
	}

}
