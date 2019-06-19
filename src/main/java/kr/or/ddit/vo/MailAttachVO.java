package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MailAttachVO implements Serializable{
	
	private String filename;
	private String savepath;

}
