package com.soket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;


public class SocketServerThread extends Thread {
	public SocketServer ss = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null;//현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	
	
	public SocketServerThread(SocketServer ss) {
		this.ss = ss;
		this.client = ss.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject();
			
			ss.jta_log.append(msg+"\n");
			StringTokenizer st = new StringTokenizer(msg,"#");
			st.nextToken();
			chatName = st.nextToken();
			ss.jta_log.append(chatName+"님이 입장하였습니다.\n");
			
			for(SocketServerThread sst:ss.globalList) {
			//이전에 입장해 있는 친구들 정보 받아내기
				//String currentName = tst.chatName;
				/**
				 * tst.chatName은 새로 연결된 클라이언트의 대화명
				 * ts.globalList에 있는 모든 SocketServerThread 객체를 순회 
				 * 이 리스트에는 이전에 서버에 접속한 클라이언트 스레드의 목록이다.
				 */
				this.send(100+"#"+sst.chatName);
				
				/**
				 * 클라이언트 스레드의 대화명을 나타낸다. 
				 * 새로 연결된 클라이언트의 대화명이 아니라, 현재 연결된 클라이언트의 대화명을 의미
				 */
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ss.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		for(SocketServerThread sst:ss.globalList) {
			sst.send(msg);
		}
	}
	
	
	public void LogoutRequest(String nickName) {
	    try {
	    	System.out.println(nickName);
	        String message = nickName + "님이 탈퇴하였습니다.";
	        send(210 + "#" + nickName + "#" + message);
	        // 사용자 목록 업데이트
	        removeList(nickName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * 시점이 뒤에 발생하는 사람들은 반영을 못한다.
	 * @param nickName
	 */

	// 사용자 목록에서 사용자 제거
	public void removeList(String nickName) {
	    for (SocketServerThread sst : ss.globalList) {
	        if (sst != null && sst.chatName.equals(nickName)) {
	            ss.globalList.remove(sst);
	            break;
	        }
	    }
	}

	
	// 사용자 목록을 클라이언트에게 전송
	//서버에 접속할 때 또는 사용자 목록을 요청할 때 사용
	public void sendUserList(SocketServerThread cthread) throws IOException {
	    StringBuilder userListMessage = new StringBuilder("100#"); // 목록 메시지를 만들기 위한 문자열 빌더
	    for (SocketServerThread sst : ss.globalList) {
	        userListMessage.append(sst.chatName).append("#");
	    } // 해당 부분 리팩토링 대상 -> 회원목록 구현 예정 시 사용 메소드
	    /**
	     * 루프 내에서, 각 클라이언트 스레드 sst의 chatName을 가져와서 userListMessage에 # 문자와 함께 추가한다.
	     */
	    cthread.send(userListMessage.toString());
	}////////////////////end of sendUserList
	
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/////////////////end of send
	
	
	public void run() {
		String msg = null;
		boolean isStop = false;
		try {
			//while(true) {//무한루프에 빠질 수 있다.
			run_start:
			while(!isStop) {
				msg = (String)ois.readObject();
				ss.jta_log.append(msg+"\n");
				ss.jta_log.setCaretPosition
				(ss.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {

					case 201:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						broadCasting(201
								   +"#"+nickName
								   +"#"+message);
					}break;
					case 202:{
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message = st.nextToken();
						this.chatName = afterName;
						broadCasting(202
								+"#"+nickName
								+"#"+afterName
        						+"#"+message);
					}break;
					
//					case 210: // 삭제 소켓통신 actionPerformed와 연동하여 작성 
//					{
//						String nickName = st.nextToken();
//						String message = st.nextToken();
//						String del = "회원탈퇴 했습니다.";
//						broadCasting(210
//								+"#"+nickName + "#" + del);
//						oos.writeObject(210 + "#" + nickName + "#"+ del);
//						LogoutRequest(nickName);
//						// 이 부분에서 refresh해주는 함수를 구현해야한다.
//						sendUserList()
//						
//						
//					}break;

					case 210:{
//						String nickName = st.nextToken();
//						ss.globalList.remove(this);
//						broadCasting(500
//								+"#"+nickName);
						String nickName = st.nextToken();
						String message = st.nextToken();
						String del = "회원탈퇴 했습니다.";
						broadCasting(210
								+"#"+nickName + "#" + del);
						oos.writeObject(210 + "#" + nickName + "#"+ del);
						LogoutRequest(nickName);
						// 이 부분에서 refresh해주는 함수를 구현해야한다.
				
					}break run_start;
				}/////////////end of switch
			}/////////////////end of while			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}/////////////////////////end of run
}
