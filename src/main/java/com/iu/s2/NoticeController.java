package com.iu.s2;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	// @Autowired
	@Inject
	private NoticeService noticeService;

	@RequestMapping(value="noticeList")
	public String list(Model model, ListData listData) {
		try {
			noticeService.selectList(listData, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("board", "notice");

		return "board/boardList";
	}

	@RequestMapping(value="noticeView")
	public String view(@RequestParam(defaultValue="0", required=false) int num, Model model) {
		BoardDTO noticeDTO = null;
		try {
			noticeDTO = noticeService.selectOne(num, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("view", noticeDTO);
		model.addAttribute("board", "notice");

		return "board/boardView";
	}

	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}

	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String write(RedirectAttributes ra, NoticeDTO boardDTO, HttpSession session) {
		int result = 0;
		System.out.println("title : "+boardDTO.getTitle());
		System.out.println("writer : "+boardDTO.getWriter());
		System.out.println("contents : "+boardDTO.getContents());
		try {
			result = noticeService.insert(boardDTO, session);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String message = "FAIL";
		if(result > 0) {
			message = "SUCCESS";
		}

		ra.addFlashAttribute("message", message);
		return "redirect:./noticeList";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String update(Model model, int num) {
		BoardDTO boardDTO = null;
		try {
			boardDTO = noticeService.selectOne(num, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("view", boardDTO);
		model.addAttribute("board", "notice");
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String update(RedirectAttributes ra, NoticeDTO noticeDTO, HttpSession session) {
		String message = "Fail";
		try {
			int result = noticeService.update(noticeDTO, session);
			if(result > 0) 
				message = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ra.addFlashAttribute("message", message);
		return "redirect:noticeList";
	}
	
	@RequestMapping(value="noticeDelete")
	public String delete(int num, RedirectAttributes ra, HttpSession session) {
		int result = 0;
		
		try {
			result = noticeService.delete(num, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = "FAIL";
		if(result > 0)
			message = "SUCCESS";
		ra.addFlashAttribute(message);
		
		return "redirect:./noticeList";
	}
}

