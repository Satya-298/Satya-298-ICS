package com.java.lib.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.LibAdmin;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TransReturn;

public interface LibraryDao {
	
	String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	
	public int login(LibUsers libUsers) throws ClassNotFoundException, SQLException;

	List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException;
	
	String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException;
	
	List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException;
	
	String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException;
	
	List<TransReturn> history(String name) throws ClassNotFoundException, SQLException;
	
	String createAdmin(LibAdmin libAdmin) throws ClassNotFoundException, SQLException;
	
	public int adminlogin(LibAdmin libAdmin) throws ClassNotFoundException, SQLException;
	
	String addBook(Books book) throws ClassNotFoundException, SQLException;
	
	int overdueCheckFine(Date issueDate);
}
