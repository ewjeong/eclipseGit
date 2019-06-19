package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientEmpVO extends ClientVO implements Serializable{

	private String emp_name;
}
