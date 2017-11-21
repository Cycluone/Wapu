package com.x.backstage.mapper;

import com.x.backstage.pojo.DbUsers;

public interface DBUsersMapper {
	public int deleteByPrimaryKey(Integer id);

	public int insert(DbUsers record);

	public int insertSelective(DbUsers record);

	public DbUsers selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(DbUsers record);

	public int updateByPrimaryKey(DbUsers record);

}
