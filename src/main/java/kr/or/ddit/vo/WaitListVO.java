package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WaitListVO implements Serializable{
	private Integer attach_no;
	private Integer elec_priority;
	private String authorized_id;
	private String instead_id;
	private Integer elec_no;
	private String send_code;
	private String elec_writer;
	private String elec_form_code;
	private String elec_title;
	private String elec_content;
	private String elec_senddate;
	private String elec_deadline;
	private String elec_comple;
	private String send_type_code;
	private String appr_status_code;
	private String appr_status_name;
	
	private Elec_FormVO form;
	
	private List<Elec_ApprlineVO> apprLineList;
	
	// 삭제된 첨부파일의 번호들
	private int[] deleteFileNos;
	// 첨부파일 has many 관계 설정
	private List<AttachmentVO> fileList;
	// 실제 이미지를 저장할 프로퍼티
	private MultipartFile[] elec_files;
	
	// 파일 셋팅
	public void setElec_files(MultipartFile[] elec_files) {
		if(elec_files == null ) return;
		this.elec_files = elec_files;
		
		this.fileList = new ArrayList<>();
		for(MultipartFile file : elec_files){
			if(StringUtils.isBlank(file.getOriginalFilename())) continue;
			fileList.add(new AttachmentVO(file));
		}
	}
}
