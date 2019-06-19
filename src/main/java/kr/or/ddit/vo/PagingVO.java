package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징 처리에 필요한 프로퍼티를 가진 VO
 *
 */
@Getter
@NoArgsConstructor // 기본 생성자
public class PagingVO<T> {
   
   public PagingVO(int screenSize, int blockSize) {
      super();
      this.screenSize = screenSize;
      this.blockSize = blockSize;
   }
   private long totalRecord; // 쿼리문 조회로 결정
   private int screenSize = 10;
   private int blockSize = 5;
   private long currentPage; // 사용자가 선택한 파라미터로 결정
   private long startRow;
   private long endRow;
   private long startPage; 
   private long endPage; 
   private long totalPage;
   
   // 19-05-22 이초연 : 로그인한 유저 id를 저장하기 위한 프로퍼티
   private String emp_id;
   
   // 상품목록 검색기능 - 도메인에 검색 조건을 다르게 처리하기 위해, 타입변수 T 이용.
   private T searchData;
   
   // 회원관리 검색 조건과 키워드
   private String searchType;
   private String searchWord;
   
   // 페이징 처리가 된 목록을 저장할 변수
   private List<T> dataList; // pagingVO를 범용적으로 사용하기 위해 List<T> 타입변수를 주었다.
   
   public void setDataList(List<T> dataList) {
      this.dataList = dataList;
   }
   
   /**
    * totalRecord 가 결정되면, totalPage 까지 결정됨
    * @param totalRecord
    */
   public void setTotalRecord(long totalRecord) {
      this.totalRecord = totalRecord;
      totalPage = (totalRecord + (screenSize -1)) / screenSize;
   }
   
   /**
    * currentPage가 결정되면, StartRow/ endRow/ startPage/ endPage 가 결정됨
    * @param currentPage
    */
   public void setCurrentPage(long currentPage) {
      this.currentPage = currentPage;
      endRow = screenSize * currentPage;
      startRow = endRow - (screenSize -1);
      endPage = (currentPage + (blockSize -1 ))/ blockSize * blockSize;
      startPage = endPage - (blockSize -1);
      
      // 현재 페이지가 항상 가운데 링크가 되도록 하는 로직
//      int middle = (int) Math.ceil(blockSize/(double)2); // double로 캐스팅 안하면 정수연산 되므로...
//      startPage = currentPage - (middle-1);
//      if(startPage<1) startPage = 1;
//      endPage = startPage + (blockSize-1);
   }
   
   // bootstrap
//   <nav aria-label="...">
//     <ul class="pagination">
//       <li class="page-item disabled">
//         <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
//       </li>
//       <li class="page-item"><a class="page-link" href="#">1</a></li>
//       <li class="page-item active" aria-current="page">
//         <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
//       </li>
//       <li class="page-item"><a class="page-link" href="#">3</a></li>
//       <li class="page-item">
//         <a class="page-link" href="#">Next</a>
//       </li>
//     </ul>
//   </nav>
   private String functionName = "paging"; 
   public void setFunctionName(String functionName) {
      this.functionName = functionName;
   }
   
   public String getPagingHTML(){
      String pattern = "" +
            "<li class='page-item %s'>\n" 
               +"<a class='page-link' style='cursor:pointer' onclick='"+functionName + "(%d);'>%s</a>\n" 
            +"</li>\n";
      
      
      StringBuffer pagingHTML = new StringBuffer();
      pagingHTML.append("<nav>");
      pagingHTML.append("<ul class='pagination' style='justify-content: center;'>");
      
      pagingHTML.append(String.format(pattern, startPage==1?"disabled":"", (startPage-1), "이전"));
      
      long last = endPage < totalPage ? endPage : totalPage;
      
      for(long idx=startPage; idx <= last; idx++){
         pagingHTML.append(String.format(pattern, currentPage==idx?"active":"", idx, idx));   
      }
      /* 다음 구간 결정 */
      if(endPage < totalPage){
      }
      pagingHTML.append(String.format(pattern, endPage >= totalPage?"disabled":"", (endPage+1), "다음"));
      
      pagingHTML.append("</ul>");
      pagingHTML.append("</nav>");
      return pagingHTML.toString();
   }

   

   public void setSearchType(String searchType) {
      this.searchType = searchType;
   }

   public void setSearchWord(String searchWord) {
      this.searchWord = searchWord;
   }

   public void setSearchData(T searchData) {
      this.searchData = searchData;
   }
   // user id를 셋팅하기 위한 메서드
   public void setEmp_id(String emp_id) {
	   this.emp_id = emp_id;
   }
}





















