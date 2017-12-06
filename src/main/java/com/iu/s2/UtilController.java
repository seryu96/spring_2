package com.iu.s2;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.file.FileService;
import com.iu.file.PhotoDTO;
import com.iu.util.FileSaver;

@Controller
@RequestMapping(value="/util/*")
public class UtilController {
	
	@RequestMapping(value="download")
	public ModelAndView fileDownload(String filename, String oriname, HttpSession session) {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath, filename);
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("oriname", oriname);
		mv.addObject("download", file);
		mv.setViewName("filedownload");
		
		return mv;
	}
	
	@Inject
	private FileService fileService;
	
	@RequestMapping(value="fileDelete")
	public String delete(int fnum, HttpSession session, Model model) {
		String message = "Fail"; 
		try {
			int result = fileService.fileDelete(fnum, session);
			if(result > 0) 
				message = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(message);
		
		return "common/result";
	}
	
	@RequestMapping(value="photoUpload", method=RequestMethod.POST)
	public String smartEditor(PhotoDTO photoDTO, HttpSession session) {
		// service
		String filePath = session.getServletContext().getRealPath("/");
		filePath = filePath + "resources" + File.separator + "upload"; 
		File file = new File(filePath);
		System.out.println(filePath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		FileSaver fs = new FileSaver();
		String fileName = "";
		// String result = ""; 
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			fileName = fs.save2(filePath, photoDTO.getFiledata());
			// result = "&bNewLine=true&sFileName="+photoDTO.getFiledata().getOriginalFilename()+"&sFileURL="+session.getServletContext().getContextPath()+File.separator+"upload"+File.separator+fileName;
			stringBuffer.append("&bNewLine=true&sFileName=");
			stringBuffer.append(photoDTO.getFiledata().getOriginalFilename());
			stringBuffer.append("&sFileURL=");
			stringBuffer.append(session.getServletContext().getContextPath());
			stringBuffer.append("/resources/");
			stringBuffer.append("upload");
			stringBuffer.append("/");
			stringBuffer.append(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(photoDTO.getCallback()+photoDTO.getCallback_func()+stringBuffer.toString());
		
		return "redirect:"+photoDTO.getCallback()+photoDTO.getCallback_func()+stringBuffer.toString();
	}
}
