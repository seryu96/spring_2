package com.iu.board;

import java.util.List;

import com.iu.util.RowNum;

public interface BoardDAO {
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception;
	public BoardDTO selectOne(int num) throws Exception;
	public int insert(BoardDTO boardDTO) throws Exception;	
	public int update(BoardDTO boardDTO) throws Exception;
	public int delete(int num) throws Exception;
	public int getTotalCount(RowNum rowNum) throws Exception;
	public int hitUpdate(int num) throws Exception;
}
