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
	 
	public DbUsers getselectByPrimaryKey(int id) {
		return mapper.selectByPrimaryKey(id);
	}
}
