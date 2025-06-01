package com.java.jsf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.jsf.model.GroupMembers;
import com.java.jsf.model.Groups;
import com.java.jsf.util.SessionHelper;

public class MembersDaoImpl implements MembersDao {

	SessionFactory sf;
	Session session;

	@Override
	public List<GroupMembers> showMembersDao() {

		sf = SessionHelper.getSession();
		session = sf.openSession();
		Query query = session.getNamedQuery("showMembers");
		List<GroupMembers> list = query.list();
		
		return list;
	}
}
