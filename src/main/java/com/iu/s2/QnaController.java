package com.iu.s2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iu.board.BoardDTO;
import com.iu.qna.QnaService;
import com.iu.util.ListData;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {
	
	@Inject
	private QnaService qnaService;
	
	@RequestMapping(value="qnaList")
	public String list(Model model, ListData listData) {
		try {
			qnaService.selectList(listData, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("board", "qna");
		
		return "board/boardList";
	}
	
	@RequestMapping(value="qnaView")
	public String view(int num, Model model) {
		BoardDTO qnaDTO = null;
		try {
			qnaDTO = qnaService.selectOne(num, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("view", qnaDTO);
		model.addAttribute("board", "qna");
		
		return "board/boardView";
	}
	
	@RequestMapping(value="qnaWrite")
	public String write(Model model) {
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
	public String write(Model model, BoardDTO boardDTO, HttpSession session) {
		boardDTO.setTitle(boardDTO.getTitle());
		boardDTO.setWriter(boardDTO.getWriter());
		boardDTO.setContents(boardDTO.getContents());
		
		try {
			qnaService.insert(boardDTO, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("board", "qna");
		return "redirect:qnaList";
	}
}
