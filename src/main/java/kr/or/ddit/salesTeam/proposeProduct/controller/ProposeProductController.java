package kr.or.ddit.salesTeam.proposeProduct.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.purchasingTeam.itemManage.service.IItemManageService;
import kr.or.ddit.purchasingTeam.itemManage.service.IProductManageService;
import kr.or.ddit.vo.KakaoRespVO;
import kr.or.ddit.vo.ProductVO;
import kr.or.ddit.vo.KakaoRespVO.KakaoObj;


@Controller
public class ProposeProductController {
	
	
	@Inject //서비스 
	private IItemManageService service;
	
	//restTemplate 클래스의 빈을 DI 받는다. RestTemplate 빈은 스프링 설정 파일에 빈으로 등록해야한다.
//	@Resource(name="restTemplate")
	protected RestTemplate restTemplate; 
	
	
	@RequestMapping(value="/purchasingTeam/itemManage/proposeProduct", method=RequestMethod.GET)
	public String getProposeProduct(){
		

		return "purchase/item/propose";
	}
	
	@RequestMapping(value="/purchasingTeam/itemManage/proposeProduct", method=RequestMethod.POST)
//	@ResponseBody
	public String postProposeProduct(
//				@RequestParam(name="image_url") String image_url
				@RequestPart("upload") MultipartFile uploadFile
				,@RequestParam(name="exist") String exist
				, Model model
			) throws URISyntaxException, IOException, MultipartException, FileUploadException{
		
		MultiValueMap<String, Resource> bodyMap = new LinkedMultiValueMap<>();
//		File random = new File(UUID.randomUUID().toString());
//		FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), random);
//		Resource file = new FileSystemResource(random);
		ByteArrayResource file = new ByteArrayResource(uploadFile.getBytes()){
			@Override
			public String getFilename() {
				return uploadFile.getOriginalFilename(); //데이터에 이름을 붙여줌
			}
		};
		
		bodyMap.put("file[0]", Collections.singletonList(file));
		String url = "https://kapi.kakao.com/v1/vision/product/detect"; //kakao에서 제공하는 URL 주소
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", "KakaoAK 1346ccbda98c8d1ea09ebbad757ca667");
		RequestEntity<MultiValueMap<String, Resource>> request 
			= new RequestEntity<>(bodyMap, headers, HttpMethod.POST, new URI(url));
		ResponseEntity<KakaoRespVO> respEntity = restTemplate.exchange(request, KakaoRespVO.class);
			//result는 결과 데이터 object 안에 좌표, prodName이 들어가있음
		KakaoRespVO result = respEntity.getBody();
		System.out.println(result);
		for(KakaoObj obj : result.getResult().getObjects()){
			System.out.println(obj.getProdName());
		}
//		FileUtils.deleteQuietly(random);
		
		model.addAttribute("result", result);
		model.addAttribute("exist", exist);
			
		return 
				"purchase/item/propose";
//				result;
	}
	
	@RequestMapping(value="/purchasingTeam/itemManage/proposeProductAjax", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<ProductVO> getAjax(
			@RequestParam("item_name") String item_name
//			@PathVariable("item_name") String item_name
	) throws MultipartException{
			List<ProductVO> productList = service.retrieveProductList(item_name);
			return productList;
	}
	
	
	

	
}
