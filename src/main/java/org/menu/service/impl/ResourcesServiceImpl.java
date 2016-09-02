package org.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.menu.dao.ResourcesDao;
import org.menu.model.Resources;
import org.menu.service.ResourcesService;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Resource
	private ResourcesDao resourcesDao;
	
	@Override
	public List<Resources> getMenu(List<Integer> roleIds) {
		return resourcesDao.getMenu(roleIds.toArray());
	}

}
