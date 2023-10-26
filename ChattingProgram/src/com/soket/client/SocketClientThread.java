package com.soket.client;

import java.util.StringTokenizer;
import java.util.Vector;


public class SocketClientThread extends Thread {
	SocketClient sc = null;
	public SocketClientThread(SocketClient sc) {
		this.sc = sc;
	}
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = ""; 
				msg = (String)sc.ois.readObject(); 
				StringTokenizer st = null; 
				int protocol = 0;
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
					case 100:{
						String nickName = st.nextToken();
						sc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						Vector<String> v = new Vector<>();
						v.add(nickName);
						sc.dtm.addRow(v);
					}break;
					case 200:{
						
					}break;
					case 201:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						sc.jta_display.append("["+nickName+"]"+message+"\n");
						sc.jta_display.setCaretPosition
						(sc.jta_display.getDocument().getLength());					
					}break;
					case 202:{
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message = st.nextToken();
						//테이블에 대화명 변경하기
						for(int i=0;i<sc.dtm.getRowCount();i++) {
							String imsi = (String)sc.dtm.getValueAt(i, 0);
							if(nickName.equals(imsi)) {
								sc.dtm.setValueAt(afterName, i, 0);
								break;
							}
							/**
							 * 클라이언트의 대화명 목록을 나타내는 테이블(tc.dtm)을 순회하면서, 
							 * nickName과 일치하는 행을 찾아 afterName으로 대화명을 변경
							 */
						//채팅창에 타이틀바에도 대화명을 변경처리 한다.
						if(nickName.equals(sc.nickName)) {
							sc.setTitle(afterName+"님의 대화창");
							sc.nickName = afterName;
						}
						
						sc.jta_display.append(message+"\n");
					}}break;
					
					case 210:{
						String nickName = st.nextToken();
						sc.jta_display.append(nickName+"님이 회원탈퇴 했습니다.\n");
						sc.jta_display.setCaretPosition
						(sc.jta_display.getDocument().getLength());
						for(int i=0;i<sc.dtm.getRowCount();i++) {
							String n =(String)sc.dtm.getValueAt(i, 0);
							if(n.equals(nickName)) {
								sc.dtm.removeRow(i);
							}

						}
					}break;
				}////////////end of switch
			} catch (Exception e) {
				// TODO: handle exception
			}
		}////////////////////end of while
	}////////////////////////end of run
}

