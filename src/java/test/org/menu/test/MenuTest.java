package org.menu.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ltang.redis.service.RedisObjectListService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.menu.model.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class MenuTest {

	@Autowired
	private RedisObjectListService listService;
	
	@Test
	public void test() {
		List<Resources> test = (List<Resources>) listService.getObjectByIndex("menu:sys:role:3", 1);
		for (Resources res : test) {
			System.out.println(res);
		}
		List<Resources> admin = (List<Resources>) listService.getObjectByIndex("menu:sys:role:2", 1);
		for (Resources resources : admin) {
			System.out.println(resources);
		}
		
		Set<Resources> set = new HashSet<Resources>();
		set.addAll(test);
		set.addAll(admin);
		for (Resources resources : set) {
			System.out.println(resources);
		}
	}
	
}
