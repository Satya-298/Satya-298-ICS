package com.java.jsf.dao;

import java.util.List;

import com.java.jsf.model.Groups;

public interface GroupsDao {
	
	List<Groups> showGroupsDao();
	String deleteGroupsDao(int id);

}
