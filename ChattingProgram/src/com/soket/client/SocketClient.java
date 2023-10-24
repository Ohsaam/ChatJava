package com.soket.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.ui.LoginForm;
import com.ui.MemberListFrame;
import com.ui.MemberShipView;





public class SocketClient extends JFrame implements ActionListener {
	////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null;//말 하고 싶을 때
	ObjectInputStream 	ois		= null;//듣기 할 때
	String 				nickName;//닉네임 등록
	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south = new JPanel();
	
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("나가기");
	JButton jbtn_del = new JButton("삭제");
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
	
	
	
	public SocketClient() {

	}
	
	

	public SocketClient(String nickName2) {
		this.nickName = nickName2;
	}



	/**
	 * 막히는 부분 정리
	 * 1. 닉네임을 넘겨 받았음 근데 닉네임을 갖고 온 클라이언트에서 엔터, 전송, 1:1 대화, 닉네임 변경이 안된다. ->why? initDisplay 문제는 아니다. actionPerformed에서 닉네임을 읽어오지 못하는 것인가?
	 * 
	 */
	public void initDisplay() {
		

	    // 사용 가능한 닉네임 중 하나를 선택
//	    nickName = (String) JOptionPane.showInputDialog(this, "사용 가능한 닉네임을 선택하세요:", "닉네임 선택",
//	        JOptionPane.PLAIN_MESSAGE, null, availableNicknames.toArray(), availableNicknames.get(0));
//
//	    if (nickName == null) {
//	        JOptionPane.showMessageDialog(this, "닉네임을 선택해야 합니다. 프로그램을 종료합니다.", "알림", JOptionPane.ERROR_MESSAGE);
//	        System.exit(0);
//	    }
		//사용자의 닉네임 받기
		//nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		jbtn_del.addActionListener(this);
		jbtn_send.addActionListener(this);
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_del);
		
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
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SocketClient sc = new SocketClient();
		sc.initDisplay();
		sc.init();
	}

	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("172.16.2.7",3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
			//서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(100+"#"+nickName);
			//서버에 말을 한 후 들을 준비를 한다.
			SocketClientThread tct = new SocketClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
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
		else if(jtf_msg==obj) {
			try {
				oos.writeObject(201
						   +"#"+nickName
						   +"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		else if (jbtn_del == obj) {
			deleteSelectedRow();
		}
			
		
		
		else if(jbtn_exit==obj) {
			try {
				oos.writeObject(500+"#"+this.nickName);
				//자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				// TODO: handle exception
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


	

	public void deleteSelectedRow() {
	    int index = jtb.getSelectedRow();
	    if (index < 0) {
	        JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하시오.", "INFO", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }
	    String nicknameToDelete = (String) jtb.getValueAt(index, 0);

	    MemberDao dao = MemberDao.getInstance();
	    int result = dao.deleteMemberByNickname(nicknameToDelete);
	    
	    if (result == 1) {
	        JOptionPane.showMessageDialog(this, "삭제 성공하였습니다.", "Info", JOptionPane.INFORMATION_MESSAGE);
	        refreshTable();
	    } else {
	        JOptionPane.showMessageDialog(this, "삭제에 실패했습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void refreshTable() {
		 
	    while (dtm.getRowCount() > 0) {
	        dtm.removeRow(0);
	    }
	    
	    MemberDao dao = MemberDao.getInstance();
	    Vector<String> nicknames = dao.findNickName();
	    
	    for (String nickname : nicknames) {
	        dtm.addRow(new Object[] { nickname });
	    }
	}
}
