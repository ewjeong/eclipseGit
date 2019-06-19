package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Appr_StatusVO implements Serializable{
	
	private String appr_status_code;
	private String appr_status_name;
	
}
