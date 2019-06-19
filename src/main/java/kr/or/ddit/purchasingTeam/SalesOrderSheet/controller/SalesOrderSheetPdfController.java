package kr.or.ddit.purchasingTeam.SalesOrderSheet.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

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

import kr.or.ddit.purchasingTeam.SalesOrderSheet.service.ISalesOrderSheetService;
import kr.or.ddit.vo.Sale_OprodVO;
import kr.or.ddit.vo.Sale_Ord_ListVO;

@Controller
public class SalesOrderSheetPdfController {
   
   @Inject
   ISalesOrderSheetService service;
   
   
   @RequestMapping("pdf/salesOrderView.do")
   public void salesOrderView(
         @RequestParam(required=true, name="what") String sale_ord_code   
         , HttpServletResponse resp
         ) throws Exception{
      
      //pdf 파일 생성
      Document document = new Document();   //pdf 문서 객체 생성
      
      //파일다운, output전에 contentType, header 셋팅필요
      resp.setContentType("application/pdf"); 
	  resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode("주문서","UTF-8")+".pdf\"");
      PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
      
      document.open(); //pdf 문서 열기
      BaseFont baseFont = BaseFont.createFont(
            "c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H
            , BaseFont.EMBEDDED);   //한글지원폰트설정
      Font TitleFont = new Font(baseFont, 12, Font.BOLD); //폰트설정
      Font NomalFont = new Font(baseFont, 12, Font.NORMAL);
      Font ProdFont = new Font(baseFont, 8, Font.NORMAL);
      
      PdfPTable table = new PdfPTable(2); //테이블 컬럼수
      table.setHorizontalAlignment(Element.ALIGN_CENTER);
      
      //테이블너비
      float[] widths2 = {1f, 3f};
      table.setWidths(widths2);
      
      Chunk chunk = new Chunk("주문서", TitleFont); //출력할내용
      Paragraph ph = new Paragraph(chunk);   //문단
      ph.setAlignment(Element.ALIGN_CENTER);   //가운데정렬
      document.add(ph);
      
      document.add(chunk.NEWLINE);   //줄바꿈처리
      document.add(chunk.NEWLINE);
      //document.newPage(); //페이지나누기
      
      //데이터넣기
      Sale_Ord_ListVO saleOrd = service.retrieveSalesOrderSheet(sale_ord_code);
      
      //row1
      PdfPCell cell1 = new PdfPCell(new Phrase("주문번호", TitleFont));//테이블1행 성성
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell1.setFixedHeight(21);
      table.addCell(cell1);//테이블에 셀 추가
      
      PdfPCell cellOrdCode = new PdfPCell(
            new Phrase(saleOrd.getSale_ord_code(), NomalFont));//데이터추가
      cellOrdCode.setPaddingLeft(10);
      cellOrdCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellOrdCode.setFixedHeight(21);
      table.addCell(cellOrdCode);//테이블에 데이터 추가
      
      //row2
      PdfPCell cell2 = new PdfPCell(new Phrase("주문일자", TitleFont));
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell2.setFixedHeight(21);
      table.addCell(cell2);
      
      PdfPCell cellOrdDate = new PdfPCell(
            new Phrase(saleOrd.getSale_ord_date(), NomalFont));
      cellOrdDate.setPaddingLeft(10);
      cellOrdDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellOrdDate.setFixedHeight(21);
      table.addCell(cellOrdDate);
      
      //row3
      PdfPCell cell3 = new PdfPCell(new Phrase("주문자", TitleFont));
      cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell3.setFixedHeight(21);
      table.addCell(cell3);
      
      PdfPCell cellClCharger = new PdfPCell(
            new Phrase(saleOrd.getCl_charger(), NomalFont));
      cellClCharger.setPaddingLeft(10);
      cellClCharger.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClCharger.setFixedHeight(21);
      table.addCell(cellClCharger);
      
      //row4
      PdfPCell cell4 = new PdfPCell(new Phrase("주문자전화번호", TitleFont));
      cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell4.setFixedHeight(21);
      table.addCell(cell4);
      
      PdfPCell cellClTel = new PdfPCell(
            new Phrase(saleOrd.getCl_tel(), NomalFont));
      cellClTel.setPaddingLeft(10);
      cellClTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClTel.setFixedHeight(21);
      table.addCell(cellClTel);
      
      //row5
      PdfPCell cell5 = new PdfPCell(new Phrase("수령자", TitleFont));
      cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell5.setFixedHeight(21);
      table.addCell(cell5);
      
      PdfPCell cellClReceive = new PdfPCell(
            new Phrase(saleOrd.getCl_receive(), NomalFont));
      cellClReceive.setPaddingLeft(10);
      cellClReceive.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClReceive.setFixedHeight(21);
      table.addCell(cellClReceive);
      
      //row6
      PdfPCell cell6 = new PdfPCell(new Phrase("수령자주소", TitleFont));
      cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell6.setFixedHeight(21);
      table.addCell(cell6);
      
      PdfPCell cellClAdd1 = new PdfPCell(
            new Phrase(saleOrd.getCl_add1(), NomalFont));
      cellClAdd1.setPaddingLeft(10);
      cellClAdd1.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClAdd1.setFixedHeight(21);
      table.addCell(cellClAdd1);
      
      //row7
      PdfPCell cell7 = new PdfPCell(new Phrase("수령자상세주소", TitleFont));
      cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell7.setFixedHeight(21);
      table.addCell(cell7);
      
      PdfPCell cellClAdd2 = new PdfPCell(
            new Phrase(saleOrd.getCl_add2(), NomalFont));
      cellClAdd2.setPaddingLeft(10);
      cellClAdd2.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClAdd2.setFixedHeight(21);
      table.addCell(cellClAdd2);
      
