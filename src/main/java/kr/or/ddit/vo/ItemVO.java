package kr.or.ddit.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ItemVO implements Serializable{
	private String item_code; //품목코드
	private String top_item_code; //상위품목코드
	@NotBlank(groups=InsertHint.class)
	private String item_name; //품목이름
	private String item_delete; //삭제여부
	private String top_item_name; //상위이름
	
}
