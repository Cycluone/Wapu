package com.x.junitTest.mybatis;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.x.backstage.pojo.DBUsers;
import com.x.backstage.service.DBUsersService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class Test {
	@Autowired
	private DBUsersService dbservice;
	@org.junit.Test
	public void test1(){
		DBUsers users = dbservice.getDBUsersquery();
		System.out.println(users.getAddress());
	}

}
