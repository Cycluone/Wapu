package com.x.backstage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.x.backstage.mapper.DBUsersMapper;
import com.x.backstage.pojo.DbUsers;
import com.x.backstage.service.DBUsersService;
@Service()
public class DBUusersServiceImpl implements DBUsersService {
	@Autowired
	private DBUsersMapper mapper;
	 
	public DbUsers operationSelectByPrimaryKey(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	public int operationDeleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int operationInsert(DbUsers record) {
		return mapper.insert(record);
	}

	public int operationInsertSelective(DbUsers record) {
		return mapper.insertSelective(record);
	}

	public int operationUpdateByPrimaryKey(DbUsers record) {
		return mapper.updateByPrimaryKey(record);
	}

	public int operationUpdateByPrimaryKeySelective(DbUsers record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
}
