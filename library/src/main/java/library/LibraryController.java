package library;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list")
public class LibraryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    
    public void init() {
        String URL = "jdbc:mysql://localhost:3306/library";
        String Username = "root";
        String Password = "123456";
        bookDAO = new BookDAO(URL, Username, Password);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                editBook(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Book> listBook = bookDAO.ListAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Library.jsp");
		dispatcher.forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Book book = new Book(id, title, author, category, quantity);
		bookDAO.updateBook(book);
		response.sendRedirect("list");
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.getBook(id);
		request.setAttribute("book", book);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = new Book(id);
		bookDAO.deleteBook(book);
		response.sendRedirect("list");
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Book book = new Book(title, author, category, quantity);
		bookDAO.insertBook(book);
		response.sendRedirect("list");
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
