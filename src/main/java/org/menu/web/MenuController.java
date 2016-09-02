package org.menu.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.menu.model.Resources;
import org.menu.model.Role;
import org.menu.model.User;
import org.menu.service.ResourcesService;
import org.menu.service.UserService;
import org.menu.utils.Base64Util;
import org.menu.utils.TreeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@Resource
	private UserService userService;
	
	@Resource
	private ResourcesService resourcesService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpServletResponse response,
			String username, String password) {
		User user = userService.login(username, password);
		List<Role> roles = user.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}
		String cookieValue = Base64Util.getBase64(user.getUserId() + ";" + roleIds.toString().substring(1, roleIds.toString().length() - 1).replace(" ", "")
				+ ";" + user.getUserName());
		Cookie cookie = new Cookie("MENU", cookieValue);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		Cookie cookie[] = request.getCookies();
		String cookieValue = null;
		for (Cookie c : cookie) {
			if("MENU".equals(c.getName())) {
				cookieValue = Base64Util.getFromBase64(c.getValue());
			}
		}
		String roleString = cookieValue.split(";")[1];
		List<Integer> roleIds = new ArrayList<Integer>();
		for(int i = 0; i < roleString.split(",").length; i++) {
			roleIds.add(Integer.parseInt(roleString.split(",")[i]));
		}
		List<Resources> list = TreeUtils.merge(resourcesService.getMenu(roleIds));
		request.setAttribute("reslist", list);
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("MENU", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/login";
	} 
	
}
