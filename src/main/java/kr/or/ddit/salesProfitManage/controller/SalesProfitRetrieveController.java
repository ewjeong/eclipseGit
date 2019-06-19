package kr.or.ddit.salesProfitManage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.salesProfitManage.service.ISalesProfitManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SalesProfitVO;

/**
 * @author 박연욱
 * @since 2019. 5. 23.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 23.   박연욱     		  최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class SalesProfitRetrieveController {
	
	@Inject
	ISalesProfitManageService salesProfitService;
	
	@RequestMapping(value="/salesProfitList", method=RequestMethod.GET)
	public String getSalesProfitList(Model model){
		List<SalesProfitVO> employeeProfitList = salesProfitService.retrieveEmployeeProfitList();
		List<SalesProfitVO> monthProfitList = salesProfitService.retrieveProfitMonth();
		List<SalesProfitVO> monthInputProfitList = salesProfitService.retrieveMonthProfit();
		List<SalesProfitVO> employeeList = salesProfitService.retrieveProfitEmployee();
		List<SalesProfitVO> pureProfitList = salesProfitService.retrievePureProfit();
		model.addAttribute("employeeProfit",employeeProfitList);
		model.addAttribute("monthProfit",monthProfitList);
		model.addAttribute("employeeList",employeeList);
		model.addAttribute("pureProfitList", pureProfitList);
		model.addAttribute("monthInputList", monthInputProfitList);
		return "salesProfit/salesProfitList";
	}
	
	//페이징처리 ajax
	@ResponseBody
	@RequestMapping(value="/salesProfitList.do", produces="application/json;charset=UTF-8")
	public PagingVO<SalesProfitVO> getSalesProfitList(
			@RequestParam(name="page", required=false, defaultValue="1")long currentPage
			,@RequestParam(name="searchType", required=false)String searchType
			,@RequestParam(name="searchWord", required=false)String searchWord
			,Model model
			
	){
		
		
		PagingVO<SalesProfitVO> pagingVO = new PagingVO<SalesProfitVO>();
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		//현재페이지 설정
		pagingVO.setCurrentPage(currentPage);
		long totalRecord = salesProfitService.retrieveSalesProfitCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
//		SalesProfitVO sales = new SalesProfitVO();
//		sales.setItem_name(searchWord);
//		sales.setProd_name(searchWord);
//		sales.setCl_name(searchWord);
		
//		pagingVO.setSearchData(sales);
		
		List<SalesProfitVO> profitList = salesProfitService.retrieveSalesProfitList(pagingVO);
		pagingVO.setDataList(profitList);
		
		model.addAttribute("pagingVO",pagingVO);
		
		return pagingVO;
	}
	
	
	@RequestMapping(value="/salesProfitListExcel.do", method=RequestMethod.POST)
	public void excelDown(@RequestParam("hiddenWord") String hiddenWord, @RequestParam("hiddenType")String hiddenType, HttpServletResponse resp) throws IOException{
		
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		
		//제목폰트
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		font.setFontName("맑은고딕");
		
		//제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle style = workbook.createCellStyle(); //제목스타일
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //가운데 정렬
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); //수직 중앙 정렬 설정
		
		sheet = workbook.createSheet("첫번째 시트");
		
		
		PagingVO<SalesProfitVO> pagingVO = new PagingVO<SalesProfitVO>();
		pagingVO.setSearchType(hiddenType);
		pagingVO.setSearchWord(hiddenWord);
		List<SalesProfitVO> excelList = salesProfitService.retrieveSalesProfitExcelList(pagingVO);
		System.out.println(excelList.size());
		
		row = sheet.createRow(0);	//1행 생성
		row.setHeight((short)0X150); //행높이 지정
		
		//셀에 데이터 넣기(1행은 거의 제목)
		cell = row.createCell(0);
		cell.setCellValue("매출번호");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("매출일자");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("품목명");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("제품명");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("거래처코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("거래처명");
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue("수량");
		cell.setCellStyle(style);
		
		cell = row.createCell(7);
		cell.setCellValue("매출");
		cell.setCellStyle(style);
		
		int index = 1;
		for(SalesProfitVO profit : excelList){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(profit.getRel_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(profit.getRel_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(profit.getItem_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(profit.getProd_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(profit.getCl_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(profit.getCl_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(profit.getSale_oprod_qty());
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(profit.getSale_oprod_qty()*profit.getSale_oprod_cost());
			cell.setCellStyle(style);
			
			index++;
		}
		
		for(int i = 0; i<excelList.size(); i++){
			sheet.autoSizeColumn(i);
		}
		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("매출리스트","UTF-8")+".xlsx");
		
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		
	}
	
	
	
	
	
	
}
