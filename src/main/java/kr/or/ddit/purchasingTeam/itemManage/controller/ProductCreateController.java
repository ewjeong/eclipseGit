package kr.or.ddit.purchasingTeam.itemManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.itemManage.service.IProductManageService;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.ProductVO;

@Controller
@RequestMapping("/purchasingTeam/itemManage/productInsert")
public class ProductCreateController {
	
	@Inject
	IProductManageService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String productForm(
			Model model
			){
		List<ItemVO> itemList = service.retrieveItemList();
		
		model.addAttribute("itemList", itemList);
		return "purchase/item/productForm";
	}
	  
	@RequestMapping(method=RequestMethod.POST)
	public String productInsert(
			ProductVO product
			, Model model
			, RedirectAttributes redirectAttributes
			){
		String view = "";
		String msg = "";
		
		ServiceResult result = service.CreateProd(product);
		if(ServiceResult.OK.equals(result)) {
			view = "redirect:/purchasingTeam/itemManage/productList";
			msg = "OK";
			redirectAttributes.addAttribute("message", msg);
		} else if(ServiceResult.FAILED.equals(result)){
			view = "purchase/item/productForm";
			msg = "FAILED";
			model.addAttribute("message", msg);
		}
		return view;
	}
	
	
}
