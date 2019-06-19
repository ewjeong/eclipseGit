package kr.or.ddit.superManager.EmplscheduleManage.controller;

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
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.superManager.EmplscheduleManage.service.ISalesManageService;
import kr.or.ddit.vo.IncomeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReleaseVO;
import kr.or.ddit.vo.SalesProfitVO;

                   
@Controller
public class PerformanceRetrieveController {
	
	@Inject
	ISalesManageService service;
	
	
	@RequestMapping("/superManager/emplScheduleManage/performanceList")
	public String getemplScheForm(){
		return "superManager/salesSchedule/performanceList";
	}
	
	@ResponseBody
	@RequestMapping(value="/superManager/emplScheduleManage/performanceList",produces="application/json;charset=UTF-8")
	public PagingVO<IncomeVO> getemplScheFormAJAX(
			@RequestParam(name="page", required=false, defaultValue="1")long currentPage
			,IncomeVO searchData
			,@RequestParam(name="emp_name",required=false)String emp_name
			,@RequestParam(name="rel_date",required=false)String rel_date
			){
		
		//페이징 리스트수,페이징번호 셋팅
		PagingVO<IncomeVO> pagingVO = new PagingVO<IncomeVO>(5,5);
		searchData.setEmp_name(emp_name);
		searchData.setRel_date(rel_date);
		
		
		pagingVO.setSearchData(searchData);
		pagingVO.setCurrentPage(currentPage);
		
		
		//토탈 리스트의 수
		long totalRecord= service.retrieveEmpIncomeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//리스트 
		List<IncomeVO> dataList= service.retrieveEmpIncomeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		
		
		return pagingVO;
	}
	
	@RequestMapping(value="/performanceListExcel", method=RequestMethod.POST)
	public void excelDown(
			IncomeVO searchData
			,@RequestParam(name="emp_name",required=false)String emp_name
			,@RequestParam(name="rel_date",required=false)String rel_date
			,@RequestParam(name="page",required=false,defaultValue="1")long currentPage
			,HttpServletResponse resp) throws IOException{
		
		
		
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
		
		PagingVO<IncomeVO> pagingVO = new PagingVO<IncomeVO>();
		pagingVO.setCurrentPage(currentPage);
		searchData.setEmp_name(emp_name);
		searchData.setRel_date(rel_date);
		pagingVO.setSearchData(searchData);
		
		List<IncomeVO> excelList= service.retrieveEmpExcel(pagingVO);
		
		row = sheet.createRow(0);	//1행 생성
		row.setHeight((short)0X150); //행높이 지정
		
		//셀에 데이터 넣기(1행은 거의 제목)
		cell = row.createCell(0);
		cell.setCellValue("매출일자");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("사원코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("사원명");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("거래처코드");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("거래처명");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("상품명");
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue("수량");
		cell.setCellStyle(style);
		
		cell = row.createCell(7);
		cell.setCellValue("공급액");
		cell.setCellStyle(style);
		
		cell = row.createCell(8);
		cell.setCellValue("매출");
		cell.setCellStyle(style);
		
		int index = 1;
		for(IncomeVO income : excelList){
			row = sheet.createRow(index);
			row.setHeight((short)0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(income.getRel_date());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(income.getEmp_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(income.getEmp_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(income.getCl_no());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(income.getCl_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(income.getProd_name());
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(income.getSale_oprod_qty());
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(income.getSale_oprod_cost());
			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue(income.getIncome());
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
	
	@ResponseBody
	@RequestMapping(value="/superManager/emplScheduleManage/chart",produces="application/json;charset=UTF-8")
	public PagingVO<IncomeVO> chartAjax(
			@RequestParam(name="emp_name",required=false) String emp_name
			,IncomeVO searchData
			){
		
		PagingVO<IncomeVO> pagingVO = new PagingVO<>();
		searchData.setEmp_name(emp_name);
		pagingVO.setSearchData(searchData);
		
		List<IncomeVO> chartList = service.retrieveEmpSales(pagingVO);
		pagingVO.setDataList(chartList);
		
		return pagingVO;
		
	}
	
	
	
	
	
	
	
	
	
}



















