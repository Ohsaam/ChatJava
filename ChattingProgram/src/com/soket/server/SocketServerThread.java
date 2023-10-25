package com.soket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;


public class SocketServerThread extends Thread {
	public SocketServer ts = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null;//현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	public SocketServerThread(SocketServer ts) {
		this.ts = ts;
		this.client = ts.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject();
			ts.jta_log.append(msg+"\n");
			StringTokenizer st = new StringTokenizer(msg,"#");
			st.nextToken();//100
			chatName = st.nextToken();
			ts.jta_log.append(chatName+"님이 입장하였습니다.\n");
			for(SocketServerThread tst:ts.globalList) {
			//이전에 입장해 있는 친구들 정보 받아내기
				//String currentName = tst.chatName;
				this.send(100+"#"+tst.chatName);
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		for(SocketServerThread tst:ts.globalList) {
			tst.send(msg);
		}
	}
	public void LogoutRequest(String nickName) {
	    try {
	        String message = nickName + "님이 퇴장하였습니다.";
	        broadCasting(210 + "#" + nickName + "#" + message);
	        // 사용자 목록 업데이트
	        removeList(nickName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// 사용자 목록에서 사용자 제거
	public void removeList(String nickName) {
	    for (SocketServerThread tst : ts.globalList) {
	        if (tst != null && tst.chatName.equals(nickName)) {
	            ts.globalList.remove(tst);
	            break;
	        }
	    }
	}

	
	// 사용자 목록을 클라이언트에게 전송
	public void sendUserList(SocketServerThread clientThread) throws IOException {
	    StringBuilder userListMessage = new StringBuilder("100#");
	    for (SocketServerThread t : ts.globalList) {
	        userListMessage.append(t.chatName).append("#");
	    }
	    clientThread.send(userListMessage.toString());
	}
	
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String msg = null;
		boolean isStop = false;
		try {
			//while(true) {//무한루프에 빠질 수 있다.
			run_start:
			while(!isStop) {
				msg = (String)ois.readObject();
				ts.jta_log.append(msg+"\n");
				ts.jta_log.setCaretPosition
				(ts.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
					case 200:{
						
					}break;
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
					
					case 210: // 삭제 소켓통신 actionPerformed와 연동하여 작성 
					{
						String nickName = st.nextToken();
						String message = st.nextToken();
						String del = "회원탈퇴 했습니다.";
						broadCasting(210
								+"#"+nickName + "#" + del);
						oos.writeObject(210 + "#" + nickName + "#"+ del);
						
						
					}break;

					case 500:{
						String nickName = st.nextToken();
						ts.globalList.remove(this);
						broadCasting(500
								+"#"+nickName);
					}break run_start;
				}/////////////end of switch
			}/////////////////end of while			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}/////////////////////////end of run
}
