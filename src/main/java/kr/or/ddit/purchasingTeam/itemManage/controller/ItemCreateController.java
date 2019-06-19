package kr.or.ddit.purchasingTeam.itemManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.itemManage.service.IItemManageService;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class ItemCreateController {
	@Inject
	IItemManageService service;
	
	@RequestMapping("/purchasingTeam/itemManage/itemInsert")
	@ResponseBody
	public ModelAndView getEstimateForm(
		ModelAndView model
	){
		List<ItemVO> top_item_codeList = service.retrieveTopItemCode();
		String view = "purchase/item/itemForm";
		model.addObject("top_item_codeList",top_item_codeList);
		model.setViewName(view);
		return model;
	}
	
	//jsp에서 ItemVO를 받아와야해 그러러면 requestBody가 필요해
	@RequestMapping(value="/purchasingTeam/itemManage/itemInsert", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public String ajaxPost( //비동기로 모달창에서 등록하는 작업
			/*@RequestParam(name="item_code") String item_code,*/
			@RequestParam(name="item_name") String item_name,
			@Validated(InsertHint.class) @ModelAttribute("item") ItemVO item, Errors errors
			,Model model
	){
		/*item.setItem_code(item_code);*/
		item.setItem_name(item_name);
		String view="jsonView"; //json 형식으로 리턴받는것z
		String msg = null;
		if(!errors.hasErrors()){
			ServiceResult result = service.createTopItem(item);
			if(ServiceResult.OK.equals(result)){
			//resp를 VO
			List<ItemVO> top_item_codeList = service.retrieveTopItemCode(); 
			model.addAttribute("top_item_codeList", top_item_codeList);//이걸 다시 보내줘야 얘를 받아서 다시 조회할 수 있다.
			}else{
				view = "/purchasingTeam/itemManage/itemInsert";
				msg = "서버오류, 다시 시도해주십시오";
			}
		}else{
			msg = "none";
			view = "purchase/item/itemForm";
		}
		model.addAttribute("message", msg);
			return view;
	}
	
	@RequestMapping(value="/purchasingTeam/itemManage/itemInsert", method=RequestMethod.POST)
	public String insertItem(
		//xml로 넘겨줘야하는 데이터 : item_code을 파라미터로 받아서 top_item_code가 되어야한다. item_name을 받아야한다
		@RequestParam String top_item_code,
		@Validated(InsertHint.class) @ModelAttribute("item") ItemVO item, Errors errors
		,@RequestParam String item_name,
		Model model
		
	){
		int left = top_item_code.indexOf("I");
		int right = top_item_code.indexOf(")");
		top_item_code = top_item_code.substring(left, right);
		System.out.println(top_item_code);
		item.setTop_item_code(top_item_code);
		item.setItem_name(item_name);
		String view = null;
		String msg = null;
		if(!errors.hasErrors()){
			ServiceResult result = service.createItem(item);
			if(result.equals(ServiceResult.OK)){
				view = "redirect:/purchasingTeam/itemManage/itemList";
			}else{
				view = "/purchasingTeam/itemManage/itemInsert";
				msg = "서버오류, 다시 시도해주십시오";
			}
		}else{
			msg = "none";
			view = "purchase/item/itemForm";
		}
		model.addAttribute("message", msg);
		return view; 
		
	}
	
}
