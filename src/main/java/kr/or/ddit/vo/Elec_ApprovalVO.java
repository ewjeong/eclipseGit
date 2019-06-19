package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.UpdateHint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of="elec_no")
@NoArgsConstructor
@Data
public class Elec_ApprovalVO implements Serializable {
	private Integer attach_no;
	@NotNull(groups=UpdateHint.class)
	private Integer elec_no;
	private String send_code;
	private String elec_writer;
	private String elec_form_code;
	private String elec_title;
	private String elec_content;
	private String elec_senddate;
	private String elec_comple;
	private String send_type_code;
	private String fix_line_no;
	
	// 삭제할 첨부파일의 번호들
	private int[] deleteFileNos; // 클라이언트 단에서 사용
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
	private SendVO send;
	private Elec_FormVO form;
	
	// 참조자
	private List<ReferenceVO> referenceList;
	
	// Elec_ApprlineVO와 has many 관계 형성
	@NotNull
	private List<Elec_ApprlineVO> apprLineList;
	
	private String appr_status_name;
}













