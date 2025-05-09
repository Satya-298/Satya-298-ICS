package com.java.lib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.LibAdmin;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TransReturn;
import com.java.lib.util.ConnectionHelper;
import com.java.lib.util.EncryptPassword;

public class LibraryDaoImpl implements LibraryDao {
	
	Connection conn;
	PreparedStatement pstmt;

	@Override
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {

		String encr = EncryptPassword.getCode(libUsers.getPassWord());
		conn = ConnectionHelper.getConnection();
		String cmd = "Insert into LibUsers(UserName, Password) values (?,?)";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, libUsers.getUserName());
		pstmt.setString(2, encr);
		pstmt.executeUpdate();
		
		return "User Account Created Successfully";
	}

	@Override
	public int login(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassWord());
		conn = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from LibUsers where UserName = ? AND " + "Password = ?";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, libUsers.getUserName());
		pstmt.setString(2, encr);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	@Override
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String cmd;
		boolean isValid=true;
		if(searchType.equals("id")) {
			cmd = " SELECT * FROM Books WHERE Id = ? " ;
		} else if (searchType.equals("bookname")) {
			cmd = " SELECT * FROM Books Where Name = ?";
		} else if (searchType.equals("authorname")) {
			cmd = "SELECT * FROM Books where Author = ?";
		} else if (searchType.equals("dept")) {
			cmd = "select * from Books where Dept = ?";
		} else {
			isValid=false;
			cmd = "select * from Books";
		}
		
		conn=ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(cmd);
		if (isValid == true) {
			pstmt.setString(1, searchValue);
		}
		ResultSet rs = pstmt.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}
	

	public int issueOrNot(String userName, int bookId) throws ClassNotFoundException, SQLException {
		conn=ConnectionHelper.getConnection();
		String sql = "select count(*) cnt from TranBook where UserName=? and BookId=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setInt(2, bookId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	@Override
	public String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		int count = issueOrNot(userName,bookId);
		if(count==0) {
			conn=ConnectionHelper.getConnection();
			String sql = "Insert into TranBook(UserName,BookId) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			sql = "Update Books set TotalBooks=TotalBooks-1 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.executeUpdate();
			return "Book with Id" +bookId + "Issued Successfully...";
		} else {
			return "Book Id " +bookId+ "for User " +userName+ "Already Issued...";
		}
	}

	@Override
	public List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException {
		conn=ConnectionHelper.getConnection();
		String cmd = "select * from tranbook where username = ?";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, userName);
		ResultSet rs = pstmt.executeQuery();
		List<TranBook> booksIssued = new ArrayList<TranBook>();
		TranBook tranBook = null;
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(rs.getString("UserName"));
			tranBook.setFromDate(rs.getDate("FromDate"));
			booksIssued.add(tranBook);
		}
		
		return booksIssued;
	}

	@Override
	public String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		String cmd = "SELECT * FROM TranBook WHERE Username = ? and BookId = ?";
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, userName);
		pstmt.setInt(2, bookId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		Date fromDate = rs.getDate("fromDate");
		
		String sql2 = " INSERT INTO TransReturn(UserName,BookId,FromDate) VALUES (?,?,?)" ;
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1,userName);
		pstmt.setInt(2,bookId);
		pstmt.setDate(3,fromDate);
		pstmt.executeUpdate();
		
		String sql1 = "DELETE FROM TranBook WHERE BookId = ? AND Username = ? " ;
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1,bookId);
		pstmt.setString(2,userName);
		pstmt.executeUpdate();
		
		String sql3 = "Update Books set TotalBooks = TotalBooks + 1 where Id = ?";
		pstmt = conn.prepareStatement(sql3);
		pstmt.setInt(1, bookId);
		pstmt.executeUpdate();
		return "Book with Id " +bookId + " For User " +userName + " Returned Successfully...";
	}

	@Override
	public List<TransReturn> history(String name) throws ClassNotFoundException, SQLException {
		conn = ConnectionHelper.getConnection();
		String cmd = "Select * from TransReturn Where Username = ?";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		TransReturn transreturn = null;
		List<TransReturn> trans = new ArrayList<TransReturn>();
		while(rs.next()) {
			transreturn = new TransReturn();
			transreturn.setUserName(rs.getString("userName"));
			transreturn.setBookId(rs.getInt("bookId"));
			transreturn.setFromDate(rs.getDate("fromDate"));
			transreturn.setToDate(rs.getDate("toDate").toLocalDate());
			trans.add(transreturn);
		}
		return trans;
	}
	
	@Override
	public String createAdmin(LibAdmin libAdmin) throws ClassNotFoundException, SQLException {

		String encr = EncryptPassword.getCode(libAdmin.getPassWord());
		conn = ConnectionHelper.getConnection();
		String cmd = "Insert into LibAdmin(AdminName, Password) values (?,?)";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, libAdmin.getAdminName());
		pstmt.setString(2, encr);
		pstmt.executeUpdate();
		
		return "Admin Account Created Successfully";
	}
	
	@Override
	public int adminlogin(LibAdmin libAdmin) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libAdmin.getPassWord());
		conn = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from LibAdmin where AdminName = ? AND " + "Password = ? ";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, libAdmin.getAdminName());
		pstmt.setString(2, encr);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	public boolean isBookName(String bookName) throws ClassNotFoundException, SQLException {
		conn = ConnectionHelper.getConnection();
		String cmd = "Select count(*) cnt from Books where Name = ?";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, bookName);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		if (rs.next()) {
			return count > 0;
		}
		return false;	
	}
	@Override
	public String addBook(Books book) throws ClassNotFoundException, SQLException {
		conn=ConnectionHelper.getConnection();
		if(isBookName(book.getName())) {
			String cmd = "UPDATE Books SET Author = ?, Edition = ?, Dept = ?, TotalBooks = ? WHERE Name = ?";
			pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, book.getAuthor());
			pstmt.setString(2, book.getEdition());
			pstmt.setString(3, book.getDept());
			pstmt.setInt(4, book.getNoOfBooks());
			pstmt.setString(5, book.getName());
			pstmt.executeUpdate();
			return "Book '" + book.getName() + "' updated successfully.";

		} else {
		
			String cmd = "INSERT INTO Books (Name, Author, Edition, Dept, TotalBooks) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getEdition());
			pstmt.setString(4, book.getDept());
			pstmt.setInt(5, book.getNoOfBooks());
			pstmt.executeUpdate();
			return "Book '" + book.getName() + "' added successfully.";
		}
	}
	
	

	@Override
	public int overdueCheckFine(Date issueDate) {
	     Date currentDate = new Date(System.currentTimeMillis()); // Get current date
		        
	     // Calculate the number of days between issueDate and currentDate
	     long millisBetween = currentDate.getTime() - issueDate.getTime();
	     long daysBetween = millisBetween / (1000 * 60 * 60 * 24); // Convert millis to days
	        
	     int fine = 0;

	     // If the book is overdue (more than 21 days), calculate the fine
		 if (daysBetween > 21) {
		     fine = (int) (daysBetween - 21) * 5; // Rs. 5 fine for each day after 21 days
		 }
		 return fine;
    }

	// Method to fetch transaction details based on book ID
	public TranBook getTransactionDetails(int bookId) {
		 TranBook tranBook = null;
		 try {
		      Connection conn = Database.getConnection(); // Assumed database connection
		      String query = "SELECT * FROM book_transactions WHERE bookId = ?";
		      PreparedStatement stmt = conn.prepareStatement(query);
		      stmt.setInt(1, bookId);
		      ResultSet rs = stmt.executeQuery();
		         
		      if (rs.next()) {
		          tranBook = new TranBook();
		          tranBook.setBookId(rs.getInt("bookId"));
		          tranBook.setFromDate(rs.getDate("fromDate"));
		          tranBook.setUserName(rs.getString("userName"));
		      }
		  } catch (SQLException e) {
		          e.printStackTrace();
		  }
		  return tranBook;
		  }

		    // Method to apply fine when the book is returned
		    public void applyFine(String userName, int fineAmount) {
		        try {
		            Connection conn = Database.getConnection(); // Assumed database connection
		            String query = "UPDATE users SET fine_balance = fine_balance + ? WHERE userName = ?";
		            PreparedStatement stmt = conn.prepareStatement(query);
		            stmt.setInt(1, fineAmount);
		            stmt.setString(2, userName);
		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    // Method to update transaction when the book is returned
		    public void updateBookReturn(TranBook tranBook) {
		        try {
		            Connection conn = Database.getConnection(); // Assumed database connection
		            String query = "UPDATE book_transactions SET returned = 1 WHERE bookId = ? AND userName = ?";
		            PreparedStatement stmt = conn.prepareStatement(query);
		            stmt.setInt(1, tranBook.getBookId());
		            stmt.setString(2, tranBook.getUserName());
		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	
		
		return null;
	}
	
	
}