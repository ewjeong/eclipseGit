package kr.or.ddit.salesTeam.orderManage.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.salesTeam.orderManage.service.IOrderSManageService;
import kr.or.ddit.salesTeam.orderManage.service.OrderSManageServiceImpl;
import kr.or.ddit.vo.ClientVO;
import kr.or.ddit.vo.ItemVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Controller
public class OrderSRetrieveController {
	
	@Inject
	IOrderSManageService service;
	
	@Inject
	WebApplicationContext container;
	 
	@RequestMapping(value="/salesTeam/orderManage/orderList", method=RequestMethod.GET)
	public String getScheduleList(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@ModelAttribute("pagingVO") PagingVO<Sale_Ord_ListVO> pagingVO 
			,@RequestParam(name="searchType", required=false) String searchType
			,@RequestParam(name="searchWord", required=false) String searchWord
			,Model model
			,HttpServletResponse resp
				){
		
		pagingVO= new PagingVO<>();
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveSalesOrderCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
	
		
		//list를 받아서 컨트롤러 쪽에서 다시 뷰로 넘겨줘야한다.
		List<Sale_Ord_ListVO> orderList = service.retrieveSalesOrderList(pagingVO);
		pagingVO.setDataList(orderList);
		model.addAttribute("pagingVO", pagingVO);
		
//		resp.setHeader("Pragma", "no-cache");
//		resp.setHeader("Cache-Control", "no-cache");
//		//파이어폭스
//		resp.addHeader("Cache-Control", "no-store");
//		resp.setDateHeader("Expires", 0);
		
		
		return "sales/order/orderList";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/salesTeam/orderManage/orderLists", produces="application/json;charset=UTF-8")
	@ResponseBody
	public PagingVO<Sale_Ord_ListVO> getAjax(
		@RequestParam(name="page", required=false, defaultValue="1") long currentPage
		,@ModelAttribute("pagingVO") PagingVO<Sale_Ord_ListVO> pagingVO
		,@RequestParam(required=false) String searchWord
		,@RequestParam(required=false) String searchType //검색타입
		,Model model
		,HttpServletResponse resp
			){
		
		pagingVO = new PagingVO<Sale_Ord_ListVO>(10,5);
		
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		//현재 페이지를 넣어준다.
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveSalesOrderCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Sale_Ord_ListVO> clientList = service.retrieveSalesOrderList(pagingVO);
		pagingVO.setDataList(clientList);
//		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO;
	}

	
	@RequestMapping(value="/salesTeam/orderManage/orderView", method=RequestMethod.GET)
	public String getScheduleView(
			@RequestParam(required=true, name="what") String sale_ord_code
		, Model model
	){
		Sale_Ord_ListVO sale_ord_listVO = service.retrieveSalesOrder(sale_ord_code);
		model.addAttribute("sale_ord_listVO", sale_ord_listVO);
		return "sales/order/orderView";
	}
	
	@RequestMapping(value="/salesOrdListExcel", method=RequestMethod.POST)
	public void excelDown(@RequestParam("hiddenWord") String hiddenWord,
				@RequestParam("hiddenType") String hiddenType
				,HttpServletResponse resp) throws IOException{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		font.setFontName("맑은고딕");
		
		//제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //가운데 정렬
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); //수직 중앙 정렬
		
		sheet = workbook.createSheet("first Sheet");
		
		PagingVO<Sale_Ord_ListVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchType(hiddenType);
		pagingVO.setSearchWord(hiddenWord);
		
		List<Sale_Ord_ListVO> excelList = service.retrieveSalesOrdExcelList(pagingVO);
		System.out.println(excelList.size());
		
		row = sheet.createRow(0); //1행 생성
		row.setHeight((short)0x150); //행 높이 지정
		
		cell = row.createCell(0);
		cell.setCellValue("주문일자");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("주문자");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("수령자");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("상품명");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("수량");
		cell.setCellStyle(style);
		
		int index = 1;
		for(Sale_Ord_ListVO sale_ord : excelList){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(sale_ord.getSale_ord_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(sale_ord.getCl_charger());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(sale_ord.getCl_receive());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(sale_ord.getProd_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(sale_ord.getSale_oprod_qty());
			cell.setCellStyle(style);
			
			index++;
		}
		
		for(int i = 0; i<excelList.size(); i++){
			sheet.autoSizeColumn(i);
		}
		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("주문리스트", "UTF-8")+".xlsx");
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		
	}
	
	
	
}
