package com.java.jsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.jsf.model.Users;
import com.java.jsf.util.SessionHelper;

public class UsersDaoImpl implements UsersDao{

	SessionFactory sf;
	Session session;
	
	public List<Users> showUsersDao() {
	    sf = SessionHelper.getSession();
	    session = sf.openSession();
	    Query query = session.getNamedQuery("showUsers");
	    List<Users> list = query.list();
	    session.close();  
	    return list;
	}

	@Override
	public String deleteUsersDao(int id) {
	    sf = SessionHelper.getSession();
	    session = sf.openSession();
	    Transaction trans = session.beginTransaction();
	    Query query = session.getNamedQuery("deleteId");
	    query.setParameter("id", id);
	    query.executeUpdate();
	    trans.commit();
	    session.close();
	    return "User Deleted Successfully";
	}

	@Override
	public Users userLogin(String email, String password) {
	    sf = SessionHelper.getSession();
	    session = sf.openSession();
	    Query query = session.createQuery("from Users where email = :email and password = :password");
	    query.setParameter("email", email);
	    query.setParameter("password", password);
	    Users user = (Users) query.uniqueResult();
	    session.close();
	    return user;
	}


}
