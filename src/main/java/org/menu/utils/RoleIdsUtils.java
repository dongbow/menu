package org.menu.utils;

import java.util.ArrayList;
import java.util.List;

import org.menu.model.Role;

public class RoleIdsUtils {

	public static String getRoleIds(List<Role> roles) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Role role : roles) {
			ids.add(role.getRoleId());
		}
		return formatIds(ids.toString());
	}
	
	public static String formatIds(String ids) {
		return ids.substring(1, ids.length() - 1).replace(" ", "");
	}
	
	public static List<Integer> decodeRoleIds(Object ids) {
		List<Integer> roleIds = new ArrayList<Integer>();
		for (int i = 0; i < ids.toString().split(",").length; i++) {
			roleIds.add(Integer.parseInt(ids.toString().split(",")[i]));
		}
		return roleIds;
	}
	
	public static String getRoleIdsByCookie(List<Integer> ids) {
		return formatIds(ids.toString());
	}
	
}
