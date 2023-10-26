package com.database.zipcodeview;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
	static DBConnectionMgr  dbMgr = null;
	/*
	 * null로 초기화 하는 이유는 nullcheck를 해서 null일 때만 새로 인스턴스화 하고 null이 아닐 떄는 계속 사용한다.
	 * 
	 */
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@172.16.2.7:1521:orcl11";
	public static final String user = "scott";
	public static final String pw = "tiger";
//	
//	String url="jdbc:oracle:thin:@172.30.1.66:1522:orcl11";
//	String id="scott";
//	String pw="tiger";
//	String driver="oracle.jdbc.driver.OracleDriver";
	public static DBConnectionMgr getInstance()
	{
		
		if(dbMgr == null)
		{
			dbMgr =  new DBConnectionMgr();
		}
		return dbMgr;
		
	}
	 // 리턴 타입으로 연결통로를 확보한 con을 얻는다.
	// connection(url,계정정보가 일치해야함) - PrepareStatement(쿼리문 전달, 처리 요청한다.) - ResultSet(커서를 조작하는 메소드) 를 제공 받는다.
	// 앞에 객체가 주입되지 않으면 나머지 뒤에는 모두 null인 상태에 놓인다.
	
	public Connection getConnection()
	{
		/*
		 * 예외처리 시 try..catch 블록을 사용하는데 멀티 블록이 가능함. 단 하위에서 상위클래스로 처리함.
		 * 
		 * 중복으로 예외처리를 하면 되는데 범위를 따져야한다. -> 적은 범위를 처음으로 했으면 다음엔 더 넓은 범위 
		 * 그 다음은 더 넓은 범위로 해야한다.
		 */
	
		try {
			
			// 각 제조사의 드라이버 클래스를 로딩하기
			/*
			 * 물리적으로 떨어져있는 오라클 서버와 연결통로 확보 // ClassNotFoundException
			 * getConnection 메소드의 원형도 Static 붙어 있다. + 하나다 복제 허용 안함 - 하나로 사용하고 반납하고 사용한다.
			 * getConnection 메소드 호출 때문에 예외처리를 하였다. 
			 * 이유는 URL이 존재하지 않을 경우 - 런타임오류(실행에러) + 논리의 오류 + 사이드 이팩트
			 * 신뢰도가 높은 코드는 + 지역변수를 이용해야한다.
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 물리적으로 떨어져 있는 오라클 서버와 연결통로 확보 
			con = DriverManager.getConnection(url,user,pw);
		}catch(ClassNotFoundException e)
		{
			System.out.println("ojdbc6.jar를 설정하지 않았다. 그래서 클래스를 못 찾는다.");
		
		}
		
		catch (Exception e) { // 비번이 맞지 않을 때
			e.printStackTrace();
		}
		return con;
	}
	
	/*
	 * 29 && 31번 호출 시 에러가 없다면 catch 블록은 실행하지 않는다.
	 */
	public static void freeConnection(ResultSet rs, PreparedStatement pstmt, Connection con){
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void freeConnection(PreparedStatement pstmt, Connection con){
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void freeConnection(ResultSet rs, CallableStatement cstmt, Connection con){
		try {
			if(rs !=null) rs.close();
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void freeConnection(CallableStatement cstmt, Connection con){
		try {
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

