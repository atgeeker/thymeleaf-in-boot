package com.geek.spring.boot.blog.repository;

import java.util.List;

import com.geek.spring.boot.blog.domain.User;

/**
 * User Repository 接口
 * @author zhuyangyong
 * @date 2017年9月5日 下午1:54:25 
 */
public interface UserRepository {
	
	/**
	 * 新增或修改用户
	 * @param user
	 * @return
	 */
	User saveOrUpdate(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> listUsers();

}
