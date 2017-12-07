package com.iu.s2;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public String join() {
		return "member/memberJoin";
	}
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public String join(MemberDTO memberDTO, RedirectAttributes ra) {
		String message = "Fail";
		try {
			int result = memberService.insert(memberDTO);
			if(result > 0) 
				message = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:../";
	}
	
	@RequestMapping(value="memberLogin", method=RequestMethod.GET)
	public String login() {
		return "member/memberLogin";
	}
	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public String login(MemberDTO memberDTO, RedirectAttributes ra, HttpServletRequest request) {
		try {
			memberService.login(memberDTO, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../";
	}
	
	
	@RequestMapping(value="memberView")
	public String view() {
		return "";
	}
	
	@RequestMapping(value="memberUpdate")
	public String update() {
		return "";
	}
	
	@RequestMapping(value="memberWithdraw")
	public String withdraw() {
		return "";
	}


}
