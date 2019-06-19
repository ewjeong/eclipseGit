package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SendVO implements Serializable{
	
	private String send_code;
	private String send_name;
	
}
