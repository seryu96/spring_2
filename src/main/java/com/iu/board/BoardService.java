package com.iu.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.iu.util.ListData;

public interface BoardService {
	public List<BoardDTO> selectList(ListData listData, Model model) throws Exception;
	public BoardDTO selectOne(int num, Model model) throws Exception;
	public int insert(BoardDTO boardDTO, HttpSession session) throws Exception;
	public int update(BoardDTO boardDTO, HttpSession session) throws Exception;
	public int delete(int num, HttpSession session) throws Exception;
}
