package com.soket.client;

import java.util.StringTokenizer;
import java.util.Vector;


public class SocketClientThread extends Thread {
	SocketClient tc = null;
	public SocketClientThread(SocketClient tc) {
		this.tc = tc;
	}
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = "";//100#apple
				msg = (String)tc.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
					case 100:{//100#apple
						String nickName = st.nextToken();
						tc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						Vector<String> v = new Vector<>();
						v.add(nickName);
						tc.dtm.addRow(v);
					}break;
					case 200:{
						
					}break;
					case 201:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						tc.jta_display.append("["+nickName+"]"+message+"\n");
						tc.jta_display.setCaretPosition
						(tc.jta_display.getDocument().getLength());					
					}break;
					case 202:{
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message = st.nextToken();
						//테이블에 대화명 변경하기
						for(int i=0;i<tc.dtm.getRowCount();i++) {
							String imsi = (String)tc.dtm.getValueAt(i, 0);
							if(nickName.equals(imsi)) {
								tc.dtm.setValueAt(afterName, i, 0);
								break;
							}
						}
						//채팅창에 타이틀바에도 대화명을 변경처리 한다.
						if(nickName.equals(tc.nickName)) {
							tc.setTitle(afterName+"님의 대화창");
							tc.nickName = afterName;
						}
						tc.jta_display.append(message+"\n");
					}break;
					
					case 210:{
						String nickName = st.nextToken();
						tc.jta_display.append(nickName+"님이 퇴장 하였습니다.\n");
						tc.jta_display.setCaretPosition
						(tc.jta_display.getDocument().getLength());
						for(int i=0;i<tc.dtm.getRowCount();i++) {
							String n =(String)tc.dtm.getValueAt(i, 0);
							if(n.equals(nickName)) {
								tc.dtm.removeRow(i);
							}
							/**
							 * 2개의 창을 띄운다고 가정했을 때 한쪽에서 삭제하면 다른 한쪽에서 회원탈퇴한 내용을 반영하고 싶은데 어떻게 해야돼?
							 */
						}
					}break;
				}////////////end of switch
			} catch (Exception e) {
				// TODO: handle exception
			}
		}////////////////////end of while
	}////////////////////////end of run
}

