package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MailVO implements Serializable{

	private String subject;
	private String from;
	private String Date;
	private Date receivedDate;
	private String id;
	private Object content;
	private List<MailAttachVO> attachList;
	private String to;
	private String bodytext;
	
}
