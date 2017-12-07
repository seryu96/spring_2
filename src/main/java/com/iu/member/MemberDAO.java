package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.iu.util.DBConnector;

@Repository
public class MemberDAO {
	
	public MemberDTO selectOne(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=? and job=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getJob());
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			memberDTO.setName(rs.getString("name"));
			memberDTO.setPhone(rs.getString("phone"));
			memberDTO.setAge(rs.getInt("age"));
		} else {
			memberDTO = null;
		}
		DBConnector.disConnect(rs, st, con);
		
		return memberDTO;
	}
	
	public int insert(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values (?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getPhone());
		st.setInt(5, memberDTO.getAge());
		st.setString(6, memberDTO.getJob());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public int update(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update member set pw=?, name=?, age=?, phone=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setInt(3, memberDTO.getAge());
		st.setString(4, memberDTO.getPhone());
		st.setString(5, memberDTO.getId());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public int delete(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());

		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public boolean idCheck(String id) throws Exception {
		boolean check = true;
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			check = false;
		}
		DBConnector.disConnect(rs, st, con);
		
		return check;
	}
	
	public boolean pwCheck(String id, String pw) throws Exception {
		boolean check = false;
		Connection con = DBConnector.getConnect();
		String sql = "select pw from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		if(rs.getString("pw").equals(pw)) {
			check = true;
		}
		
		return check;
	}
	
}
