package org.menu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resources implements Serializable {

	private static final long serialVersionUID = -820776122994227877L;
	
	private int resId;
	private String resName;
	private String resHref;
	private Integer pid;
	private int resType;
	private List<Role> roles;
	private List<Resources> resources = new ArrayList<Resources>();

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResHref() {
		return resHref;
	}

	public void setResHref(String resHref) {
		this.resHref = resHref;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addChild(Resources res) {
		this.resources.add(res);
	}

	@Override
	public String toString() {
		return "Resources [resId=" + resId + ", resName=" + resName
				+ ", resHref=" + resHref + ", pid=" + pid + ", resources="
				+ resources + "]";
	}

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}

}
