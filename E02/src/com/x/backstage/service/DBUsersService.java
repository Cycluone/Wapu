package com.x.backstage.service;

import com.x.backstage.pojo.DbUsers;

public interface DBUsersService {
	public DbUsers operationSelectByPrimaryKey(int id);
	
	public int operationDeleteByPrimaryKey(Integer id);

	public int operationInsert(DbUsers record);

	public int operationInsertSelective(DbUsers record);

	public int operationUpdateByPrimaryKeySelective(DbUsers record);

	public int operationUpdateByPrimaryKey(DbUsers record);
	
	
	
}
