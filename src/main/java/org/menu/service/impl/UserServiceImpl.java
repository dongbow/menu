package org.menu.service.impl;

import javax.annotation.Resource;

import org.menu.model.User;
import org.menu.service.UserService;
import org.menu.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

}
