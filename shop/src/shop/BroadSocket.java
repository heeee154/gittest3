package shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcasting")	// client 에서 server로 접속할 주소로 지정
public class BroadSocket {
	
	
	/*
    //세션을 모두 저장한다.
    //방법 1 :  1:1 채팅
    private Map<String, Session> clients = Collections.synchronizedMap(new HashMap<String, Session>());
//  private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>(); // Spring
    
    //방법 2 : 전체 채팅
//   private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>(); // Spring
    
   
    //방법 3 : 기존 전체 체팅 방법 Set을 이용하는 방법
//	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	*/
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	
    /**
     * 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행되는 메소드
     */
	@OnMessage		// client로 부터 메시지가 도착했을때 처리, 동기화
	public void onMessage(String message, Session session) throws IOException {
		System.out.println(message);
	
//		synchronized (clients) {
//			// 맵 방법.
//	        Iterator<String> sessionIds = clients.keySet().iterator();
//	        String sessionId = "";
//	        while (sessionIds.hasNext()) {
//	            sessionId = sessionIds.next();
//	//            clients.get(sessionId).sendMessage(new TextMessage("echo:" + message.getPayload())); // Spring
//	            clients.get(sessionId).getBasicRemote().sendText(message);
//	        }
//		}
        
      //연결되어 있는 모든 클라이언트들에게 메시지를 전송한다.
//     session.sendMessage(new TextMessage("echo:" + message.getPayload()));			// Spring
//     clients.getBasicRemote().sendText(message);
		
		//방법 3.
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}
		
	}
	
	/**
	 * 클라이언트 연결 이후에 실행되는 메소드
	 */
	@OnOpen // client 에서 server로 접속할 때의 처리, client add
	public void onOpen(Session session) {
		// 맵을 쓸때 방법
//		clients.put(session.getId(), session);

		// list 추가
		System.out.println(session);
		clients.add(session);
	}
	
	
	/**
     * 클라이언트 연결을 끊었을 때 실행되는 메소드
     */
	@OnClose		// 접속이 끊겼을 때의 처리, client remove
	public void onClose(Session session) {
	    //Map 삭제
//		clients.remove(session.getId());
		
		// list 삭제
		clients.remove(session);
	}
}
