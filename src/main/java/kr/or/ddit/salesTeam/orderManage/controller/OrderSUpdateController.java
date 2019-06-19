package kr.or.ddit.salesTeam.orderManage.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.salesTeam.orderManage.service.IOrderSManageService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_OrdVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Controller
public class OrderSUpdateController {

	@Inject
	private IOrderSManageService service;

	@RequestMapping(value = "/salesTeam/orderManage/orderUpdate") // get
	public String getUpdateForm(@RequestParam("order") String sale_ord_code,
			// Sale_OrdVO saleOrd
			Model model) {

		// update를 위한 pk값 가져오기
		System.out.println(sale_ord_code);
		Sale_Ord_ListVO sale_ord_listVO = service.retrieveSalesOrder(sale_ord_code);

		model.addAttribute("sale_ord_listVO", sale_ord_listVO);

		return "sales/order/orderForm";
	}

	// 수정 버튼을 눌렀을 때 상세조회 화면으로 다시 보여주기
	@RequestMapping(value ="/salesTeam/orderManage/orderUpdate", method=RequestMethod.POST)
	public String updateOrder(
				 Sale_OrdVO saleOrd,
				 Authentication user,
				 RedirectAttributes redirectAttributes,
				 @RequestParam("order") String sale_ord_code
				 
			){
		
		//현재 로그인한 아이디 정보 set 하기
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String sale_ord_emp_id = employee.getEmp_id();
		saleOrd.setSale_ord_emp_id(sale_ord_emp_id);
		saleOrd.setSale_ord_code(sale_ord_code);
		String payment = saleOrd.getPayment();
		if("현금".equals(payment)){
			payment = "0";
		}else if("카드".equals(payment)){
			payment = "1";
		}
		saleOrd.setPayment(payment);
		
		System.out.println(saleOrd.getSale_oprodList());
		
		service.modifySalesOrder(saleOrd);
		
		for(Sale_OprodVO sale_oprod : saleOrd.getSale_oprodList()){
			service.modifySaleOprod(sale_oprod);
		}
		
		String view = "redirect:/salesTeam/orderManage/orderView?what="+saleOrd.getSale_ord_code();
		return view;
	}

}
