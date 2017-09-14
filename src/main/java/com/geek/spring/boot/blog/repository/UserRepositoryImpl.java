package com.geek.spring.boot.blog.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.geek.spring.boot.blog.domain.User;

/**
 * User Repository 接口实现类
 * @author zhuyangyong
 * @date 2017年9月5日 下午2:09:41 
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	//用于计数，每次新增对象自增1
	private static AtomicLong counter = new AtomicLong();
	
	//用于存储用户信息
	private ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

	@Override
	public User saveOrUpdate(User user) {
		Long id = user.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.userMap.put(id,user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		this.userMap.remove(id);
	}

	@Override
	public User getUserById(Long id) {
		return this.userMap.get(id);
	}

	@Override
	public List<User> listUsers() {
		return new ArrayList<User>(this.userMap.values());
	}

}
