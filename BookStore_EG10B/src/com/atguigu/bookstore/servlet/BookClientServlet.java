package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs = new BookServiceImpl();
	protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size=4;
		
		
		Page<Book> page = bs.findPage(pageNumber, size);
		System.out.println(page);
		page.setPath(WebUtils.getPath(request));
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
		
	}
	
	protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		
		System.out.println(pageNumber);
		int size=4;
		Page<Book> page = bs.findPageByPrice(minPrice, maxPrice, pageNumber, size);
		page.setPath(WebUtils.getPath(request));
		System.out.println(page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
		
	}


}
