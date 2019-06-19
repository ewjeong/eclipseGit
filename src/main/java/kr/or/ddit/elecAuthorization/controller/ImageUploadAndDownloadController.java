package kr.or.ddit.elecAuthorization.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.elecAuthorization.service.IApprovalService;
import kr.or.ddit.vo.AttachmentVO;

@Controller
public class ImageUploadAndDownloadController {
	@Inject
	IApprovalService service;
	
	@Value("#{appInfo['elecImages']}")
	String imageFolderURL;
	
	File saveFolder;
	
	@Inject
	WebApplicationContext container;
	
	@PostConstruct // ApprovalServiceImpl 생성 -> 생성자 호출 -> imageFolderURL 생성된 이후에 실행됨
	public void init(){
		String realPath = container.getServletContext().getRealPath(imageFolderURL);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	// 업로드 되고 있는 파일을 elecImages 에 저장. 저장 끝났다는 응답 데이터를 json으로 내보내기  
	// ckeditor 를 이용한 이미지 업로드
	@RequestMapping(value="/elecAuthorization/imageUpload", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> imageUpload(
			@RequestPart(name="upload") MultipartFile fileItem
			) throws IOException {
		
		Map<String, String> jsonRespMap = new LinkedHashMap<>();
		String savename = UUID.randomUUID().toString();
		
		String url = container.getServletContext().getContextPath() + imageFolderURL + "/" + savename;
		try(
				InputStream is = fileItem.getInputStream();
		){
			FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
			// 복사가 성공했을 때...
			jsonRespMap.put("fileName", fileItem.getName());
			jsonRespMap.put("uploaded", 1+"");
			jsonRespMap.put("url", url);
		}
		return jsonRespMap;
	}
	// 첨부파일 다운로드
	@RequestMapping(value="/elecAuthorization/download")
	public String downloadFile(
			@RequestParam(name="what") String attach_noStr
			, @RequestHeader(name="User-Agent") String userAgent
			, HttpServletResponse resp
			) throws IOException {
		int attach_no = Integer.parseInt(attach_noStr);
		AttachmentVO attach = service.downloadFile(attach_no);
		
		if(attach != null){
			String savePath = attach.getAttach_path();
			File file = new File(savePath);
			
			if(!file.exists()){
				resp.sendError(404); // 파일을 찾지 못할 경우
				return null;
			}
			
			String fileName = null;
			
			// https://m.blog.naver.com/PostView.nhn?blogId=dkdnblack&logNo=220061711815&proxyReferer=https%3A%2F%2Fwww.google.com%2F 참고.
			boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
			 if (ie) {
				 fileName = URLEncoder.encode(attach.getAttach_orgname(), "utf-8"); // 다운로드할 파일명 한글 처리
			 } else {
				 fileName = new String(attach.getAttach_orgname().getBytes(), "ISO-8859-1"); // 각 브라우저마다 한글 처리를 하기 위해., firefox에서도 한글 처리 가능해짐. 근데 익스플로러는 한글처리 깨짐
			 }
			resp.setContentType("application/octet-stream");
			
			// 첨부파일이니까 저장을 해야한다고 header를 통해 브라우저에게 알려주기
			// 2번째 파라미터 : attachment : 첨부파일 (inline이면 바로 화면에 출력) ; filename : 부가정보 셋팅. 안해주면 .do 라는 확장자로 다운로드 됨
			resp.setHeader("Content-Disposition", "attachment;filename=\"" +fileName+ "\";"); // \" : 공백 처리 - 토큰 구분 가능해짐
			
			try (
					OutputStream os = resp.getOutputStream();
					InputStream is = new FileInputStream(file);
			){
				IOUtils.copy(is, os);
			}
		}
		return null;
	}
}



























