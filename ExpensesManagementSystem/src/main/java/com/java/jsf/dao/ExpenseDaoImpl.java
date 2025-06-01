package com.java.jsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.jsf.model.Users;
import com.java.jsf.util.SessionHelper;

public class ExpenseDaoImpl implements ExpenseDao {

	SessionFactory sf;
	Session session;
	
	@Override
	public List<Users> showUsersDao() {
		sf = SessionHelper.getSession();
		session = sf.openSession();
		Query query = session.getNamedQuery("showUsers");
		List<Users> list = query.list();
		
		return list;
	}

	@Override
	public String addUsersDao(Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUsersDao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
