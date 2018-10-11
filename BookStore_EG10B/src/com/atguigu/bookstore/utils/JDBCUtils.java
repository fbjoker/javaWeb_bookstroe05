package com.atguigu.bookstore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 管理数据库连接的工具类
 * 	获取连接和释放连接
 * @author Administrator
 *
 */
public class JDBCUtils {
	//一个项目使用一个数据库连接池
	private static DataSource source;
	//通过静态代码块初始化数据库连接池
	static {
		//准备数据库连接池需要的参数
		Properties properties = new Properties();
		//加载properties文件到内存中
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
			source = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 释放连接的方法
	 */
	public static void closeConn(Connection conn ) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
