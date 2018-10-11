package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

public interface OrderDao {
	
	int saveOrder(Order order);
	List<Order> getAllOrder();
	List<Order> getOrderByUser(String  orderId);
	int updateStatusByid(String orderId,int status);
}
