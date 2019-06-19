package kr.or.ddit.salesTeam.estimateManage.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kr.or.ddit.salesTeam.estimateManage.service.IEstimateSManageService;
import kr.or.ddit.vo.Sale_Eprod_ListVO;
import kr.or.ddit.vo.Sale_Est_ListVO;


@Controller
public class SalesEstimatePdfController {
	
	@Inject
	IEstimateSManageService service;
	
	@RequestMapping("pdf/salesEstimatePDFView")
	public void salesEstimatePDFView(
			@RequestParam(required=true, name="what") String sale_est_noStr
			,HttpServletResponse resp
			)throws Exception{
		
		int sale_est_no = 0;
		if(StringUtils.isNumeric(sale_est_noStr)){
			sale_est_no = Integer.parseInt(sale_est_noStr);
		}
		
		
		//PDF 파일 생성
		Document document = new Document();
		
		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode("견적서", "UTF-8")+".pdf\"");
		
		PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
	
		document.open(); //pdf 문서 열기
		
		BaseFont baseFont = BaseFont.createFont(
				"c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H
				, BaseFont.EMBEDDED); //한글 지원 폰트 설정
		Font titleFont = new Font(baseFont, 9, Font.BOLD);
		Font normalFont = new Font(baseFont, 9, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(2); //테이블 칼럼 수
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		//테이블 너비
		float[] widths2 = {1f, 3f};
		table.setWidths(widths2);
		
		Chunk chunk = new Chunk("견적서", titleFont); //출력할 내용
		Paragraph ph = new Paragraph(chunk); //문단
		ph.setAlignment(Element.ALIGN_CENTER); //가운데 정렬
		document.add(ph); //문단을 추가한다.
		
		document.add(chunk.NEWLINE);   //줄바꿈처리
    	document.add(chunk.NEWLINE);
	      //document.newPage(); //페이지나누기
		
		
		//데이터를 넣는다
		Sale_Est_ListVO sale_Est = service.retrieveSalesEstimate(sale_est_no);
		
		//row 1
		PdfPCell cell1 = new PdfPCell(new Phrase("견적서코드", titleFont)); //테이블 1행 생성
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell1.setFixedHeight(21);
		table.addCell(cell1); //테이블에 셀 추가하기
		

	    PdfPCell cellEstNo = new PdfPCell(
	          new Phrase(sale_Est.getSale_est_no()+"", normalFont));//데이터추가
	    cellEstNo.setPaddingLeft(10);
	    cellEstNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellEstNo.setFixedHeight(21);
	    table.addCell(cellEstNo);//테이블에 데이터 추가
		
	    //row 2
	    PdfPCell cell2 = new PdfPCell(new Phrase("견적일자", titleFont)); //테이블 1행 생성
	    cell2.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell2.setFixedHeight(21);
	    table.addCell(cell2); //테이블에 셀 추가하기
	    
	    
	    PdfPCell cellEstDate = new PdfPCell(
	    		new Phrase(sale_Est.getSale_est_date(), normalFont));//데이터추가
	    cellEstDate.setPaddingLeft(10);
	    cellEstDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellEstDate.setFixedHeight(21);
	    table.addCell(cellEstDate);//테이블에 데이터 추가
	    
	    //row 3
	    PdfPCell cell3 = new PdfPCell(new Phrase("주문자", titleFont)); //테이블 1행 생성
	    cell3.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell3.setFixedHeight(21);
	    table.addCell(cell3); //테이블에 셀 추가하기
	    
	    
	    PdfPCell cellClCharger = new PdfPCell(
	    		new Phrase(sale_Est.getCl_charger(), normalFont));//데이터추가
	    cellClCharger.setPaddingLeft(10);
	    cellClCharger.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellClCharger.setFixedHeight(21);
	    table.addCell(cellEstDate);//테이블에 데이터 추가
	    
	    //row 4
	    PdfPCell cell4 = new PdfPCell(new Phrase("담당사원", titleFont)); //테이블 1행 생성
	    cell4.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell4.setFixedHeight(21);
	    table.addCell(cell4); //테이블에 셀 추가하기
	    
	    
	    PdfPCell cellEmpName = new PdfPCell(
	    		new Phrase(sale_Est.getEmp_name(), normalFont));//데이터추가
	    cellEmpName.setPaddingLeft(10);
	    cellEmpName.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellEmpName.setFixedHeight(21);
	    table.addCell(cellEmpName);//테이블에 데이터 추가
	    
	    //row 5
	    PdfPCell cell5 = new PdfPCell(new Phrase("거래처명", titleFont)); //테이블 1행 생성
	    cell5.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell5.setFixedHeight(21);
	    table.addCell(cell5); //테이블에 셀 추가하기
	    
	    
	    PdfPCell cellClName = new PdfPCell(
	    		new Phrase(sale_Est.getCl_name(), normalFont));//데이터추가
	    cellClName.setPaddingLeft(10);
	    cellClName.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellClName.setFixedHeight(21);
	    table.addCell(cellClName);//테이블에 데이터 추가
	    
	    //row 5
	    PdfPCell cell55 = new PdfPCell(new Phrase("특이사항", titleFont)); //테이블 1행 생성
	    cell5.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell5.setFixedHeight(21);
	    table.addCell(cell55); //테이블에 셀 추가하기
	    
	    
	    PdfPCell cellSaleDetail = new PdfPCell(
	    		new Phrase(sale_Est.getSale_detail(), normalFont));//데이터추가
	    cellSaleDetail.setPaddingLeft(10);
	    cellSaleDetail.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cellSaleDetail.setFixedHeight(21);
	    table.addCell(cellSaleDetail);//테이블에 데이터 추가
	    
	    
	    
	    //하나의 견적서에 대한 여러개의 상품조회
	    
	    //document에 테이블 추가
	    document.add(table);
	    
	    //문단 바꿈
	    document.add(new Paragraph(" "));
	    
	    //상품내역테이블
	    PdfPTable table1 = new PdfPTable(6);
	    Chunk chunk1 = new Chunk("견적상품내역", titleFont); //출력 내용
	    Paragraph ph1 = new Paragraph(chunk1);//문단
	    ph1.setAlignment(Element.ALIGN_CENTER);
	    document.add(ph1);
	    
	    document.add(chunk1.NEWLINE);//줄바꿈처리
	    
	    //row6
	    PdfPCell cell6 = new PdfPCell(new Phrase("상품코드", titleFont)); //테이블 1행 생성
	    cell6.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell6.setFixedHeight(21);
	    table1.addCell(cell6); //테이블에 셀 추가하기
	    
	    //row7
	    PdfPCell cell7 = new PdfPCell(new Phrase("상품명", titleFont)); //테이블 1행 생성
	    cell7.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell7.setFixedHeight(21);
	    table1.addCell(cell7); //테이블에 셀 추가하기
	    
	    //row8
	    PdfPCell cell8 = new PdfPCell(new Phrase("색상", titleFont)); //테이블 1행 생성
	    cell8.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell8.setFixedHeight(21);
	    table1.addCell(cell8); //테이블에 셀 추가하기
	    
	    //row9
	    PdfPCell cell9 = new PdfPCell(new Phrase("크기", titleFont)); //테이블 1행 생성
	    cell9.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell9.setFixedHeight(21);
	    table1.addCell(cell9); //테이블에 셀 추가하기
	    
	    //row10
	    PdfPCell cell10 = new PdfPCell(new Phrase("수량", titleFont)); //테이블 1행 생성
	    cell10.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell10.setFixedHeight(21);
	    table1.addCell(cell10); //테이블에 셀 추가하기
	    
	    //row11
	    PdfPCell cell11 = new PdfPCell(new Phrase("6개월치 평균 단가", titleFont)); //테이블 1행 생성
	    cell11.setHorizontalAlignment(Element.ALIGN_CENTER); //가운데 정렬
	    cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell11.setFixedHeight(21);
	    table1.addCell(cell11); //테이블에 셀 추가하기
	    
	    
	    //상품 데이터 추가
	    List<Sale_Eprod_ListVO> list = sale_Est.getSale_eprodList();
	    for(int i=0; i<list.size(); i++){
	    	Sale_Eprod_ListVO eprod = list.get(i);
	    	
	    	PdfPCell cellProdNo = new PdfPCell(
	    		new Phrase(eprod.getProd_no()+"", normalFont));
	    	cellProdNo.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdNo.setFixedHeight(21);
    		table1.addCell(cellProdNo);
			
    		PdfPCell cellProdName = new PdfPCell(
    				new Phrase(eprod.getProd_name(), normalFont));
    		cellProdName.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdName.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdName.setFixedHeight(21);
    		table1.addCell(cellProdName);
    		
    		PdfPCell cellProdColor = new PdfPCell(
    				new Phrase(eprod.getProd_color()+"", normalFont));
    		cellProdColor.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdColor.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdColor.setFixedHeight(21);
    		table1.addCell(cellProdColor);
    		
    		PdfPCell cellProdSize = new PdfPCell(
    				new Phrase(eprod.getProd_size(), normalFont));
    		cellProdSize.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdSize.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdSize.setFixedHeight(21);
    		table1.addCell(cellProdSize);
    		
    		PdfPCell cellProdQty = new PdfPCell(
    				new Phrase(eprod.getSale_eprod_qty()+"", normalFont));
    		cellProdQty.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdQty.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdQty.setFixedHeight(21);
    		table1.addCell(cellProdQty);
    		
    		PdfPCell cellProdCost = new PdfPCell(
    				new Phrase(eprod.getSale_eprod_cost()+"", normalFont));
    		cellProdCost.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cellProdCost.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		cellProdCost.setFixedHeight(21);
    		table1.addCell(cellProdCost);
    		
	    }
	    
	    //document에 테이블 1 추가
	    document.add(table1);
	    
	    //pdf 파일이 생성
	    document.close();
	    		
	    
	}
}
