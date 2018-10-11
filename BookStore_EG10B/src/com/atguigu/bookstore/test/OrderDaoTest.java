package com.atguigu.bookstore.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

class OrderDaoTest {

	public static void main(String[] args) {
		 BookDao dao = new BookDaoImpl();
		 OrderDao orderDao = new OrderDaoImpl();
		 OrderItemDao orderItemDao = new OrderItemDaoImpl();
		//当用户点击去结账超链接时：购物车中已经有数据、如果能够处理结账请求必须先登录
				//1、表示层：获取数据交给业务层处理
				//用户对象
				User user = new User(1 , "admin" , "123456" , "adssadsad");
				//有数据的购物车对象
				Cart cart = new Cart();
				String bookId = "6";
				Book book = dao.getBookById(bookId);
				cart.addBook(book);
				cart.addBook(book);
				cart.addBook(book);
				//2、业务层：  将购物车和购物项转为订单和订单项
				//创建订单对象
				int state = 0;//用户购买时 默认状态是未发货
				Date createTime = new Date();//用户结账时间就是订单创建的时间
				String orderId = System.currentTimeMillis()+""+user.getId();//订单id： 自己创建一个唯一的字符串
				Order order = new Order(orderId,  cart.getTotalCount(),cart.getTotalAmount(), state, createTime, user.getId());
				
				//3、dao层：  将数据保存到数据库
				int saveOrder = orderDao.saveOrder(order);
				System.out.println(saveOrder);
				List<CartItem> list = cart.getMap2list();
				for (CartItem cartItem : list) {
					Book b = cartItem.getBook();
					//将购物项转为订单项
					OrderItem orderItem = new OrderItem(null, b.getTitle(), b.getAuthor(), b.getImgPath(), b.getPrice(),  cartItem.getCount(),cartItem.getAmount(), orderId);
					//保存到orderitem表中
					orderItemDao.saveOrderItem(orderItem);
					
					}
	
	

		
		
		
		
		
	}

}
