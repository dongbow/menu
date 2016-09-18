package org.menu.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.menu.model.Resources;
import org.menu.service.ResourcesService;
import org.menu.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class TreeTest {

	@Autowired
	private ResourcesService service;
	
	@Test
	public void test() {
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(2);
		roleIds.add(3);
		List<Resources> resources = TreeUtils.formatResources(service.getMenu(roleIds));
		for (Resources res : resources) {
			System.out.println(res.toString());
		}
	}

}
