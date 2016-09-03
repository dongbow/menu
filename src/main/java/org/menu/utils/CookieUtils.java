package org.menu.utils;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	public static String MENU_COOKIE = "MENU_COOKIE";
	public static String PATH = "/";
	public static int EXPIRE_TIME = 60 * 60 * 24 * 7;
	
	public static Cookie createCookie(String cookieName, String value, 
			String path, int expireTime) {
		Cookie cookie = new Cookie(cookieName, Base64Utils.getBase64(value));
		cookie.setPath(path);
		cookie.setMaxAge(expireTime);
		return cookie;
	}
	
	public static Cookie createMenuCookie(long userId, String roleIds, String userName) {
		String value = userId + ";" + roleIds + ";" + userName;
		Cookie cookie = new Cookie(MENU_COOKIE, Base64Utils.getBase64(value));
		cookie.setPath(PATH);
		cookie.setMaxAge(EXPIRE_TIME);
		return cookie;
	}
	
	public static String getCookieValue(String cookieName, HttpServletRequest request) {
		String cookieValue = null;
		Cookie cookie[] = request.getCookies();
		for (Cookie c : cookie) {
			if(cookieName.equals(c.getName())) {
				cookieValue = Base64Utils.getFromBase64(c.getValue());
			}
		}
		return cookieValue;
	}
	
	public static Cookie makeCookieExpire(String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setPath(PATH);
		cookie.setMaxAge(-1);
		return cookie;
		
	}
	
	public static long getUserIdFromCookie(HttpServletRequest request) {
		return (long) getValue(request, "userId");
	}
	
	public static String getUserNameFromCookie(HttpServletRequest request) {
		return (String) getValue(request, "userName");
	}
	
	public static List<Integer> getRoleIdsFromCookie(HttpServletRequest request) {
		return RoleIdsUtils.decodeRoleIds(getValue(request, "roleIds"));
	}
	
	private static Object getValue(HttpServletRequest request, String field) {
		String cookieValue = null;
		Cookie cookie[] = request.getCookies();
		for (Cookie c : cookie) {
			if(MENU_COOKIE.equals(c.getName())) {
				cookieValue = Base64Utils.getFromBase64(c.getValue());
			}
		}
		Object o[] = cookieValue.split(";");
		if ("userId".equals(field)) {
			return o[0];
		} else if ("userName".equals(field)) {
			return o[2];
		} else {
			return o[1];
		}
	} 
	
}
