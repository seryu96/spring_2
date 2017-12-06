package com.iu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {

	public static Connection getConnect() throws Exception {
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@192.168.20.52:1521:xe";		//해당 서버가 있는 URL주소
		String driver = "oracle.jdbc.driver.OracleDriver";			//OracleDriver를 객체로 만들어서 사용하겠다.

		Class.forName(driver);										//driver를 string으로 만들어버리는 것.

		Connection con = DriverManager.getConnection(url, user, password);

		return con;
	}


	public static void disConnect(ResultSet rs, PreparedStatement st , Connection con) throws Exception {
		rs.close();
		st.close();
		con.close();
	}

	public static void disConnect(PreparedStatement st , Connection con) throws Exception {
		st.close();
		con.close();
	}
}

