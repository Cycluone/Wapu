package com.x.junitTest.mybatis;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.x.backstage.pojo.DbUsers;
import com.x.backstage.service.DBUsersService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class Test {
	@Autowired
	private DBUsersService dbservice;
	@org.junit.Test
	public void test1(){
		
		DbUsers record = new DbUsers(2,"dengshuxing",19,"��������","18868363026",2);
		//���
		int i = dbservice.operationInsert(record);
		System.out.println(i);
		
		
		
		//��ѯ
		//DbUsers users = dbservice.operationSelectByPrimaryKey(1);
		//System.out.println(users.getAddress());
	}

}
