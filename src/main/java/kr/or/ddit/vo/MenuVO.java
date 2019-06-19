package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuVO implements Serializable{
	
	private String menu_id;
	private String menu_name;
	private int menu_flag;

}
