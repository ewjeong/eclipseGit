package kr.or.ddit.salesTeam.estimateManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.salesTeam.estimateManage.service.IEstimateSManageService;
import kr.or.ddit.vo.Sale_EprodVO;
import kr.or.ddit.vo.Sale_EstVO;
import kr.or.ddit.vo.Sale_Est_ListVO;

@Controller
public class EstimateSUpdateController {
//	@Inject
//	IEstimateSManageService service;
//	
//	@RequestMapping(value="/salesTeam/estimateManage/estimateUpdate", method=RequestMethod.GET)
//	public String getUpdateForm(
//				@RequestParam("est") String sale_est_no,
//				Model model
//			){
//		
//		Sale_Est_ListVO sale_est_listVO = service.retrieveSalesEstimate(Integer.parseInt(sale_est_no));
//		model.addAttribute("sale_est_listVO", sale_est_listVO);
//		
//		//View로 간다.
//		return "sales/estimate/estimateForm";
//	}
//	
//	//수정 버튼 클릭했을 때 상세조회 화면
//	@RequestMapping(value="/salesTeam/estimateManage/estimateUpdates", method=RequestMethod.POST)
//	public String update(
//				Sale_EstVO sale_est,
//				Authentication user,
//				RedirectAttributes redirectAttributes
//				
//			){
//		
//		service.modifySalesEstimate(sale_est);
//		
//		for(Sale_EprodVO sale_eprod : sale_est.getSale_eprodList()){
//			service.modifySalesEprod(sale_eprod);
//		}
//		
//		
//		return "redirect:/salesTeam/estimateManage/estimateView?what="+sale_est.getSale_est_no();
//	}
	
	
	

}
