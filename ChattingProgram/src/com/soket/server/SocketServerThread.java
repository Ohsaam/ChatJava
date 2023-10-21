package com.soket.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class SocketServerThread extends Thread{
	
	SocketServer socketServer = null;
	Socket client = null;
	ObjectOutputStream oos = null; // 입력 시 직렬화 사용
	ObjectInputStream ois = null; // 들을 때 사용
	String chatName = null; // 클라이언트 스레드의 닉네임 저장

	public SocketServerThread(SocketServer socketServer) {
		socketServer.jta_log.append("socketServerThread 호출중..." + "\n");
		this.socketServer = socketServer;
		this.client = socketServer.socket; //클라이언트의 정보를 가져온다
		socketServer.jta_log.append("client" + client + "\n");
		
		try
		{
			oos = new ObjectOutputStream(client.getOutputStream()); // 직렬화
			ois = new ObjectInputStream(client.getInputStream()); // 역직렬화
			String msg = (String)ois.readObject(); // 바이트->객체로 변환하는 역직렬화 과정 
			socketServer.jta_log.append(msg + "\n");
			StringTokenizer st = new StringTokenizer(msg,"|");
			st.nextToken();
			chatName = st.nextToken();
			socketServer.jta_log.append(chatName + "님이 입장하였습니다. \n");
			/**
			 * 이렇게 입장 했을 때 다른 사람들에게도 해당 메세지를 전달 해야 된다. 어떻게 구현할까?
			 */
			for(SocketServerThread sst : socketServer.globalList)
			{
				this.send(100+"|"+sst.chatName); // 
				
			}
			socketServer.globalList.add(this);
			this.broadCasting(msg);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}///////////////////end of SocketServerThread
	

	@Override 
	public void run()
	{
		socketServer.jta_log.append("SocketServerThread의 run 호출중..");
		boolean isStop = false;
		String msg = null;
		try
		{
			start:
				while(!isStop)
				{
					msg = (String)ois.readObject(); // 읽어옴
					socketServer.jta_log.append(msg + "\n");
					socketServer.jta_log.setCaretPosition(socketServer.jta_log.getDocument().getLength());
					StringTokenizer st = null;
					int protocol = 0;
					if(msg != null)//msg NullPointException 예외처리 해야함
					{
						st = new StringTokenizer(msg,"|");
						protocol = Integer.parseInt(st.nextToken());
						
					}
					
					switch(protocol)
					{
					case 200:
						socketServer.jta_log.append("SocketServerThread : 200번 청취완료");
						String nickName = st.nextToken();
						String message = st.nextToken();
						broadCasting(200 + "|" + nickName + "|" + message);
						
					}break start; 
				
		}}
		
		catch(Exception e) {
			
		}
	} /////////////////////////////// end of run()
	
	public void broadCasting(String message)
	{
		for(SocketServerThread sts : socketServer.globalList)
		{
			sts.send(message);
		}
	} /////////////////////////////// end of broadCasting()
	

	
	public void send(String message)
	{
		try {
			oos.writeObject(message);
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
