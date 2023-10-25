package com.soket.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

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

import com.database.MemberDTO;
import com.database.MemberDao;
import com.soket.server.Protocol;
import com.ui.LoginForm;
import com.ui.MemberListFrame;
import com.ui.MemberShipView;





public class SocketClient extends JFrame implements ActionListener {
	////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null;//말 하고 싶을 때
	ObjectInputStream 	ois		= null;//듣기 할 때
	String 				nickName;//닉네임 등록
	String					 contents;

	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_list	  = new JButton("회원 리스트");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("로그아웃");
	JButton jbtn_del = new JButton("회원탈퇴");
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
	JScrollPane jsp_display = null;
	MemberListFrame mbl = null;
	
	public SocketClient() {

	}
	
	

	public SocketClient(String nickName2) {
		this.nickName = nickName2;
	}




	public void initDisplay() {
		

		jbtn_list.addActionListener(this);
		jbtn_del.addActionListener(this);
		jbtn_send.addActionListener(this);
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_font.addActionListener(this);
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_del);
		jp_second_south.add(jbtn_del);
		jp_second_south.add(jbtn_list);
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
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SocketClient sc = new SocketClient();
		sc.initDisplay();
		sc.init();
	}

	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("172.30.1.66",3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(100+"#"+nickName);
			SocketClientThread tct = new SocketClientThread(this);
			tct.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		/**
		 * 이 지점에서 닉네임을 읽어들이지 못하는 문제가 발생했다. -> 서버, 서버스레드에 대한 문제는 없다.
		 */
		
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();
		
		
		 if(jbtn_send == obj)
		{
			try {
				oos.writeObject(201
						   +"#"+nickName
						   +"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		 else if (obj == jbtn_font)
		 {
			 /**
			  * 구글참고
			  */
		        Color currentColor = jta_display.getForeground();
		        if (Color.RED.equals(currentColor)) {
		            jta_display.setForeground(Color.BLACK);
		        } else {
		            jta_display.setForeground(Color.RED);
		        }}
		 
		 
		else if(jtf_msg==obj) {
			try {
				oos.writeObject(201
						   +"#"+nickName
						   +"#"+msg);
				jtf_msg.setText("");
				
			} catch (Exception e) {
			}}

		
		else if (jbtn_del == obj) {

			String del = "회원탈퇴.";
			try {
				oos.writeObject(210
						   +"#"+nickName
						   +"#"+del);
				jtf_msg.setText("");
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			// deleteSelectedRow();
			
			
			 
		}
			
		 
		else if(jbtn_list == obj)
		{
			mbl = new MemberListFrame();
			
		}
		
		else if(jbtn_exit==obj) {
			try {

			    try {
			        oos.writeObject(210 + "#" + nickName + "#");
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    
				logout();
				dispose();
			} catch (Exception e) {
				
			}
		}
		
		
		else if(jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
			if(afterName == null || afterName.trim().length()<1) {
				JOptionPane.showMessageDialog(this
				, "변경할 대화명을 입력하세요"
				, "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				oos.writeObject(202
						   +"#"+nickName
						   +"#"+afterName
						   +"#"+nickName+"의 대화명이 "+afterName+"으로 변경되었습니다.");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}//////////////////////end of actionPerformed

	
	public void logout()
	{
		 int selectedRow = jtb.getSelectedRow();
		 LoginForm lf = new LoginForm();
		 DefaultTableModel model = (DefaultTableModel) jtb.getModel();
		 model.removeRow(selectedRow);
		 JOptionPane.showMessageDialog(this, "로그아웃 성공했습니다..", "Info", JOptionPane.INFORMATION_MESSAGE);
		 dispose();
	}
	

}
