package com.iu.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.iu.util.DBConnector;

@Repository
public class FileDAO {
	
	public List<FileDTO> selectList(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from upload where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		
		List<FileDTO> ar = new ArrayList<FileDTO>();
		FileDTO fileDTO = null;
		
		while(rs.next()) {
			fileDTO = new FileDTO();
			fileDTO.setNum(rs.getInt("num"));
			fileDTO.setFnum(rs.getInt("fnum"));
			fileDTO.setFname(rs.getString("fname"));
			fileDTO.setOname(rs.getString("oname"));
			ar.add(fileDTO);
		}
		DBConnector.disConnect(rs, st, con);
		
		return ar;
	}
	
	public String selectOne(int fnum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select fname from upload where fnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, fnum);
		ResultSet rs = st.executeQuery();
		rs.next();
		String fname = rs.getString(1);
		DBConnector.disConnect(rs, st, con);
		
		return fname;
	}
	
	
	public int insert(FileDTO fileDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into upload values (board_seq.nextval, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, fileDTO.getNum());
		st.setString(2, fileDTO.getFname());
		st.setString(3, fileDTO.getOname());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete from upload where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
	
	public int deleteOne(int fnum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete from upload where fnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, fnum);
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
	}
}
