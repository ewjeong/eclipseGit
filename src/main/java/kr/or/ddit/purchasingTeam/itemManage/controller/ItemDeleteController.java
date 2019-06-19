package kr.or.ddit.purchasingTeam.itemManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.itemManage.service.IItemManageService;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class ItemDeleteController {
	
	@Inject
	private IItemManageService service;
	
	//비동기로 삭제작업하기
	@RequestMapping(value="/purchasingTeam/itemManage/itemList/{item_code}", produces="application/json;charset=UTF-8")
	@ResponseBody //리다이렉트 방식이고, 보내줄 데이터가 따로 필요하지 않고, String으로 뷰네임을 보낼것이므로 리스폰스 바디가 필요 없다.
	public PagingVO<ItemVO>  ajaxDelete(
		@PathVariable String item_code //먼저 JSP측에서 PathVariable을 통해 선택한 아이템의 코드를 받아온다.
//		Model model //model을 통해 message를 전달할 것이다.
	){
		PagingVO<ItemVO> pagingVO = new PagingVO<>(10, 5); //screenSize: 10, blockSize: 5
		
		ServiceResult result = service.removeItem(item_code); //받은 아이템의 코드를 xml까지 전달해서 삭제작업을 하고, 상태코드를 받는다.
//		String view = "jsonView"; //view : redirect 주소 (OK이면 밑에 주소를 타는것이고, 
		if(ServiceResult.OK.equals(result)){ //상태코드가 OK이면, 리다이렉션 작업을 해준다.
//			view = "/purchasingTeam/itemManage/itemList"; //성공할 시 redirect로 다시 조회처리
			pagingVO.setTotalRecord(service.retrieveItemCount(pagingVO));
			pagingVO.setCurrentPage(1);
			List<ItemVO> list = service.retrieveItemList(pagingVO);
			pagingVO.setDataList(list);
		}else{ //실패하면 model을 통해 삭제에 실패했다는 message를 전달해준다. 
			throw new RuntimeException("삭제실패");
		}
		return pagingVO; //view를 리턴한다. 그러면 저 주소가 있는 곳을 타겠지? 그러면 ItemRetrieveController를 탈 것이다(화면전환이 될 것임)
	}
	
}
