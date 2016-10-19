package org.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.menu.dao.ResourcesDao;
import org.menu.model.Resources;
import org.menu.service.GeneralService;
import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService{

	@Resource
	private ResourcesDao resourcesDao;
	
	public boolean authMenu(String uri, Object[] roleIds) {
		List<Resources> list = resourcesDao.getMenu(roleIds);
		for (Resources resources : list) {
			System.out.println(resources.getResHref());
			if(resources.getResHref().indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

}
