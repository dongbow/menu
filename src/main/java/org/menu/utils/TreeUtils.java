package org.menu.utils;

import java.util.ArrayList;
import java.util.List;

import org.menu.model.Resources;

public class TreeUtils {

	private static List<Resources> list1 = new ArrayList<Resources>();
	
	private static List<Resources> list2 = new ArrayList<Resources>();
	
	public static List<Resources> merge(List<Resources> list) {
		list1.clear();
		list2.clear();
		for (Resources res : list) {
			if(res.getPid() == null) {
				list1.add(res);
			} else {
				list2.add(res);
			}
		}
		
		
		for (Resources res : list1) {
			List<Resources> tmp = new ArrayList<Resources>();
			for (Resources child : list2) {
				if(child.getPid() == res.getResId()) {
					tmp.add(child);
				}
			}
			res.setResources(tmp);
		}
		return list1;
	}
	
}
