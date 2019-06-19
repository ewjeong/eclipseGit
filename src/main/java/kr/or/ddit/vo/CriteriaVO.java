package kr.or.ddit.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class CriteriaVO<T> {

	//페이지번호
	private int pageNum;
	//페이지당 보여지는 데이터의 갯수
	private int amount;
	
	private String type;
	private String keyword;
	private String date;

	 // 페이징 처리가 된 목록을 저장할 변수
	   private List<T> dataList; // pagingVO를 범용적으로 사용하기 위해 List<T> 타입변수를 주었다.
	   
	   public void setDataList(List<T> dataList) {
	      this.dataList = dataList;
	   }
	
	public CriteriaVO(){
		this(1,3);
	}

	public CriteriaVO(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//검색 조건이 각 글자 (T,W,C)로 구성되어있으므로
	//검색 조건을 배열로 만들어서 한번에 처리하기 위함이다.
	//MyBatis의 동적 태그를 활용할수 있다.
	public String[] getTypeArr(){
		return type ==null? new String[] {}: type.split("");
	}
	
	
	
}
