package kr.or.ddit.purchasingTeam.estimateManage.controller;

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

import kr.or.ddit.purchasingTeam.estimateManage.service.IEstimatePReqManageService;
import kr.or.ddit.vo.Pur_Er_ListVO;
import kr.or.ddit.vo.Pur_Er_ProdVO;

@Controller
public class EstimatePReqPdfController {
	
	@Inject
	IEstimatePReqManageService service;
	   
	   
      @RequestMapping("pdf/EstReqView.do")
      public void salesOrderView(
    	 @RequestParam(required=true, name="what") String pur_er_no   
         , HttpServletResponse resp
         ) throws Exception{
      
      //pdf 파일 생성
      Document document = new Document();   //pdf 문서 객체 생성
//	      PdfWriter writer = PdfWriter.getInstance(
//	            document, new FileOutputStream("d:/sample.pdf")); //pdf writer 객체
      
      //파일다운, output전에 contentType, header 셋팅필요
      resp.setContentType("application/pdf"); 
	  resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode("견적요청서","UTF-8")+".pdf\"");
      PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
      
      document.open(); //pdf 문서 열기
      BaseFont baseFont = BaseFont.createFont(
            "c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H
            , BaseFont.EMBEDDED);   //한글지원폰트설정
      Font TitleFont = new Font(baseFont, 12, Font.BOLD); //폰트설정
      Font NomalFont = new Font(baseFont, 12, Font.NORMAL);
      
      PdfPTable table = new PdfPTable(2); //테이블 컬럼수
      table.setHorizontalAlignment(Element.ALIGN_CENTER);
      
      //테이블너비
      float[] widths2 = {1f, 3f};
      table.setWidths(widths2);
      
      Chunk chunk = new Chunk("견적요청서", TitleFont); //출력할내용
      Paragraph ph = new Paragraph(chunk);   //문단
      ph.setAlignment(Element.ALIGN_CENTER);   //가운데정렬
      document.add(ph);
      
      document.add(chunk.NEWLINE);   //줄바꿈처리
      document.add(chunk.NEWLINE);
      //document.newPage(); //페이지나누기
      
      //데이터넣기
      Pur_Er_ListVO purEr = service.retreiveReqEst(pur_er_no);
      
      //row1
      PdfPCell cell1 = new PdfPCell(new Phrase("견적요청서번호", TitleFont));//테이블1행 성성
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell1.setFixedHeight(21);
      table.addCell(cell1);//테이블에 셀 추가
      
      PdfPCell cellEstNo = new PdfPCell(
            new Phrase(purEr.getPur_er_no()+"", NomalFont));//데이터추가
      cellEstNo.setPaddingLeft(10);
      cellEstNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellEstNo.setFixedHeight(21);
      table.addCell(cellEstNo);//테이블에 데이터 추가
      
      //row2
      PdfPCell cell2 = new PdfPCell(new Phrase("견적요청일자", TitleFont));
      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell2.setFixedHeight(21);
      table.addCell(cell2);
      
      PdfPCell cellErDate = new PdfPCell(
            new Phrase(purEr.getPur_er_date(), NomalFont));
      cellErDate.setPaddingLeft(10);
      cellErDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellErDate.setFixedHeight(21);
      table.addCell(cellErDate);
      
      //row3
      PdfPCell cell3 = new PdfPCell(new Phrase("거래처코드", TitleFont));
      cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell3.setFixedHeight(21);
      table.addCell(cell3);
      
      PdfPCell cellClNo = new PdfPCell(
            new Phrase(purEr.getCl_no(), NomalFont));
      cellClNo.setPaddingLeft(10);
      cellClNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClNo.setFixedHeight(21);
      table.addCell(cellClNo);
      
      //row4
      PdfPCell cell4 = new PdfPCell(new Phrase("거래처명", TitleFont));
      cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell4.setFixedHeight(21);
      table.addCell(cell4);
      
      PdfPCell cellClName = new PdfPCell(
            new Phrase(purEr.getCl_name(), NomalFont));
      cellClName.setPaddingLeft(10);
      cellClName.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClName.setFixedHeight(21);
      table.addCell(cellClName);
      
      //row5
      PdfPCell cell5 = new PdfPCell(new Phrase("거래처전화번호", TitleFont));
      cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell5.setFixedHeight(21);
      table.addCell(cell5);
      
      PdfPCell cellClTel = new PdfPCell(
            new Phrase(purEr.getCl_tel(), NomalFont));
      cellClTel.setPaddingLeft(10);
      cellClTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellClTel.setFixedHeight(21);
      table.addCell(cellClTel);
      
      //row6
      PdfPCell cell6 = new PdfPCell(new Phrase("담당자", TitleFont));
      cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell6.setFixedHeight(21);
      table.addCell(cell6);
      
      PdfPCell cellEmp = new PdfPCell(
            new Phrase(purEr.getEmp_name(), NomalFont));
      cellEmp.setPaddingLeft(10);
      cellEmp.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cellEmp.setFixedHeight(21);
      table.addCell(cellEmp);
      
      //document에 테이블 추가
      document.add(table);
      
      //문단 바꿈
      document.add(new Paragraph(" "));
      
      //상품내역테이블
      PdfPTable table1 = new PdfPTable(5); 
      Chunk chunk1 = new Chunk("견적요청상품내역", TitleFont); //출력할내용
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
      
      //상품데이터추가
      List<Pur_Er_ProdVO> list = purEr.getPur_er_prodList();
      for(int i=0; i<list.size(); i++){	//여러개 상품이므로 반복문 필요
    	  Pur_Er_ProdVO prod = list.get(i);
    	  
	      PdfPCell cellProdNo = new PdfPCell(
	          new Phrase(prod.getProd_no()+"", NomalFont));
	      cellProdNo.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdNo.setFixedHeight(21);
	      table1.addCell(cellProdNo);
	      
	      PdfPCell cellProdName = new PdfPCell(
	          new Phrase(prod.getProd_name(), NomalFont));
	      cellProdName.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdName.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdName.setFixedHeight(21);
	      table1.addCell(cellProdName);
	      
	      PdfPCell cellProdColor = new PdfPCell(
	          new Phrase(prod.getProd_color(), NomalFont));
	      cellProdColor.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdColor.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdColor.setFixedHeight(21);
	      table1.addCell(cellProdColor);
	      
	      PdfPCell cellProdSize = new PdfPCell(
	          new Phrase(prod.getProd_size(), NomalFont));
	      cellProdSize.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellProdSize.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellProdSize.setFixedHeight(21);
	      table1.addCell(cellProdSize);
	      
	      PdfPCell cellQty = new PdfPCell(
	          new Phrase(prod.getPur_erprod_qty()+"", NomalFont));
	      cellQty.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cellQty.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      cellQty.setFixedHeight(21);
	      table1.addCell(cellQty);
	      
      }
      
      //document에 테이블1 추가
      document.add(table1);
      
      //pdf 파일이 생성됨
      document.close();
      
      
   }
}
