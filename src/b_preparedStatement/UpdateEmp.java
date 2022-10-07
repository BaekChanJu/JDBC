package b_preparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateEmp {

	public static void main(String[] args) {
		
		//입력값
		//int sabun = 7698;
		//String saname = "아무개";
		//int salary = 15000;
		
		//7698 사원의 이름과 월급을 변경하세요
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버로딩 성공");

			//2. 연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.48:1521:xe"; 
			String user = "scott";
			String pass = "tiger";

			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("디비 연결 성공2");  
			
		
			int sabun = 7698;
			String saname = "아무개";
			int salary = 15000;

			// 3.sql 문장 만들기
			String sql = "UPDATE emp SET ename = ?,sal=? WHERE empno = ?";
			

			//4.전송객체 얻어오기
			 PreparedStatement stmt = con.prepareStatement(sql);
	         stmt.setString(1, saname);
	         stmt.setInt(2, salary);
	         stmt.setInt(3, sabun);


			//5. 전송
			
			int result = stmt.executeUpdate(); //sql 을 넣어주면 안된다
	         System.out.println(result + "행을 실행");

			//6. 닫기
				//닫히는 순서 중요함 반대로 닫힘	
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("실패 : " + e);
		}

	}

	}


