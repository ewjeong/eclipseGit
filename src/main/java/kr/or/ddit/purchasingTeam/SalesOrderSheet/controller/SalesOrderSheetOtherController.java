package kr.or.ddit.purchasingTeam.SalesOrderSheet.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.purchasingTeam.SalesOrderSheet.service.ISalesOrderSheetService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;


/**
 * @author 정은우
 * @since 2019. 5. 28.
 * @version 1.0
 * @see 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 5. 28.      정은우       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class SalesOrderSheetOtherController {
	
	@Inject
	ISalesOrderSheetService service;
	
	@RequestMapping(value="/SalesOrderSheetExcel.do", method=RequestMethod.POST)
	public void excelDown(
			@RequestParam("cl_name") String cl_name,
			@RequestParam("sale_ord_date") String sale_ord_date,
			Sale_Ord_ListVO searchData,
			HttpServletResponse resp
			) throws IOException{
		
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
		
		PagingVO<Sale_Ord_ListVO> pagingVO = new PagingVO<Sale_Ord_ListVO>();
		searchData.setCl_name(cl_name);
		searchData.setSale_ord_date(sale_ord_date);
		pagingVO.setSearchData(searchData);
		
		List<Sale_Ord_ListVO> excelList = service.retireveExcelList(pagingVO);
		
		row = sheet.createRow(0);	//1행 생성
		row.setHeight((short)0X150); //행높이 지정
		
		//셀에 데이터 넣기(1행은 거의 제목)
		cell = row.createCell(0);
		cell.setCellValue("주문일자");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("주문코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("주문자명");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("거래처명");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("결제방법");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("처리단계");
		cell.setCellStyle(style);
		
		int index = 1;
		for(Sale_Ord_ListVO order : excelList){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(order.getSale_ord_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(order.getSale_ord_code());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(order.getCl_charger());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(order.getCl_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(order.getPayment());
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(order.getSale_ord_complete());
			cell.setCellStyle(style);
			
			index++;
		}
		
		for(int i = 0; i<excelList.size(); i++){
			sheet.autoSizeColumn(i);
		}
		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("주문서리스트","UTF-8")+".xlsx");
		
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}	
	
	
	
	
}

























