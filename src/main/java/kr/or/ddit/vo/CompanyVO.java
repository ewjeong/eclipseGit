package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyVO implements Serializable{
	
	private String com_no;
	private String com_name;
	private String com_tel;
	private String com_add1;
	private String com_add2;
	
}
