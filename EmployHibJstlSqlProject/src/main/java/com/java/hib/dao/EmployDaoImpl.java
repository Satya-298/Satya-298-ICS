package com.java.hib.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.java.hib.model.Employ;

public class EmployDaoImpl implements EmployDao {
	
	SessionFactory sf;
	Session session;

	@Override
	public List<Employ> showEmployDao() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		Query query = session.createQuery("from Employ");
		List<Employ> employList = query.list();
		return employList;
	}

	@Override
	public Employ searchEmployDao(int empno) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		Query query = session.createQuery("from Employ where empno = " +empno);
		Employ employ = (Employ) query.uniqueResult();
		
		return employ;
	}

	@Override
	public String updateEmployDao(Employ employ) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		Transaction trans = session.beginTransaction();
		session.update(employ);
		trans.commit();
		
		return "Employ Record Updated";
	}

	@Override
	public String addEmployDao(Employ employ) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		Transaction trans = session.beginTransaction();
		session.save(employ);
		trans.commit();
		
		return "Employ Record Inserted";
	}

	@Override
	public String deleteEmployDao(int empno) {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		Employ employ = searchEmployDao(empno);
		Transaction trans = session.beginTransaction();
		session.delete(employ);
		trans.commit();
		
		return null;
	}

}
