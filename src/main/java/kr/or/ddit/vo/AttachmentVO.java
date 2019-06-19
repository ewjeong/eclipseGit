package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AttachmentVO implements Serializable{
	
	// wrapper pattern : AttachmentVO attachment = new AttachmentVO(fileItem); 해주면 컨트롤러 단이 간단해 진다.
	public AttachmentVO(MultipartFile fileItem) {
		super();
		this.fileItem = fileItem;
		attach_orgname = fileItem.getOriginalFilename();
		attach_size = fileItem.getSize();
		attach_mime = fileItem.getContentType();
		attach_fancysize = FileUtils.byteCountToDisplaySize(fileItem.getSize());
	}

	private Integer attach_no;
	private Integer elec_no;
	private String attach_orgname;
	private String attach_path;
	private Long attach_size;
	private String attach_fancysize;
	private String attach_mime;
	
	private MultipartFile fileItem;
}
