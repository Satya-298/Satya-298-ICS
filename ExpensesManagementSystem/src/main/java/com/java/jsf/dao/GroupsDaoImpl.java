package com.java.jsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.jsf.model.Groups;
import com.java.jsf.model.Users;
import com.java.jsf.util.SessionHelper;

public class GroupsDaoImpl implements GroupsDao{

	SessionFactory sf;
	Session session;
	
	@Override
	public List<Groups> showGroupsDao() {
		sf = SessionHelper.getSession();
		session = sf.openSession();
		Query query = session.getNamedQuery("showGroups");
		List<Groups> list = query.list();
		
		return list;
	}

	@Override
	public String deleteGroupsDao(int id) {
		sf = SessionHelper.getSession();
		session = sf.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.getNamedQuery("deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
		trans.commit();
		
		return "Group Deleted Successfully";
	}

}
