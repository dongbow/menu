package org.menu.model;

import java.util.List;

public class Role {

	private int roleId;
	private String roleName;
	private List<User> users;
	private List<Resources> resources;

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
