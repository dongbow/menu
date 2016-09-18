package org.menu.service;

import java.util.List;

import org.menu.model.Resources;

public interface ResourcesService {

	public List<Resources> getMenu(List<Integer> roleIds);

	public List<Resources> getMenuByRoleId(Integer roleId);
	
}
