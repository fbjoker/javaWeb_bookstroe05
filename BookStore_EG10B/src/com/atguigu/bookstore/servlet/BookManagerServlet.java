package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs = new BookServiceImpl();

	protected void bookList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> list = bs.getBookList();
		request.setAttribute("list", list);

		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	protected void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = WebUtils.parm2Bean(new Book(), request);
		bs.saveBook(book);
		System.out.println("add");
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?type=bookList");

	}

	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookId = request.getParameter("bookId");
		bs.deleteBook(bookId);

		String ref = request.getHeader("referer");
		response.sendRedirect(ref);

	}

	protected void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = WebUtils.parm2Bean(new Book(), request);

		boolean updateBook = bs.updateBook(book);
		String ref = request.getParameter("ref");
		response.sendRedirect(ref);

	}

	protected void findBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");

		Book book = bs.getBookById(bookId);

		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

	}
	
	
	protected void findPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size=4;
		Page<Book> page = bs.findPage(pageNumber, size);
		page.setPath(WebUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}

}
