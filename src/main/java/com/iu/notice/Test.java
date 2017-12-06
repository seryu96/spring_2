package com.iu.notice;

import java.sql.Connection;

import com.iu.util.DBConnector;

public class Test {

	public static void main(String[] args) {
		Connection con;
		try {
			con = DBConnector.getConnect();
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
