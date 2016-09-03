package org.menu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.addCookie(CookieUtils.createMenuCookie(user.getUserId(), 
				RoleIdsUtils.getRoleIds(user.getRoles()), user.getUserName()));
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<Resources> list = TreeUtils.merge(resourcesService.getMenu(CookieUtils.getRoleIdsFromCookie(request)));
		request.setAttribute("reslist", list);
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		response.addCookie(CookieUtils.makeCookieExpire(CookieUtils.MENU_COOKIE));
		return "redirect:/login";
	} 
	
}
