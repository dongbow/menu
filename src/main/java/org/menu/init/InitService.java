package org.menu.init;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import ltang.redis.service.RedisObjectMapService;

import org.menu.model.Resources;
import org.menu.service.ResourcesService;
import org.menu.service.RoleService;
import org.menu.utils.TreeUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class InitService {
	
	@Resource
	private RedisObjectMapService mapService;
	
	@Resource
	private ResourcesService resourcesService;
	
	@Resource
	private RoleService roleService;
	
	@PostConstruct
	public void init() {
		List<Integer> roleIds = roleService.getRoleIds();
		for (Integer roleId : roleIds) {
			List<Resources> resources = TreeUtils.formatResources((resourcesService.getMenuByRoleId(roleId)));
			JSONObject jsonObject = new JSONObject(true);
			jsonObject.put("menu", JSON.toJSONString(resources));
			mapService.save("menu:sys:role:" + roleId, jsonObject, JSONObject.class);
		}
	}
	
}
