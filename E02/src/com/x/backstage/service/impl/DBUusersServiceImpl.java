package com.x.backstage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.x.backstage.mapper.DBUsersMapper;
import com.x.backstage.pojo.DBUsers;
import com.x.backstage.service.DBUsersService;
@Service()
public class DBUusersServiceImpl implements DBUsersService {
	@Autowired
	private DBUsersMapper mapper;
	 
	public DBUsers getDBUsersquery() {
		return mapper.getquery();
	}

}
