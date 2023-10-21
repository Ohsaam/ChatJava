package com.soket.server;

import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SocketServer extends Thread{
	/**
	 * 왜 스레드를 상속? 설계 자체를 화면처리부 / 통신채널 설정 코드 분리
	 * 
	 */
	List<SocketServerThread> globalList = null;
	ServerSocket server = null;
	Socket socket = null;
	JFrame jf = new JFrame();
	JTextArea jta_log = new JTextArea(10,60);
	JScrollPane jsp_log = new JScrollPane(jta_log
			 , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public SocketServer() {}
	
	@Override
	public void run()
	{ 
		// 서버에 접속해온 클라이언트 스레드 정보를 관리하기 위해 벡터 생성
		globalList = new Vector<>(); 
		boolean isFlag = false;
		try
		{
			server = new ServerSocket(3002);
			jta_log.append("Server Ready.....\n");
			while(!isFlag)
			{
				socket = server.accept(); // 클라이언트 소켓의 주소번지를 가지고 있다.
				jta_log.append("클라이언트 info : "  + socket + "\n"); // 해당 부분 소켓은 클라이언트 소켓이다.
				Thread thread = new SocketServerThread(this);
				thread.start();
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void initDisplay() {
		jta_log.setLineWrap(true);
		jf.setBackground(Color.orange);
		jf.add("Center", jsp_log);
		jf.setTitle("서버측 로그 출력화면 제공...");
		jf.setSize(800, 600);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		SocketServer socketserver = new SocketServer();
		socketserver.start();
		socketserver.initDisplay();
		
	}
	
	

}
