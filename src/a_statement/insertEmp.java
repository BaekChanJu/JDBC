package a_statement;

import java.sql.*;

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
         
         //3.sql 문장
         	//1-1.요구조건 - 10번 부서의 사원의 월급을 10% 인상해주세요
         	//이렇게 이부분만 수정하면서 사용가능
       // String sql = "UPDATE emp SET sal = sal*1.1 WHERE  deptno = 10 ";
         
         //1-2.요구조건 - 월급이 10000이상인 사원들 삭제
         String sql = "DELETE emp  WHERE sal>=10000";
        
         
         //4. sql 전송객체 (3에서 문장을 만들어주면 날리는 이게 필요)
         Statement stmt = con.createStatement();
         
         
         //5. sql 전송메소드
            // 1. ResultSet executeQuery()  : SELECT 문장에만
            // 2. int executeUpdate()  : INSERT/DELETE/UPDATE 문장에만 
         
         int result = stmt.executeUpdate(sql);
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