package kr.or.ddit.mainPage.searchEmployee.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mail.controller.mailWrite;
import kr.or.ddit.mail.service.IMailService;
import kr.or.ddit.mainPage.searchEmployee.service.ISearchEmployeeService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class SearchEmployeeRetrieveController {
   
	@Inject
	mailWrite write;
	
   @Inject
   private ISearchEmployeeService searchService;
   
   public void setSearchService(ISearchEmployeeService searchService) {
      this.searchService = searchService;
   }
   
   //아이디찾기
   @RequestMapping(value="/mainPage/searchEmployee/searchId.do", method=RequestMethod.POST)
   public String sendMailId(HttpSession session, @RequestParam("emp_name")String emp_name, @RequestParam("emp_hp")String emp_hp, @RequestParam("emp_mail")String mail,RedirectAttributes ra) throws AddressException, GeneralSecurityException, IOException, MessagingException{
      //입력한 이름을 가진 사원이 있는지 조회한다.
      EmployeeVO employee = searchService.retrieveEmployeeNameHp(emp_name, emp_hp);
      if(employee == null){
         //null일 경우 입력한 이름을 가진 사원 존재 X
         ra.addFlashAttribute("resultMsg","아이디나 전화번호가 일치하지 않습니다.");
         return "redirect:/";
      }else{
    	  
         //널 아닐경우 이메일 발송
         String subject = "아이디 찾기 안내입니다.";
         String text = "귀하의 아이디는 "+employee.getEmp_id()+" 입니다";
         write.send(subject, text, mail);
         ra.addFlashAttribute("resultMsg","귀하의 이메일 주소로 아이디를 발송 하였습니다.");
      }
      return "redirect:/";
   }
   
   
   //비밀번호 찾기
   @RequestMapping(value="/mainPage/searchEmployee/searchPass.do", method=RequestMethod.POST)
   public String sendMailPassword(HttpSession session, @RequestParam("emp_name")String emp_name, @RequestParam("emp_id") String emp_id, 
                           @RequestParam("emp_mail") String mail, RedirectAttributes ra) throws AddressException, GeneralSecurityException, IOException, MessagingException{
      EmployeeVO employee = searchService.retrieveEmployees(emp_id, emp_name); //입력한 아이디를 가진 사원이 있는지 조회한다.
      if(employee == null){
         //널일경우 아이디 또는 이름이 틀렸습니다.
         ra.addFlashAttribute("resultMsg","이름이나 아이디가 일치하지 않습니다.");
         return "redirect:/";
      }else{
         
         //널이 아닐경우 이메일 발송되었습니다.
         int randomPass = new Random().nextInt(100000)+10000;   //10000~99999
         String password = String.valueOf(randomPass);
         employee.setEmp_pass(password);
         
         String subject = "임시 비밀번호 발급 안내 입니다.";
         String text = "귀하의 임시 비밀번호는 "+password+" 입니다";
         boolean flag = write.send(subject,text, mail);
         if(flag ==true){
            ra.addFlashAttribute("resultMsg","귀하의 이메일 주소로 새로운 임시 비밀번호를 발송 하였습니다.");
            searchService.modifyEmployee(employee);
         }
         
      }
      return "redirect:/";
   }
}