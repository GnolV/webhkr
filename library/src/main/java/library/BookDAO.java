package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	private String url;
	private String username;
	private String password;
	private Connection connection;
	
	public BookDAO(String URL, String Username, String Password) {
        this.url = URL;
        this.username = Username;
        this.password = Password;
    }
	
	protected void connect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(url, username, password);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (connection != null || !connection.isClosed()) {
			connection.close();
		}
	}
	
	public boolean insertBook(Book book) throws SQLException {
		String sql = "insert into library.book (title, author, category, quantity) values (?,?,?,?)";
		connect();
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, book.getTitle());
		st.setString(2, book.getAuthor());
		st.setString(3, book.getCategory());
		st.setInt(4, book.getQuantity());
		boolean rowInsert = st.executeUpdate() > 0;
		st.close();
		disconnect();
		return rowInsert;
	}
	
	public List<Book> ListAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		String sql = "select * from library.book";
		connect();
		Statement st = connection.createStatement();
		ResultSet resSet = st.executeQuery(sql);
		while (resSet.next()) {
			int id = resSet.getInt("book_id");
			String title = resSet.getString("title");
			String author = resSet.getString("author");
			String category = resSet.getString("category");
			int quantity = resSet.getInt("quantity");
			Book book = new Book(id, title, author, category, quantity);
			listBook.add(book);
		}
		resSet.close();
		st.close();
		return listBook;
	}
	
	public boolean deleteBook(Book book) throws SQLException {
		String sql = "delete from library.book where book_id = ?";
		connect();
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, book.getId());
		boolean rowDelete = st.executeUpdate(sql) > 0;
		st.close();
		disconnect();
		return rowDelete;
	}
	
	public boolean updateBook(Book book) throws SQLException {
		String sql = "update library.book set title = ?, author = ?, category = ?, quantity = ? where book_id = ?";
		connect();
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, book.getTitle());
		st.setString(2, book.getAuthor());
		st.setString(3, book.getCategory());
		st.setInt(4, book.getQuantity());
		st.setInt(5, book.getId());
		boolean rowUpdate = st.executeUpdate() > 0;
		st.close();
		disconnect();
		return rowUpdate;
	}
	
	public Book getBook(int id) throws SQLException {
		Book book = null;
		String sql = "select * from library.book where book_id = ?";
		connect();
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet resSet = st.executeQuery();
		if (resSet.next()) {
			String title = resSet.getString("title");
			String author = resSet.getString("author");
			String category = resSet.getString("category");
			int quantity = resSet.getInt("quantity");
			book = new Book(id, title, author, category, quantity);
		}
		st.close();
		disconnect();
		return book;
	}
}