      //row8
      PdfPCell cell8 = new PdfPCell(new Phrase("결제방법", TitleFont));
      cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell8.setFixedHeight(21);
      table.addCell(cell8);
      
      PdfPCell cellPayment = new PdfPCell(
            new Phrase(saleOrd.getPayment(), NomalFont));
      cellPayment.setPaddingLeft(10);
      cellPayment.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellPayment.setFixedHeight(21);
      table.addCell(cellPayment);
      
      //row9
      PdfPCell cell9 = new PdfPCell(new Phrase("결제금액", TitleFont));
      cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell9.setFixedHeight(21);
      table.addCell(cell9);
      
      PdfPCell cellOrdCost = new PdfPCell(
            new Phrase(saleOrd.getTotal_cost()+"", NomalFont));
      cellOrdCost.setPaddingLeft(10);
      cellOrdCost.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellOrdCost.setFixedHeight(21);
      table.addCell(cellOrdCost);
      
      //row10
      PdfPCell cell10 = new PdfPCell(new Phrase("거래처코드", TitleFont));
      cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell10.setFixedHeight(21);
      table.addCell(cell10);
      
      PdfPCell cellClNo = new PdfPCell(
            new Phrase(saleOrd.getCl_no(), NomalFont));
      cellClNo.setPaddingLeft(10);
      cellClNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClNo.setFixedHeight(21);
      table.addCell(cellClNo);
      
      //row11
      PdfPCell cell11 = new PdfPCell(new Phrase("거래처명", TitleFont));
      cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell11.setFixedHeight(21);
      table.addCell(cell11);
      
      PdfPCell cellClName = new PdfPCell(
            new Phrase(saleOrd.getCl_name(), NomalFont));
      cellClName.setPaddingLeft(10);
      cellClName.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClName.setFixedHeight(21);
      table.addCell(cellClName);
      
      //row12
      PdfPCell cell12 = new PdfPCell(new Phrase("출고상태", TitleFont));
      cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell12.setFixedHeight(21);
      table.addCell(cell12);
      
      PdfPCell cellComple = new PdfPCell(
            new Phrase(saleOrd.getSale_ord_complete(), NomalFont));
      cellComple.setPaddingLeft(10);
      cellComple.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellComple.setFixedHeight(21);
      table.addCell(cellComple);
     
      //document에 테이블 추가
      document.add(table);
      
      
      //문단 바꿈
      document.add(new Paragraph(" "));
      
      //상품내역테이블
      PdfPTable table1 = new PdfPTable(6); 
      Chunk chunk1 = new Chunk("주문상품내역", TitleFont); //출력할내용
      Paragraph ph1 = new Paragraph(chunk1);   //문단
      ph1.setAlignment(Element.ALIGN_CENTER);   //가운데정렬
      document.add(ph1);
      
      document.add(chunk1.NEWLINE);   //줄바꿈처리
      
      
      //row1
      PdfPCell cell13 = new PdfPCell(new Phrase("상품코드", TitleFont));
      cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell13.setFixedHeight(21);
      table1.addCell(cell13);
      
      PdfPCell cell14 = new PdfPCell(new Phrase("상품명", TitleFont));
      cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell14.setFixedHeight(21);
      table1.addCell(cell14);
      
      PdfPCell cell15 = new PdfPCell(new Phrase("색상", TitleFont));
      cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell15.setFixedHeight(21);
      table1.addCell(cell15);
      
      PdfPCell cell16 = new PdfPCell(new Phrase("크기", TitleFont));
      cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell16.setFixedHeight(21);
      table1.addCell(cell16);
      
      PdfPCell cell17 = new PdfPCell(new Phrase("수량", TitleFont));
      cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell17.setFixedHeight(21);
      table1.addCell(cell17);
      
      PdfPCell cell18 = new PdfPCell(new Phrase("단가", TitleFont));
      cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell18.setFixedHeight(21);
      table1.addCell(cell18);
      
      //상품데이터추가
      List<Sale_OprodVO> list = saleOrd.getSale_oprodList();
      for(int i=0; i<list.size(); i++){	//여러개 상품이므로 반복문 필요
    	  Sale_OprodVO prod = list.get(i);
    	  
	      PdfPCell cellProdNo = new PdfPCell(
	          new Phrase(prod.getProd_no()+"", ProdFont));
	      cellProdNo.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdNo.setFixedHeight(21);
	      table1.addCell(cellProdNo);
	      
	      PdfPCell cellProdName = new PdfPCell(
	          new Phrase(prod.getProd_name(), ProdFont));
	      cellProdName.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdName.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdName.setFixedHeight(21);
	      table1.addCell(cellProdName);
	      
	      PdfPCell cellProdColor = new PdfPCell(
	          new Phrase(prod.getProd_color(), ProdFont));
	      cellProdColor.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdColor.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdColor.setFixedHeight(21);
	      table1.addCell(cellProdColor);
	      
	      PdfPCell cellProdSize = new PdfPCell(
	          new Phrase(prod.getProd_size(), ProdFont));
	      cellProdSize.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdSize.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdSize.setFixedHeight(21);
	      table1.addCell(cellProdSize);
	      
	      PdfPCell cellQty = new PdfPCell(
	          new Phrase(prod.getSale_oprod_qty()+"", ProdFont));
	      cellQty.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellQty.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellQty.setFixedHeight(21);
	      table1.addCell(cellQty);
	      
	      PdfPCell cellCost = new PdfPCell(
	          new Phrase(prod.getSale_oprod_cost()+"", ProdFont));
	      cellCost.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellCost.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellCost.setFixedHeight(21);
	      table1.addCell(cellCost);
      }
      
      //document에 테이블1 추가
      document.add(table1);
      
      //pdf 파일이 생성됨
      document.close();
      
      
   }

   
   
}