package kr.or.ddit.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageVO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private CriteriaVO cri;
	
	
	public PageVO(CriteriaVO cri, int total){
		
		this.cri = cri;
		this.total = total;
		
		//페이징의 끝번호 계산
		//1페이지의 경우 math.ceil(0.1)*10 = 10
		//10페이지의 경우 math.ceil(1) * 10 = 10
		//11페이지의 경우 math.ceil(1.1) * 10 = 20
		this.endPage =(int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		//페이징의 시작번호 계산
		this.startPage = this.endPage -9;
		
		//total을 통한 endpage의 재계산
		//만일 끝번호(endpage)와 한 페이지당 출력되는 데이터수(amount)의 곱이 전체 데이터수(total)보다 크다면
		//끝 번호(endPage)는 다시 total을 이용해서 다시 계산되어야함
		int realEnd =(int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(realEnd<this.endPage){
			this.endPage=realEnd;
		}
		
		
		//이전과 다음은 간단함
		//이전의 경우는 시작번호(startpage)가 1보다 큰 경우라면 존재
		this.prev = this.startPage > 1;
		//다음으로 가는 링크는 realEnd가 끝 번호(endPage)보다 큰 경우 존재
		this.next = this.endPage < realEnd;
	}
}
