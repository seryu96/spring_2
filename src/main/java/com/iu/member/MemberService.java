package com.iu.member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Inject
	private MemberDAO memberDAO;
	
	public int insert(MemberDTO memberDTO) throws Exception {
		int result = memberDAO.insert(memberDTO);
		return result;
	}
	
	public void login(MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		memberDTO = memberDAO.selectOne(memberDTO);
		
		if(memberDTO != null) {
			session.setAttribute("member", memberDTO);
		}
	}
	
	
}
