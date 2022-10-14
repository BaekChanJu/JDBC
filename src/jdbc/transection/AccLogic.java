package jdbc.transection;

//#################################################################
//	테이블명 : account
//	account_num		계좌번호		varchar2(20)
//	customer		고객명			varchar2(20)
//	amount			계좌금액		int
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AccLogic 
{
	// 연결 객체 생성시 필요한 변수 선언
	String url;
	String user;
	String pass;

	//===============================================
	// 드라이버를 드라이버매니저에 등록
	public AccLogic() throws Exception{
		/////////////////////////////////////////////////////////
		// 1. 드라이버를 드라이버 매니저에 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		user = "scott";
		pass = "tiger";
	}


	//====================================================
	// 보내는 계좌번호와 받는 계좌번호와 계좌금액을 넘겨받아 
	//	보내는계좌에서 계좌금액을 빼고 받는계좌에서 계좌금액을 더한다
	public int moveAccount(String sendAcc, String recvAcc, int amount) throws SQLException
	{
		//sendAcc 출금계좌
		//recvAcc 입금계좌
		
//		 1. Connection 객체 생성
		Connection con =null; 
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			//@@2  ps.executeUpdate(); 하면 자동커밋됌 그걸 취소하기위한...?
			//즉 외부에서 가져온것들은 오토커밋이라고 자동으로 커밋되서 무언가 실수했을떄
			//방법이없음 만약 없는계좌로 돈을 보냈는데 금액에서는 돈이 빠져버림
			//그래서  con.setAutoCommit(false); 오토커밋을 풀어주고	
			
			//int reSend = ps.executeUpdate(); 빼는 부분에 이렇게처리
			//if(reSend  == 0) {
				//return -1; //무언가 잘못됫다 신호를 주기위해
			
			//if(reRecv ==0) { 받는 부분에 이렇게 처리
				//con.rollback();
				//return-1;
			//}
			
			//con.commit(); 잘됬으면 아래 수동으로 커밋처리 이렇게 해주면된다
			
			con.setAutoCommit(false);
			
		//	 3. 출금계좌에서 이체금액을 뺀다
				//amount - 내가 가지고있는 금액(잔액) account_num- 계좌번호
				//잔액은 잔액 - 잔액 이고 계좌에는 출금계좌를 선택
			String sqlsend = "UPDATE account SET amount = amount-? WHERE account_num = ?";
			PreparedStatement  ps = con.prepareStatement(sqlsend);
			ps.setInt(1, amount);
			ps.setString(2, sendAcc); //sendAcc 출금계좌
			int reSend = ps.executeUpdate();
			if(reSend  == 0) {
				return -1; //무언가 잘못됫다 신호를 주기위해
			}
		//	 4. 입금계좌에 이체금액을 더한다
			// 입금계좌는 당연히 잔액+들어온금액(잔액) 받으니 입금계좌
			String sqlRecv = "UPDATE account SET amount = amount+? WHERE account_num = ?";
			PreparedStatement px = con.prepareStatement(sqlRecv);
			px.setInt(1, amount);
			px.setString(2, recvAcc); // recvAcc 입금계좌
			int reRecv = px.executeUpdate();
			if(reRecv ==0) {
				con.rollback();
				return-1;
			}
			
		//	 6. 객체 닫기
			//- 만일 정상정인 경우는 0을리턴 도중잘못되면 -1을리턴
			
			con.commit(); // 둘다 정상처리되면 수동으로 커밋처리를 해주는거임
			
		}catch(Exception ex){
			System.out.println("이체실패 : " + ex.getMessage()  ) ;
			return -1;
		}finally{
			//6.닫기
			try { con.close();}catch(Exception ex) {}

		}
		return 0;
	} //end of moveAccount;

	
	
	//=======================================================
	//	보내는계좌번호와 받는계좌번호를 넘겨받아
	//  필요한 정보 검색
	public void confirmAccount(String sendAcc) throws Exception{


	}

}


