package com.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.database.zipcodeview.DBConnectionMgr;

public class ZipCodeView extends JFrame implements ActionListener, FocusListener, MouseListener {
	String zdo = null;
	Connection 			con 	= null;
	PreparedStatement 	pstmt 	= null;
	ResultSet 			rs 		= null;
	JPanel jp_north = new JPanel();
	//insert here
	String zdos[] = {"전체","서울","경기","강원"};
	String zdos2[] = {"전체","부산","전남","대구"};
	Vector<String> vzdos = new Vector<>();
	JComboBox jcb_zdo = new JComboBox(zdos);//West
	JComboBox jcb_zdo2 = null;//West
	JTextField jtf_search = new JTextField("동이름을 입력하세요.");//Center
	JButton jbtn_search = new JButton("조회");//East
	String cols[] = {"우편번호","주소"};
	String data[][] = new String[0][2];
	DefaultTableModel dtm_zipcode = new DefaultTableModel(data,cols);
	JTable jtb_zipcode = new JTable(dtm_zipcode);
	JTableHeader jth = jtb_zipcode.getTableHeader();
	JScrollPane jsp_zipcode = new JScrollPane(jtb_zipcode
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String zdos3[] = null;
	 DBConnectionMgr dbMgr = null;
	MemberShipView memberShipView = null;
	//생성자
	public ZipCodeView() {
	}

	public ZipCodeView(MemberShipView memberShipView) {
		this.memberShipView = memberShipView;// 재정의, 치환하기
	}

	//화면처리부
	public void initDisplay() {
		jtb_zipcode.requestFocus();
		jtf_search.addFocusListener(this);
		jtb_zipcode.addMouseListener(this);
		jbtn_search.addActionListener(this);
		jtf_search.addActionListener(this);
		jp_north.setLayout(new BorderLayout());
		for(int x=0;x<zdos2.length;x++) {
			vzdos.add(zdos2[x]);
		}
		for(String s:vzdos) {
			System.out.println("s===>"+s);
		}
		jp_north.add("Center",jtf_search);
		jp_north.add("East",jbtn_search);
		this.add("North",jp_north);
		this.add("Center",jsp_zipcode);
		this.setTitle("우편번호 검색");
		this.setSize(430, 400);
		this.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		ZipCodeView zcs = new ZipCodeView();
		zcs.initDisplay();//화면이 먼저 열리도록 하고 오라클서버를 나중에 연결하자
	}

	public void refreshData(String dong) {
		List<Map<String,Object>> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT zipcode, address    ");
		sql.append("  FROM zipcode_t           ");
		sql.append(" WHERE dong LIKE ?||'%'");
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();//물리적으로 떨어져 있는 서버와 연결통로 확보
			pstmt = con.prepareStatement(sql.toString());//쿼리문을 먼저 스캔하여 있을 지 모르는 변수의 자리를 치환할것.
			pstmt.setString(1, dong);//dong, 당산, 가산, 공덕
			rs = pstmt.executeQuery();
			Map<String,Object> rmap = null;
			while(rs.next()) {
				rmap = new HashMap<>();
				rmap.put("zipcode", rs.getInt("zipcode"));
				rmap.put("address", rs.getString("address"));
				list.add(rmap);
			}
			System.out.println(list);//주소번지가 33번 출력될것이다. - 단위테스트 하자
			for(int i=0;i<list.size();i++) {//33번 반복됨 - row수
				Map<String, Object> map = list.get(i);
				Vector<Object> v = new Vector<>();
				v.add(0,map.get("zipcode"));
				v.add(1,map.get("address"));
				dtm_zipcode.addRow(v);
			}
		} catch (SQLException se) {
			System.out.println(sql.toString());//출력된 쿼리문을 갈무리해서 토드에서 확인해 볼것.
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_search || obj == jtf_search) {
			System.out.println(jtf_search.getText());//당산
			String dong = jtf_search.getText();
			refreshData(dong);
		}
		
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		if(obj == jtf_search) {
			jtf_search.setText("");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount()==2) {
			int index = jtb_zipcode.getSelectedRow();
			int zipcode = (int)dtm_zipcode.getValueAt(index, 0);
			String address = (String)dtm_zipcode.getValueAt(index, 1);
			System.out.println(zipcode +",  "+ address);//

			memberShipView.jtf_zipcode.setText(String.valueOf(zipcode));
			memberShipView.jtf_address.setText(String.valueOf(address));
			dispose();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}