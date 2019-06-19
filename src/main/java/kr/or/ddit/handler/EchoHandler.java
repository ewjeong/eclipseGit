package kr.or.ddit.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mainPage.signUp.service.ISignUpService;
import kr.or.ddit.vo.DepNameVO;
import kr.or.ddit.vo.EmployeeVO;
import lombok.Data;


public class EchoHandler  extends TextWebSocketHandler{

		@Inject
		ISignUpService service;
	
		private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
		//웹소켓 세션을 저장할 리스트 생성 -> 맵으로 바꿔야함. 키는 employee
//		private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
		
//		@Autowired
//		@Resource(name = "sessionMap")
//		private Map<String, WebSocketSession> sessionMap;
		private Map<String,  WebSocketSession> sessionMap = new HashMap<String,WebSocketSession>();
		
		//클라이언트와 연결 이후에 실행되는 메서드
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			//맵: sessions.put(session.getId(), session);
//			sessionList.add(session);
			Authentication auth =  (Authentication) session.getPrincipal();
			EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
			String id = employee.getEmp_id();
			String name = employee.getEmp_name();
			sessionMap.put(id, session);
			chatMessage.setType("in");
			logger.info("{} 연결됨",name);
		
			Iterator<String> empIds = sessionMap.keySet().iterator();
			
			System.out.println(name+"님이 입장하셨습니다.");
		}
		
		@Data
		class ChatMessage{
			String sender;
			String depname;
			String message;
			String type;
		}
		
		ChatMessage chatMessage = new ChatMessage();
		
		//클라이언트가 서버로 메세지를 전송했을 때 실행되는 메서드 
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			Authentication auth =  (Authentication) session.getPrincipal();
			EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
			String id = employee.getEmp_id();
			String name = employee.getEmp_name();
			DepNameVO department = service.retrieveDepName(id);
			String dep_name = department.getDep_name();
			logger.info("{}로 부터 {} 받음", name, message.getPayload());
			
			//모든 클라이언트에게 메세지 전송
//			for(WebSocketSession sess : sessionList) {
//				sess.sendMessage(new TextMessage(name+" : "+message.getPayload()));
//			}
			
			Iterator<String> empIds = sessionMap.keySet().iterator();
			
			ObjectMapper obm = new ObjectMapper();
			
			while(empIds.hasNext()){
				String empId = empIds.next();
				if(message.getPayload().contains("님이 입장하셨습니다")||message.getPayload().contains("님이 퇴장하셨습니다")){
					sessionMap.get(empId).sendMessage(new TextMessage(message.getPayload()));
				}else{
					sessionMap.get(empId).sendMessage(new TextMessage(name+"("+dep_name+") :"+message.getPayload()));
				}
//				chatMessage.setSender(name);
//				chatMessage.setMessage(message.getPayload());
//				chatMessage.setDepname(dep_name);
//				String chatInfo = obm.writeValueAsString(chatMessage);
//				System.out.println(chatInfo);
//				String empId = empIds.next();
//				sessionMap.get(empId).sendMessage(new TextMessage(chatInfo));
			}
			
			
			
			
			/*
			map방법
			Iterator<String>sessionIds = sessions.keySet().iterator();
			String sessionID = "";
			while(sessionIDs.hasNext()){
			sessionId = sessionIds.next();
			sessions.get(sessionId).sendMessage(new TextMessage("echo : "+message.getPayload())); 
			*/
		}
		
		//클라이언트와 연결을 끊었을 때 실행되는 메서드
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			Authentication auth =  (Authentication) session.getPrincipal();
			EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
			String id = employee.getEmp_id();
			String name = employee.getEmp_name();
			
			Iterator<String> empIds = sessionMap.keySet().iterator();
			
			while(empIds.hasNext()){
				String empId = empIds.next();
				sessionMap.get(empId).sendMessage(new TextMessage(name + "님이 퇴장하셨습니다."));
			
			sessionMap.remove(id);
//			TextMessage message = new TextMessage(name+"님이 퇴장하셨습니다");
//			session.sendMessage(message);
			logger.info("{} 연결 끊김 {}",name, status);
			
			System.out.println("채팅방 퇴장자 : "+ name);
		
			}
		}
	}