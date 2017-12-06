package com.iu.qna;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.util.ListData;
import com.iu.util.Pager;
import com.iu.util.RowNum;

@Service
public class QnaService implements BoardService {

	@Inject
	private QnaDAO qnaDAO;
	
	public void setQnaDAO(QnaDAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}

	@Override
	public List<BoardDTO> selectList(ListData listData, Model model) throws Exception {
		RowNum rowNum = listData.makeRow();
		int totalCount = qnaDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
		model.addAttribute("pager", pager);
		model.addAttribute("list", qnaDAO.selectList(rowNum));
		
		return qnaDAO.selectList(rowNum);
	}

	@Override
	public BoardDTO selectOne(int num, Model model) throws Exception {
		qnaDAO.hitUpdate(num);
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO, HttpSession session) throws Exception {
		return qnaDAO.insert(boardDTO);
	}
	
	@Override
	public int update(BoardDTO boardDTO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
