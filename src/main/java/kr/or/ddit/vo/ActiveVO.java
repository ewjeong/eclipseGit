package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 정다혜3
 * @since 2019. 5. 17.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 17.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class ActiveVO implements Serializable{
	private Integer ac_no;
	private String cl_no;
	private String emp_id;
	@NotEmpty(message="시작날짜는 필수 입력 값 입니다.")
	@JsonProperty("start")
	private String ac_startdate;
	@JsonProperty("title")
	private String ac_content;
	@NotEmpty(message="활동구분은 필수 입력 값 입니다.")
	private String ac_sort;
	@NotEmpty(message="종료날짜는 필수 입력 값 입니다.")
	@JsonProperty("end")
	private String ac_enddate;
	private String ac_delete;
	private String ac_location;
	
	@JsonProperty("color")
	private String getColor(){
		String color = null;
		//널포인트 신경쓰기 순서를 바꿔라
		if(("출장").equals(ac_sort)){
			color = "#A9F5BC";
		}else if(("세미나").equals(ac_sort)){
			color="#D8CEF6";
		}else{
			color="#F5A9E1";
		}
		return color;
	}

	
	
}
