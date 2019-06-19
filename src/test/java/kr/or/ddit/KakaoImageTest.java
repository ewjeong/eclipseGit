package kr.or.ddit;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import kr.or.ddit.vo.KakaoRespVO;
import kr.or.ddit.vo.KakaoRespVO.KakaoObj;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml"),
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
public class KakaoImageTest {
	@Test
	public void test() throws URISyntaxException, UnsupportedEncodingException{
		MultiValueMap<String, Resource> bodyMap = new LinkedMultiValueMap<>();
		Resource file = new FileSystemResource(new File("d:/product_demo1.jpg"));
		bodyMap.put("file", Collections.singletonList(file));
//		String image_url = "http://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/product_demo1.jpg";
		String url = "https://kapi.kakao.com/v1/vision/product/detect";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", "KakaoAK 1346ccbda98c8d1ea09ebbad757ca667");
		RequestEntity<MultiValueMap<String, Resource>> request = new RequestEntity<>(bodyMap, headers, HttpMethod.POST, new URI(url));
		KakaoRespVO result = restTemplate.exchange(request, KakaoRespVO.class).getBody();
		for(KakaoObj obj : result.getResult().getObjects()){
			System.out.println(obj.getProdName());
		}
	}
}
