package kr.or.ddit.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class KakaoRespVO {
	@Data
	public static class KakaoObj{
		private double x1;
		private double x2;
		private double y1;
		private double y2;
		@JsonProperty("class")
		private String prodName;
	}
	@Data
	public static class KakaoObjsWrapper{
		private Integer width;
		private Integer height;
		private KakaoObj[] objects;
		
	}
	
	private KakaoObjsWrapper result;
	
}
