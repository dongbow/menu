package org.menu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ltang.redis.service.RedisObjectMapService;

import org.menu.model.Resources;
import org.menu.model.User;
import org.menu.service.ResourcesService;
import org.menu.service.UserService;
import org.menu.utils.CookieUtils;
import org.menu.utils.RoleIdsUtils;
import org.menu.utils.TreeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class MenuController {

	@Resource
	private UserService userService;
	
	@Resource
	private ResourcesService resourcesService;
	
	@Resource
	private RedisObjectMapService mapService;
	
	@RequestMapping("/account/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/account/dologin", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpServletResponse response,
			String username, String password) {
		User user = userService.login(username, password);
		if(user != null) {
			response.addCookie(CookieUtils.createMenuCookie(user.getUserId(), user.getIsAdmin(), 
					RoleIdsUtils.getRoleIds(user.getRoles()), user.getUserName()));
			return "redirect:/system/auth/index";
		} else {
			return "redirect:/account/login";
		}
		
	}
	
	@RequestMapping("/system/auth/index")
	public String index(HttpServletRequest request) {
		List<Resources> list = TreeUtils.formatResources((resourcesService.getMenu(CookieUtils.getRoleIdsFromCookie(request))));
		request.setAttribute("reslist", list);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/system/auth/getmenu", method = RequestMethod.POST)
	public JSONObject getMenu(HttpServletRequest request) {
		List<Integer> ids = CookieUtils.getRoleIdsFromCookie(request);
		JSONObject jsonObject = mapService.get("menu:sys:role:" + RoleIdsUtils.getRoleIdsByCookie(ids), JSONObject.class);
		if(jsonObject == null) {
			List<Resources> list = TreeUtils.formatResources((resourcesService.getMenu(ids)));
			jsonObject.put("menu", JSON.toJSONString(list));
			mapService.save("menu:sys:role:" + RoleIdsUtils.getRoleIdsByCookie(ids), jsonObject, JSONObject.class);
		}
		return jsonObject;
	}
	
	@RequestMapping("/account/logout")
	public String logout(HttpServletResponse response) {
		response.addCookie(CookieUtils.makeCookieExpire(CookieUtils.MENU_COOKIE));
		return "redirect:/account/login";
	} 
	
}
