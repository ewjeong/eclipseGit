package kr.or.ddit.purchasingTeam.stockManage.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import kr.or.ddit.purchasingTeam.stockManage.service.IStockManageService;
import kr.or.ddit.purchasingTeam.stockManage.service.IWarehousingManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Pur_Ord_ListVO;
import kr.or.ddit.vo.StockVO;

@Controller
public class WarehousingOtherController {
	
	@Inject
	IWarehousingManageService service;
	
	@RequestMapping(value="/wareExcel.do", method=RequestMethod.POST)
	public void excelDown(
			@RequestParam("prod_name") String prod_name,
			@RequestParam("ware_date") String ware_date,
			Pur_Ord_ListVO searchData,
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
		
		PagingVO<Pur_Ord_ListVO> pagingVO = new PagingVO<Pur_Ord_ListVO>();
		searchData.setProd_name(prod_name);
		searchData.setWare_date(ware_date);
		pagingVO.setSearchData(searchData);
		
		List<Pur_Ord_ListVO> excelList = service.retrieveExcelList(pagingVO);
		
		row = sheet.createRow(0);	//1행 생성
		row.setHeight((short)0X150); //행높이 지정
		
		//셀에 데이터 넣기(1행은 거의 제목)
		cell = row.createCell(0);
		cell.setCellValue("입고일");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("품목분류명");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("상품코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("상품명");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("상품크기");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("상품색상");
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue("입고수량");
		cell.setCellStyle(style);
		
		cell = row.createCell(7);
		cell.setCellValue("거래처코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(8);
		cell.setCellValue("거래처명");
		cell.setCellStyle(style);
		
		cell = row.createCell(9);
		cell.setCellValue("담당자");
		cell.setCellStyle(style);
		
		int index = 1;
		for(Pur_Ord_ListVO ware : excelList ){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(ware.getWare_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(ware.getItem_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(ware.getProd_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(ware.getProd_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(ware.getProd_size());
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(ware.getProd_color());
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(ware.getPur_oprod_qty());
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(ware.getCl_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue(ware.getCl_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(9);
			cell.setCellValue(ware.getEmp_name());
			cell.setCellStyle(style);
			index++;
		}
		
		for(int i = 0; i<excelList.size(); i++){
			sheet.autoSizeColumn(i);
		}
		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("입고내역리스트","UTF-8")+".xlsx");
		
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}	
}
