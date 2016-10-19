package org.menu.dao;

import java.util.List;
import java.util.Map;

import org.menu.model.Resources;

public interface ResourcesDao {

	public List<Resources> getMenu(Object[] roleIds);

	public List<Resources> getMenuByRoleId(Integer roleId);
	
	public List<Map<String, Object>> getAllMenu();
	
}
