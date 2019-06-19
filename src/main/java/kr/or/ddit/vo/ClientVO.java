package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.common.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 정다혜
 * @since 2019. 5. 24.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 24.      작성자명       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Data
public class ClientVO implements Serializable{
	
	private String cl_no;
	private String cl_empid;
	@NotBlank(groups=InsertHint.class)
	private String cl_name;
	private String cl_charger;
	@NotBlank(groups=InsertHint.class)
	private String cl_tel;
	@NotBlank(groups=InsertHint.class)
	private String cl_mail;
	private String cl_detail;
	private String cl_bank;
	private String cl_receive;
	private String cl_add1;
	private String cl_add2;
	private String cl_items;
	private String cl_bankno;
	private String cl_depository;
	private String cl_sort;
	private String cl_delete;
	private int cl_zip;
	
}
