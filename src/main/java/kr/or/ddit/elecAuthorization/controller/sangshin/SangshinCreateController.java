package kr.or.ddit.elecAuthorization.controller.sangshin;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.InsertHint;
import kr.or.ddit.elecAuthorization.service.ISangshinService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.purchasingTeam.orderManage.service.IOrderPManageService;
import kr.or.ddit.salesTeam.orderManage.service.IOrderSManageService;
import kr.or.ddit.vo.Elec_ApprovalVO;
import kr.or.ddit.vo.Elec_FormVO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.FixLineVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

/**
 * @author 이초연
 * @since 2019. 5. 17
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]] 
 * 수정일        수정자     수정내용
 * ==========   ======    ============== 
 * 2019. 5. 17  이초연      상신 등록(기안)
 * 2019. 5. 24  이초연		발주서, 주문서를 동적으로 상신등록 화면에 뿌려주기
 * 
 * Copyright (c) 2019 by DDIT All right reserved.
 * </pre>
 *
 * 상신함에 대한 컨트롤러
 * - 상신 등록 시, 유저가 선택한 결재선과 결재양식을 비동기처리로 화면에 뿌려주기
 * - 발주서나 주문서가 전자결재로 넘어왔을 경우, 
 * 	 문서의 정보들을 리플렉션 방식으로 선택된 결재양식과 맵핑시켜서, 해당 정보를 선택된 양식에 셋팅하기 
 * 
 */
@RequestMapping("/elecAuthorization/sangshin/sangshinInsert")
@Controller
public class SangshinCreateController {
	public static Logger logger = LoggerFactory.getLogger(SangshinCreateController.class);
	
	@Inject
	ISangshinService service;
	@Inject
	IOrderPManageService purchaseOrderService;
	@Inject
	IOrderSManageService saleOrderService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getSangshinForm(
		Model model
		, String ordCode
			){
		logger.info(ordCode);
		String saleOrPurOrdCode = ordCode;
		
		// 결재선 조회
		List<FixLineVO> fixLineList = service.retreiveFixLineList();
		
		// 결재 양식 조회
		List<Elec_FormVO> formList = service.retreiveFormList();
		
		model.addAttribute("fixLineList", fixLineList);
		model.addAttribute("formList", formList);
		model.addAttribute("saleOrPurOrdCode", saleOrPurOrdCode);
		
		return "elec/sangshin/sangshinForm";
	}
	
