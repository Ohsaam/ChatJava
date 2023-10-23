package com.soket.client;

import java.util.StringTokenizer;

public class SocketClientThread extends Thread{

	SocketClient socketClient = null;
	String nickName = null;
	
	public SocketClientThread(SocketClient socketClient) {

		this.socketClient =socketClient;
	}
	
	
	public void run()
	{
		boolean isStop = false;
		start:
			while(!isStop)
			{
				try
				{
					String msg = "";
					msg = (String)socketClient.ois.readObject();
					StringTokenizer st =null;
					int protocol = 0;
					if(msg != null)
					{
						st = new StringTokenizer(msg,"|");
						protocol = Integer.parseInt(st.nextToken());
					}
					switch(protocol)
					{
					case 100:
					{
						String nickName = st.nextToken();
						String message = st.nextToken();
						socketClient.jta_display.append(nickName+ "님이 입장하였습니다.");
						socketClient.jta_display.setCaretPosition(socketClient.jta_display.getDocument().getLength());
					}break;
					case 500:
					{
						
					}break start;
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
	}

}
