package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class OrderServlet extends BaseServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	OrderService os=new OrderServiceImpl();
	
	protected void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		
		
		if(user==null) {
			//通过request域共享数据，设置一个错误消息
			request.setAttribute("errorMsg", "用户未登录！");
			//查询失败，登录失败 ， 转发到登录页面让用户继续登录
			//转发由服务器解析，绝对路径基准地址到项目名
			//转发特点： 一次请求，地址栏地址不变，打开的页面和url地址不对应 ， 转发会造成转发后的页面中的相对路径失效
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			//解决相对路径失效的问题
		}else {
			
			String orderId = os.saveOrder(cart,user);
			
			System.out.println(orderId);
			request.setAttribute("orderId", orderId);
			
			request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
			
		}
		
	}
	
	
	protected void getAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> list = os.getAllOrder();
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");
	}
	
	protected void getOrderByUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		List<Order> list = os.getOrderByUser(user.getId()+"");
		session.setAttribute("list", list);
	}

}
