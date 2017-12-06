package com.iu.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.Pager;
import com.iu.util.RowNum;

//@Component
@Service
public class NoticeService implements BoardService {
	
	// @Autowired
	@Inject
	private NoticeDAO noticeDAO;
	
	@Inject
	private FileDAO fileDAO;
	
	@Inject
	private FileSaver fileSaver;
	
	@Override
	public List<BoardDTO> selectList(ListData listData, Model model) throws Exception {
		RowNum rowNum = listData.makeRow();
		int totalCount = noticeDAO.getTotalCount(rowNum);
		Pager pager = listData.makePage(totalCount);
		model.addAttribute("pager", pager);
		model.addAttribute("list", noticeDAO.selectList(rowNum));
		
		return noticeDAO.selectList(rowNum);
	}
	
	@Override
	public BoardDTO selectOne(@RequestParam(defaultValue="0", required=false) int num, Model model) throws Exception {
		noticeDAO.hitUpdate(num);
		NoticeDTO noticeDTO = (NoticeDTO)noticeDAO.selectOne(num);
		List<FileDTO> ar = fileDAO.selectList(num);
		noticeDTO.setAr(ar);
		return noticeDTO;
	}
	
	
	@Override
	public int insert(BoardDTO boardDTO, HttpSession session) throws Exception {
		int num = noticeDAO.getNum();
		String filePath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> names = new ArrayList<FileDTO>();
		fileSaver = new FileSaver();
		FileDTO fileDTO = null;
		
		for(MultipartFile f : ((NoticeDTO)boardDTO).getF1()) {
			fileDTO = new FileDTO();
			fileDTO.setFname(fileSaver.save2(filePath, f));
			fileDTO.setOname(f.getOriginalFilename());
			fileDTO.setNum(num);
			names.add(fileDTO);
			fileDAO.insert(fileDTO);
		}
		boardDTO.setNum(num);
		
		return noticeDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO, HttpSession session) throws Exception {
		int result = noticeDAO.update(boardDTO);
		MultipartFile[] ar = ((NoticeDTO)boardDTO).getF1();
		for(MultipartFile f : ar) {
			FileDTO fileDTO = new FileDTO();
			fileSaver = new FileSaver();
			
			String filePath = session.getServletContext().getRealPath("resources/upload");
			String fileName = fileSaver.save2(filePath, f);
			
			fileDTO.setNum(boardDTO.getNum());
			fileDTO.setFname(fileName);
			fileDTO.setOname(f.getOriginalFilename());
			fileDAO.insert(fileDTO);
		}
		
		return result;
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		int result = noticeDAO.delete(num);
		List<FileDTO> ar = fileDAO.selectList(num);
		fileDAO.delete(num);
		
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		for(FileDTO fileDTO : ar) {
			File file = new File(filePath, fileDTO.getFname());
			file.delete();
		}
		
		return result;
	}

}
