package com.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class MemberDao {
	private MemberDao() {}
	private static MemberDao instance=new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection conn; //DB 연결 객체
	private PreparedStatement pstmt; //Query 작성 객체
	private ResultSet rs; //Query 결과 커서
	
	
	//성공 1, 실패 -1, 없음 0
	public int findByUsernameAndPassword(String username, String password) {
		//1. DB 연결
		conn = DBConnection.getConnection();
		
		try {
			//2. Query 작성
			pstmt = conn.prepareStatement("select * from member where username = ? and password = ?");
			
			//3. Query ? 완성 (index 1번 부터 시작)
			//setString, setInt, setDouble, setTimeStamp 등이 있음.
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			//4. Query 실행
			//(1) executeQuery() = select = ResultSet 리턴
			//(2) executeUpdate() = insert, update, delete = 리턴 없음.
			rs = pstmt.executeQuery();
			
			//5. rs는 query한 결과의 첫번째 행(레코드) 직전에 대기중
			//결과가 count(*) 그룹함수이기 때문에 1개의 행이 리턴됨. while문이 필요 없음.
			if(rs.next()) { //next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				//결과가 있다는 것은 해당 아이디와 비번에 매칭되는 값이 있다는 뜻.
				return 1; //로그인 성공
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return -1; //로그인 실패
	}
	
	//성공 1, 실패 -1, 
	public int save(MemberDTO member) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("insert into member values(?,?,?)");
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getNickname());

			pstmt.executeUpdate(); //return값은 처리된 레코드의 개수
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//성공 Vector<Member>, 실패 null
	public Vector<MemberDTO> findByAll(){
		conn = DBConnection.getConnection();
		Vector<MemberDTO> members = new Vector<>();
		try {
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setNickname(rs.getString("nickname"));

				members.add(member);
			}
			return members;
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Vector<String> findNickName() {
	    conn = DBConnection.getConnection();
	    Vector<String> nicknames = new Vector<>();
	    
	    try {
	        pstmt = conn.prepareStatement("select nickname from member");
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String nickname = rs.getString("nickname");
	            nicknames.add(nickname);
	        }
	        return nicknames;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public String findNicknameByUsernameAndPassword(String username, String password) {
	    conn = DBConnection.getConnection();
	    
	    try {
	        pstmt = conn.prepareStatement("select nickname from member where username = ? and password = ?");
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        rs = pstmt.executeQuery();
	        /**
	         *  메소드는 현재 레코드의 다음 레코드로 이동하고, 다음 레코드가 있으면 true를 반환하고,
	         *  레코드가 없으면 false를 반환
	         */
	        if (rs.next()) {
	            return rs.getString("nickname");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	
	public int deleteMemberByNickname(String nicknameToDelete) {
	    conn = DBConnection.getConnection();

	    try {
	        pstmt = conn.prepareStatement("DELETE FROM member WHERE nickname = ?");
	        pstmt.setString(1, nicknameToDelete);

	        int rowCount = pstmt.executeUpdate();

	        return rowCount; // 삭제된 레코드 수 반환

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    }

	    return -1; // 삭제 실패
	}
	
}