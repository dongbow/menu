package org.menu.dao;

import java.util.List;

import org.menu.model.Resources;

public interface ResourcesDao {

	public List<Resources> getMenu(Object[] roleIds);
	
}