	/**
	 * < ISMS 만의 리포팅 툴 만들기 >
	 * 	리플렉션 이용해서 이름 맵핑.
	 * 	rowHtml 에 orderSheet의 정보들 셋팅해주는 메서드
	 * 
	 * @param rowHtml	 가공되지 않은 결재양식의 html 코드
	 * @param orderSheet 발주서 또는 주문서
	 * @return
	 */
	private String setHtmlOrderSheet(String rowHtml, Object orderSheet){
		Class<?> objType = orderSheet.getClass(); // 틀 가져오기
		Field[] fields = objType.getDeclaredFields(); // non-public 변수까지 가져오기
		
		String workedHtml = rowHtml;  // 가공된 html 코드가 저장될 변수
		for(Field field : fields){
			String propertyName = field.getName();
			
			try {
				PropertyDescriptor pd = new PropertyDescriptor(propertyName, objType);
				Class<?> propType = pd.getPropertyType(); // 프로퍼티 타입
				Method getter = pd.getReadMethod(); // getter 메서드 가져오기
			    Object fldValue = getter.invoke(orderSheet); // invoke로 해당 메서드 실행하기. 구체적인 타입을 모르니까 Object로 받기
//			    propType.cast(fldValue); // 프로퍼티 타입으로 다운 캐스팅
				
//			    workedHtml = workedHtml.replaceAll("@"+propertyName+"@",  fldValue.toString()); // nullPointException 뜬다.
			    workedHtml = workedHtml.replaceAll("@"+propertyName+"@",  Objects.toString(propType.cast(fldValue))); // nullPointException 방지. 대신 문서에 NULL 이라고 찍힐 수도 있다.
			    
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return workedHtml;
	}
	
	// 적용버튼 클릭 시, 비동기로 화면에 선택된 결재선과 양식이 보여야 한다.
	@ResponseBody
	@RequestMapping(produces="application/json;charset=UTF-8")
	public Map<String, Object> viewLineAndForm(
			int fl_no
			, String elec_form_code
			, HttpServletResponse resp
			, String saleOrPurOrdCode
			){
		FixLineVO fixLines = service.retreiveFixLine(fl_no); // 선택된 결재선 조회
		Elec_FormVO form = service.retreiveForm(elec_form_code); // 선택된 결재양식 조회
		// 결재 양식의 #data# 가 포함된 html 코드 가져오기
		String rowHtml = form.getElec_form_html();
		
		// 전자결재를 탔을 경우
		if(StringUtils.isNotBlank(saleOrPurOrdCode)){ 
			if( StringUtils.startsWith(saleOrPurOrdCode, "SO") ){ // 영업팀의 주문서일 경우
				Sale_Ord_ListVO orderSheet = saleOrderService.retrieveSalesOrder(saleOrPurOrdCode);
				// rowHtml 에 orderSheet 의 정보들을 셋팅해주기
//				workedHtml = rowHtml.replaceAll("#data#", orderSheet.getSale_ord_code()); 
				String workedHtml = setHtmlOrderSheet(rowHtml, orderSheet); // 가공된 html 이 넘어옴
				form.setElec_form_html(workedHtml);
				
			} else if(StringUtils.startsWith(saleOrPurOrdCode, "PO")){ // 구매팀의 발주서일 경우
				Pur_Ord_ListVO orderSheet = purchaseOrderService.retrievePurOrder(saleOrPurOrdCode);
				// rowHtml 에 orderSheet 의 정보들을 셋팅해주기
				String workedHtml = setHtmlOrderSheet(rowHtml, orderSheet); // 가공된 html 이 넘어옴
				form.setElec_form_html(workedHtml); // 가공된 html을 화면에 뿌려줄 Elec_FormVO의 elec_form_html에 셋팅해주기
			}
		}
		
		Map<String, Object> jsonMap = new LinkedHashMap<>();
		jsonMap.put("fixLines", fixLines);
		jsonMap.put("form", form);
		jsonMap.put("saleOrPurOrdCode", saleOrPurOrdCode);
		
		// 캐시를 남기지 않기 위해. 캐시에 남겨진 json데이터가 보여짐. 이를 해결하기 위해 캐시를 남기지 않도록 설정하자.
//		resp.setHeader("Pragma", "no-cache");
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.addHeader("Cache-Control", "no-store");
//		resp.setDateHeader("Expires", 0);
		
		return jsonMap;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String sangshin(
			@Validated(InsertHint.class) Elec_ApprovalVO approval
			, Errors errors
			, Model model
			, RedirectAttributes redirectAttributes
			, Authentication user
			){
		String view = null;
		String msg = null;
		String status = null;
		
		// 로그인한 유저 id 셋팅
		EmployeeVO employee = (EmployeeVO) user.getPrincipal();
		String loginUserId = employee.getEmp_id();
		approval.setElec_writer(loginUserId);
		
		if(!errors.hasErrors()){
			ServiceResult result = service.createSangshin(approval);
			
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/elecAuthorization/sangshin/sangshinList";
				msg = "OK";
				redirectAttributes.addFlashAttribute("message", msg);
			} else if(ServiceResult.FAILED.equals(result)) {
				view = "elec/sangshin/sangshinForm";
				status = "ERROR";
				msg = "서버 오류, 잠시 뒤 다시 확인해 주세요.";
			}
		} else { // 검증 불통
			view = "elec/sangshin/sangshinForm";
			status = "ERROR";
			msg = "필수 파라미터 누락";
		}
		model.addAttribute("message", msg);
		model.addAttribute("status", status);
		return view;
	}
}






















