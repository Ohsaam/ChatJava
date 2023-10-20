package com.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.database.MemberDao;


public class LoginForm extends JFrame implements ActionListener {
	/////////////////////////////////////////////////////
	/* 선언부 */
	/////////////////////////////////////////////////////
	String nickName="";
	String imgPath="C:\\Users\\ohjih\\OneDrive\\바탕 화면\\ChatJava\\ChattingProgram\\src\\image\\";
	// 
	//D:\WorkSpace_Java\Java\dev_java\\src\image\\;
	JLabel jlb_id = new JLabel("아이디");
	JLabel jlb_pw = new JLabel("패스워드");

	Font jl_font = new Font("휴먼매직체", Font.BOLD, 17);
	JTextField jtf_id = new JTextField("");
	JPasswordField jpf_pw = new JPasswordField("");

	JButton jbtn_login = new JButton(
			new ImageIcon(imgPath+"login.png"));
	JButton jbtn_join = new JButton(
			new ImageIcon(imgPath+"confirm.png"));

	// JPanel에 쓰일 이미지아이콘
	ImageIcon ig = new ImageIcon(imgPath+"main.png");
	MemberShipView mbv = new MemberShipView(this);

	/////////////////////////////////////////////////////
	/* 생성자 */
	/////////////////////////////////////////////////////
	public LoginForm(){
		initDisplay();
	}

	/////////////////////////////////////////////////////
	/* jpanal 오버라이드 */
	/////////////////////////////////////////////////////

	/* 배경이미지 */
	class mypanal extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponents(g);
		}
	}

	/////////////////////////////////////////////////////
	/* 화면처리 */
	/////////////////////////////////////////////////////
	public void initDisplay() {
		setContentPane(new mypanal());
		
		/* 버튼과 텍스트필드 구성 */
		jbtn_join.addActionListener(this);
		jbtn_login.addActionListener(this);
		this.setLayout(null);
		this.setTitle("자바채팅 ver.1");
		this.setSize(350, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(800, 250);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// id 라인
		jlb_id.setBounds(45, 200, 80, 40);
		jlb_id.setFont(jl_font);
		jtf_id.setBounds(110, 200, 185, 40);
		this.add(jlb_id);
		this.add(jtf_id);

		// pw 라인
		jlb_pw.setBounds(45, 240, 80, 40);
		jlb_pw.setFont(jl_font);
		jpf_pw.setBounds(110, 240, 185, 40);
		this.add(jlb_pw);
		this.add(jpf_pw);

		// 로그인 버튼 라인
		jbtn_login.setBounds(175, 285, 120, 40);
		this.add(jbtn_login);

		// 회원가입 버튼 라인
		jbtn_join.setBounds(45, 285, 120, 40);
		this.add(jbtn_join);		
	}

	public static void main(String[] args) throws Exception {
		new LoginForm();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String username = jtf_id.getText();
		String password = jpf_pw.getText();
		MemberDao dao = MemberDao.getInstance();
		int rs = dao.findByUsernameAndPassword(username, password);
		/*
		 * rs에서 에러가 계속적으로 발생함 "PASSWORD": 부적합한 식별자 -> 에러 해결
		 */
		if (obj == jbtn_join) {

			mbv.initDisplay();

		} 
		else if (obj == jbtn_login) {
			if (rs == 1){
				JOptionPane.showMessageDialog(null, "로그인 성공");
				MemberListFrame mlf = new MemberListFrame(username);
				dispose();

			}
			else
			{
				System.out.println("읽히니?");
				try{JOptionPane.showMessageDialog(null, "로그인 실패");}
				catch (Exception e1) {e1.printStackTrace(); }
					// TODO: handle exception
				}
				


			}

				}
			
		}