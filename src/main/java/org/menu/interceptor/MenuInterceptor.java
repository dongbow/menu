package org.menu.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.menu.service.GeneralService;
import org.menu.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MenuInterceptor extends HandlerInterceptorAdapter {

	private String root = "/menu";
	
	private String home = "/system/auth/index";
	
	@Value("${none}")
	private String notInterceptor;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private GeneralService generalService;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		initService(request);
		
		String uri = formatUri(request);
		
		if(!uri.startsWith(notInterceptor)){
			
			if(CookieUtils.getCookieValue(CookieUtils.MENU_COOKIE, request) == null) {
				logger.info("no login");
				return false;
			}
			if(uri.endsWith(home)){
				Integer isAdmin = CookieUtils.getIsAdminFromCookie(request);
				if(isAdmin == null || isAdmin != 1){
					logger.info("no auth");
					return false;
				}
			}
			if(!generalService.authMenu(uri, CookieUtils.getRoleIdsFromCookie(request).toArray())) {
				logger.info("no auth");
				return false;
			}
		}
		logger.info("not");
		return true;
	}
	
	private String formatUri(HttpServletRequest request) {
		return request.getRequestURI().replace(root, "");
	}
	
	private void initService(HttpServletRequest request) {
		if(generalService == null) {
			WebApplicationContext webctx = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext());
			generalService = (GeneralService) webctx.getBean("generalService");
		}
	}
	
}
