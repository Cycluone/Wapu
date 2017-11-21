package com.x.backstage.mapper;

import com.x.backstage.pojo.DbUsers;

public interface DBUsersMapper {
	public DbUsers selectByPrimaryKey();

	int deleteByPrimaryKey(Integer id);

	int insert(DbUsers record);

	int insertSelective(DbUsers record);

	DbUsers selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DbUsers record);

	int updateByPrimaryKey(DbUsers record);

}
