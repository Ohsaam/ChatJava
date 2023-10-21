package com.soket.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;


public class SocketClient extends JFrame implements ActionListener{
	
	
////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket socket = null;
	ObjectOutputStream 	oos 	= null;//말 하고 싶을 때
	ObjectInputStream 	ois		= null;//듣기 할 때
	String 				nickName= null;//닉네임 등록
	
	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_one	  = new JButton("1:1");
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("나가기");
	String cols[] 		  = {"대화명"};
	String data[][] 	  = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable			  jtb = new JTable(dtm);
	JScrollPane       jsp = new JScrollPane(jtb);
	JPanel jp_first 		= new JPanel();
	JPanel jp_first_south 	= new JPanel();
	JTextField jtf_msg = new JTextField(20);//south속지 center
	JButton jbtn_send  = new JButton("전송");//south속지 east
	JTextArea jta_display = null;
	JTextPane jtp = null;
	JScrollPane jsp_display = null;	
	
	/**
	 * 닉네임이 결정하고 나서 init 메소드가 호출된다.
	 */
	public void init()
	{
		try {
			socket = new Socket("172.26.0.1",3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(100+"|"+nickName);
			SocketClientThread sct = new SocketClientThread(this);
			sct.start();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public void initDisplay()
	{
		jtf_msg.addActionListener(this);
		//사용자의 닉네임 받기
		/**
		 * 이 부분 자세히 보기 -> 키 값을 넘길 떄 
		 */
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		jta_display = new JTextArea();
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);
		Font font = new Font("굴림체",Font.BOLD,16);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display);		
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setSize(800, 550);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SocketClient sc = new SocketClient();
		sc.initDisplay();
		sc.init();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		//말하기 구현 - > oos.writeObject("200|kiwi|tomato|오늘 스터디할까?");//프로토콜설계
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		//메시지 입력 후에 엔터 친거야?
		if(jtf_msg == obj) {
			try {
				oos.writeObject(200+"|"+nickName+"|"+msg);
				//메시지를 서버로 전송하고 나면 JTextField적힌 문자열은 지운다.
				jtf_msg.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	


}
