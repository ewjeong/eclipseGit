package kr.or.ddit.salesTeam.estimateManage.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.salesTeam.estimateManage.service.EstimateSManageServiceImpl;
import kr.or.ddit.salesTeam.estimateManage.service.IEstimateSManageService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Sale_Est_ListVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;


@Controller
public class EstimateSRetrieveController {
	
	@Inject
	IEstimateSManageService service;
	
	@RequestMapping(value="/salesTeam/estimateManage/estimateList", method=RequestMethod.GET)
	public String getEstimateList(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@ModelAttribute("pagingVO") PagingVO<Sale_Est_ListVO> pagingVO 
			,@RequestParam(name="searchType", required=false) String searchType
			,@RequestParam(name="searchWord", required=false) String searchWord
			,Model model
			,HttpServletResponse resp
	){
		pagingVO= new PagingVO<>(10,5);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveSalesEstimateCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
	
		
		//list를 받아서 컨트롤러 쪽에서 다시 뷰로 넘겨줘야한다.
		List<Sale_Est_ListVO> orderList = service.retrieveSalesEstimateList(pagingVO);
		pagingVO.setDataList(orderList);
		model.addAttribute("pagingVO", pagingVO);
		return "sales/estimate/estimateList";
	}
	
	@RequestMapping(value="/salesTeam/estimateManage/estimateLists", produces="application/json;charset=UTF-8")
	@ResponseBody
	public PagingVO<Sale_Est_ListVO> getAjax(
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			,@ModelAttribute("pagingVO") PagingVO<Sale_Est_ListVO> pagingVO 
			,@RequestParam(name="searchType", required=false) String searchType
			,@RequestParam(name="searchWord", required=false) String searchWord
			,Model model
			,HttpServletResponse resp
	){
		pagingVO= new PagingVO<>(10,5);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		pagingVO.setCurrentPage(currentPage);
		
		long totalRecord = service.retrieveSalesEstimateCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
	
		
		//list를 받아서 컨트롤러 쪽에서 다시 뷰로 넘겨줘야한다.
		List<Sale_Est_ListVO> estList = service.retrieveSalesEstimateList(pagingVO);
		pagingVO.setDataList(estList);
		model.addAttribute("pagingVO", pagingVO);
		return pagingVO;
	}
	
	@RequestMapping(value="/salesTeam/estimateManage/estimateView", method=RequestMethod.GET)
	public ModelAndView getEstimateView(
		@RequestParam(required=true,name="what") int sale_est_no,
					ModelAndView model){
			Sale_Est_ListVO sale_est_listVO = service.retrieveSalesEstimate(sale_est_no);
			model.addObject("sale_est_listVO", sale_est_listVO);
			model.setViewName("sales/estimate/estimateView");
			return model;
	}
	
	//jsp에서 hiddenWord라는 name값의 input태그를 만들것
	@RequestMapping(value="/salesListExcel", method=RequestMethod.POST)
	public void excelDown(@RequestParam("hiddenWord") String hiddenWord
					,@RequestParam(name="searchType", required=false) String searchType
					,@RequestParam(name="searchWord", required=false) String searchWord
					,@RequestParam("hiddenType") String hiddenType,
					HttpServletResponse resp) throws IOException{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		
		//제목 폰트
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		font.setFontName("맑은고딕");
		
		
		//제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle style = workbook.createCellStyle(); //제목 스타일
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //가운데 정렬
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		sheet = workbook.createSheet("first sheet");
		
		PagingVO<Sale_Est_ListVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchType(hiddenType);
		pagingVO.setSearchWord(hiddenWord);
		
		List<Sale_Est_ListVO> excelList = service.retrieveSalesEstExcelList(pagingVO);
		System.out.println(excelList.size());
		row = sheet.createRow(0); //1행 생성
		row.setHeight((short)0X150);
		
		//셀에 데이터 넣기 (1행은 거의 제목)
		cell = row.createCell(0);
		cell.setCellValue("견적서번호");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("견적일자");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("담당자");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("거래처코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("거래처명");
		cell.setCellStyle(style);
		
		int index = 1;
		for(Sale_Est_ListVO sale_est : excelList){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(sale_est.getSale_est_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(sale_est.getSale_est_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(sale_est.getEmp_name());
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(sale_est.getCl_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(sale_est.getCl_name());
			cell.setCellStyle(style);
			
			index++;
		}
		
		for(int i = 0; i<excelList.size(); i++){
			sheet.autoSizeColumn(i);
		}
		
		resp.setContentType("Application/Msexcel");
		resp.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("견적서리스트", "UTF-8")+".xlsx");
		
		//파일로
		OutputStream fileOut = resp.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();
		
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		
	}
			
	
	
	
}
