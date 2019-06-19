package kr.or.ddit.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.vo.EmployeeVO;

public class PushHandler extends TextWebSocketHandler{
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	/*
	맵을 spring container에 등록하고 주입받아서 어플리케이션 전역에서 사용할 수 있게 만든다.
	내가 메세지를 보내고자하는 사람과 그사람의 세션 비교는 전자결재를 했을 때 동작하는 비지니스로직에서 map을 주입받아서 처리한다
	만약 사람이 사이트에 접속이 되어있지 않는 경우에는 맵에 그사람의 세션 유무 비교하고 없으면 메세지 저장해서 그냥 알람메세지 리스트에 띄어주면 될것같아!
	그리고 읽었는지 안읽었는지 상태코드 하나줘서 그 알림메세지 눌렀을때 상태 변경 해주면될것같아!
	*/ 
	@Resource(name="sessionMap")
	Map<String, WebSocketSession> sessionMap;
	
	//클라이언트와 연결될 때마다 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//로그인 사용자 정보 가져와서 맵에 넣어줌,
		Authentication auth =  (Authentication) session.getPrincipal();
		EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
		String id = employee.getEmp_id();
		String name = employee.getEmp_name();
		
		sessionMap.put(id, session);
		
		logger.info("{} 연결됨",name);
		
		//Iterator<String> empIds = sessionMap.keySet().iterator();
	}
	
	//소켓이 메세지를 받았을 때 처리되는 메서드 
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Authentication auth =  (Authentication) session.getPrincipal();
		EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
		String name = employee.getEmp_name();
		
		logger.info("{}로 부터 {} 받음", name, message.getPayload());
		
//		Iterator<String> empIds = sessionMap.keySet().iterator();
		//모든 클라이언트에게 메세지를보여준다,
//		while(empIds.hasNext()){
//			String empId = empIds.next();
//			sessionMap.get(empId).sendMessage(new TextMessage(message.getPayload()));
//		}
	}
	
	//클라이언트와 연결을 끊었을 때 실행되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Authentication auth =  (Authentication) session.getPrincipal();
		EmployeeVO employee = (EmployeeVO) auth.getPrincipal();
		String id = employee.getEmp_id();
		String name = employee.getEmp_name();
		
		sessionMap.remove(id);

		logger.info("{} 연결 끊김",name);
		
		
	}

}
