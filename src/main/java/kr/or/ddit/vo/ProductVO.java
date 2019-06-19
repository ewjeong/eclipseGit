package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(exclude={"prod_img", "prod_image"})
public class ProductVO implements Serializable{
	
	private Integer prod_qty;
	private Integer prod_no;
	private String item_code;
	private String item_name;
	private String prod_name;
	private String prod_size;
	private Integer prod_cost;
	private String prod_outline;
	private String prod_details;
	private String prod_color;
	private byte[] prod_img; // db에 저장될 프로퍼티
	private MultipartFile prod_image; // 클라이언트로부터 받기 위한 프로퍼티
	
	public void setProd_image(MultipartFile prod_image) throws IOException {
		this.prod_image = prod_image;
		if(prod_image != null 
				&& StringUtils.isNotBlank(prod_image.getOriginalFilename())){
			prod_img = prod_image.getBytes();
		}
	}
	
	public String getProd_imgBase64() {
		String base64Str = null;
		if(prod_img != null)
			// byte[] 배열을 base64 방식으로 인코딩 하기
			base64Str = Base64.getEncoder().encodeToString(prod_img);
		return base64Str;
	}
}










