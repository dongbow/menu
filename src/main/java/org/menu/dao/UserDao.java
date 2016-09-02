package org.menu.dao;

import org.apache.ibatis.annotations.Param;
import org.menu.model.User;

public interface UserDao {

	public User login(@Param("username")String username, @Param("password")String password);
	
}
