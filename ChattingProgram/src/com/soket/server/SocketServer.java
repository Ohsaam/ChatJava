package com.soket.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SocketServer extends Thread{
	/**
	 * 왜 스레드를 상속? 설계 자체를 화면처리부 / 통신채널 설정 코드 분리
	 * 
	 */
	List<ChatServerThread> globalList = null;
	ServerSocket server = null;
	Socket socket = null;
	JFrame jf = new JFrame();
	JTextArea jta_log = new JTextArea(10,60);
	JScrollPane jsp_log = new JScrollPane(jta_log
			 , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	

}
