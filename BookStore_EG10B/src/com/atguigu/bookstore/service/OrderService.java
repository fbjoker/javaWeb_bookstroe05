package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {

	String  saveOrder(Cart cart, User user);

	List<Order> getAllOrder();

	List<Order> getOrderByUser(String orderId);

	int updateStatusByid(String orderId, int status);

	int saveOrderItem(OrderItem orderItem);

	List<OrderItem> getOrderItemsByuserId(String orderId);

}
