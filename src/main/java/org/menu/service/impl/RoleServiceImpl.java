package org.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.menu.dao.RoleDao;
import org.menu.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	public List<Integer> getRoleIds() {
		return roleDao.getRoleIds();
	}

}
