package kr.or.ddit.mail.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailAttachDownload {
	
	
	@RequestMapping("/mail/down")
	public void download(@RequestParam(name="path",required = true)String path,
											@RequestParam(name="filename")String filename, HttpServletResponse resp) throws IOException {
		
		
		//다운받은 파일이름과 저장되는 파일명이 다르다
		//저장된 파일명을 한 번 인코딩해서 내보내야함..
		
		String sendingName = new String(filename.getBytes(),"ISO-8859-1");
		
		resp.setHeader("Content-Disposition", "attachment;filename= \""+sendingName+"\"");
		try(
				//파일이 저장된 실제 경로 메모리를 읽어들인다.
				InputStream is = new  FileInputStream(path+"/"+filename);
				//응답데이터 내보내줌
				OutputStream os = resp.getOutputStream();
		){
			IOUtils.copy(is, os);
		}
		
	}

}
