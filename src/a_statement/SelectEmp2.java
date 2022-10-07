package a_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectEmp2 {

	public static void main(String[] args) {
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
			
			//---입력값
			String bonmyeng = "본명";
			int welgup = 10000;
			String jikup = "IT";


			// 3.sql 문장 만들기
			String sql = "INSERT INTO emp (empno, ename, sal,job)  "
				+ "   VALUES ( seq_temp2.nextval, '"+bonmyeng+"',"+welgup+",'"+jikup+"')";
			System.out.println(sql);

			//4.전송객체 얻어오기
			Statement stmt = con.createStatement();


			//5. 전송
			// 1. ResultSet executeQuery()  : SELECT 문장에만
			// 2. int executeUpdate()  : INSERT/DELETE/UPDATE 문장에만 

			ResultSet rs = stmt. executeQuery(sql);
			//반복해서 테이블 내용들을 하나하나씩 내려가면서 읽어야하게 반복문씀
			//몇개인지 모르니 while 문
			while(rs.next()) { //넥스트가 하나씩 내려간다는 의미
				int empno = rs.getInt("EMPNO"); 
				String ename = rs.getString("ENAME");
				int sal = rs.getInt("SAL");
				String job = rs.getString("JOB");
				System.out.println(empno + "/" + ename + "/" + sal + "/" + job);
			}







			//6. 닫기
				//닫히는 순서 중요함 반대로 닫힘
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("실패 : " + e);
		}

	}

}
