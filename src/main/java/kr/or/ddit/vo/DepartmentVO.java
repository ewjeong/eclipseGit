package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DepartmentVO implements Serializable{
	private String dep_code;
	private String dep_name;
}
