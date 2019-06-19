package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DepNameVO implements Serializable{

	private String emp_name;
	private String dep_name;
	private String dep_code;
	private String emp_id;
}
