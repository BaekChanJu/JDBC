package b_preparedStatement;

import java.sql.*;

//거의 많이쓰는 구문 !!!
public class insertEmp {

   public static void main(String[] args) {
      
      //메모리에 올리는 역활 위치 찾아서
      try {
         //1. 드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("드라이버로딩 성공"); //클래스 잘 찾아냈다 의미
         
         //2. 연결 객체 얻어오기
            //정보를 변수에 담기
         String url = "jdbc:oracle:thin:@192.168.0.48:1521:xe"; //이방법 고정
         String user = "scott";
         String pass = "tiger";
         
         Connection con = DriverManager.getConnection(url, user, pass);
         System.out.println("디비 연결 성공2");
         
         
         //---입력값
			String bonmyeng = "본명";
			int welgup = 10000;
			String jikup = "IT";
         
         //3.sql 문장
			String sql = "INSERT INTO emp(empno, ename, sal, job)  VALUES(5555, ?, ?, ?)";
     
         
         //4. sql 전송객체 (3에서 문장을 만들어주면 날리는 이게 필요)
				//거의 이거씀
         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setString(1, bonmyeng);
         stmt.setInt(2, welgup);
         stmt.setString(3, jikup);
         
         //5. sql 전송메소드
            // 1. ResultSet executeQuery()  : SELECT 문장에만
            // 2. int executeUpdate()  : INSERT/DELETE/UPDATE 문장에만 
         
         
         //PreparedStatement stmt = con.prepareStatement(sql); 를 위해서
         //해줬기에  아래  int result = stmt.executeUpdate(sql); 해주면 안됌
         //? 미완성 본이기 때문에 ? 는 stmt.setString(1, bonmyeng); 구문사용
         int result = stmt.executeUpdate(); //sql 을 넣어주면 안된다
         System.out.println(result + "행을 실행");
         
         
         //6. 닫기
         stmt.close();
         con.close();
      } catch (Exception e) { // Exception 모든예외 다걸러줌
         System.out.println("DB 실패 : " + e);
         
      } //end try.catch

   } //end of main
//위치 직접찾아서 넣어서 로딩 성공
}