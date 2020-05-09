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

@ServerEndpoint("/broadcasting")	// client ���� server�� ������ �ּҷ� ����
public class BroadSocket {
	
	
	/*
    //������ ��� �����Ѵ�.
    //��� 1 :  1:1 ä��
    private Map<String, Session> clients = Collections.synchronizedMap(new HashMap<String, Session>());
//  private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>(); // Spring
    
    //��� 2 : ��ü ä��
//   private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>(); // Spring
    
   
    //��� 3 : ���� ��ü ü�� ��� Set�� �̿��ϴ� ���
//	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	*/
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �޽����� �������� �� ����Ǵ� �޼ҵ�
     */
	@OnMessage		// client�� ���� �޽����� ���������� ó��, ����ȭ
	public void onMessage(String message, Session session) throws IOException {
		System.out.println(message);
	
//		synchronized (clients) {
//			// �� ���.
//	        Iterator<String> sessionIds = clients.keySet().iterator();
//	        String sessionId = "";
//	        while (sessionIds.hasNext()) {
//	            sessionId = sessionIds.next();
//	//            clients.get(sessionId).sendMessage(new TextMessage("echo:" + message.getPayload())); // Spring
//	            clients.get(sessionId).getBasicRemote().sendText(message);
//	        }
//		}
        
      //����Ǿ� �ִ� ��� Ŭ���̾�Ʈ�鿡�� �޽����� �����Ѵ�.
//     session.sendMessage(new TextMessage("echo:" + message.getPayload()));			// Spring
//     clients.getBasicRemote().sendText(message);
		
		//��� 3.
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}
		
	}
	
	/**
	 * Ŭ���̾�Ʈ ���� ���Ŀ� ����Ǵ� �޼ҵ�
	 */
	@OnOpen // client ���� server�� ������ ���� ó��, client add
	public void onOpen(Session session) {
		// ���� ���� ���
//		clients.put(session.getId(), session);

		// list �߰�
		System.out.println(session);
		clients.add(session);
	}
	
	
	/**
     * Ŭ���̾�Ʈ ������ ������ �� ����Ǵ� �޼ҵ�
     */
	@OnClose		// ������ ������ ���� ó��, client remove
	public void onClose(Session session) {
	    //Map ����
//		clients.remove(session.getId());
		
		// list ����
		clients.remove(session);
	}
}
