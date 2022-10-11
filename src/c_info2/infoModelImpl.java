//infoModelImpl 페이지
// 오라클과 자바를 연결하는 구간이며 인터페이스만든 함수에 영향을 받는 공간

package c_info2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class infoModelImpl implements InfoModel {

	//회사에서는 일반적으로 inal static 선언하며 상수이름은 대문자로 한다
	//오라클과 연결, 내컴퓨터와, 계정, 비번을 넣어준부분
	final static String DRIVER 	="oracle.jdbc.driver.OracleDriver"; 
	final static String URL 	= "jdbc:oracle:thin:@192.168.0.48:1521:xe";
	final static String USER 	= "scott";
	final static String PASS 	= "tiger";

	//생성자) 맨처음 드라이버 로딩을 위한 생성자
	public infoModelImpl() throws ClassNotFoundException { 
		// throws 로 예외처리 던지기 > try로 예외처리하면 사용자는 확인을 못하기때문에
		// 사용자에게도 알 수 있게 하기위해 예외를 던짐 / 던지면 받는 처리도 있어야한다


		//1. 드라이버 로딩
		//자바는 무저건 자바외적인거랑 연결되면 예외가 남 예외처리는 필수
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");

	} //end infoModelImpl - 생성자) 맨처음 드라이버 로딩을 위한 생성자





	//각 동작동작마다 함수를 여기 메인테스트 칸에서 오라클과 연동시켜 만들어줘야한다
	//지금 버튼마다 로 총 4번째 행동까지 만들었고 infoView에서 가져가서 사용중이다

	//1번째 함수만들기 - 사용자의 입력값을 받아서 DB에 저장을 해주는 역활
	//insertDate() 안에서  insertInfo 는 한번사용됨
	//저쪽함수랑 여기함수는 한동작당 한개 하고 생각하자
	public void insertInfo(InfoVo vo) throws SQLException { 
		//insertDate 에서 던진 에러 처리하고  InfoVo vo 넣어준값 메게변수로 받기

		//2. 연결 객체 얻어오기
		//여기도 연결하면 무저건 예외다발생 할태니 예외 던져라
		Connection con =null; //지역변수 초기화 해야함
		PreparedStatement ps = null; //그래서 변수선언은 블록 바깥에서 선언을 해주라!
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			//3.sql 문장 - #
			String sql = "  INSERT INTO info_tab(NAME, JUMIN, TEL, GENDER, AGE, HOME) VALUES(?,?,?,?,?,?)  ";
			//4.전송객체
			ps = con.prepareStatement(sql);
			//? 세팅 - #
			ps.setString(1, vo.getName()); //vo 안에있는 첫번쨰인 이름 을 세팅한거임 ?의 첫번째
			//vo를 매개변수로 받아왔으니까!
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getTel());
			ps.setString(4, vo.getGender());
			ps.setInt(5, vo.getAge());
			ps.setString(6, vo.getHome());
			//5.전송
			ps.executeUpdate();
		}finally{
			//6.닫기  - finally로 잡으면 뭐 예외가 났던 안났던 무저건 실행시키기 위해 잡아주기
			ps.close(); //파이널리 블록안에있어서 다른블록에 ps 사용을 못함 ps 선언을 바깥으로뺴주자
			con.close();
		}//and finally
	} //end of insertInfo






	//2번째함수 만들기 - selectAll
	//"SELECT * FROM info_tab"; 라고 전체 검색 역활을 하기위해서 만들어준거임
	public ArrayList<InfoVo> selectAll() throws SQLException{
		//2. 연결 객체 얻어오기
		//여기도 연결하면 무저건 예외다발생 할태니 예외 던져라
		Connection con =null;
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			//3.sql 문장
			String sql = "SELECT * FROM info_tab";
			//4.전송 객체 얻어오기
			ps = con.prepareStatement(sql);
			//5.전송
			rs = ps.executeQuery();
			//고객정보 몇개인지 모르니 어레이리스트
			//형은 InfoVo에 값 저장되있으니
			ArrayList<InfoVo> list = new ArrayList<InfoVo>();
			while(rs.next()) {
				InfoVo vo = new InfoVo();
				vo.setName(rs.getString("NAME"));
				vo.setId(rs.getString("JUMIN"));
				vo.setTel(rs.getString("TEL"));
				vo.setGender(rs.getString("GENDER"));
				vo.setAge(rs.getInt("AGE"));
				vo.setHome(rs.getString("HOME"));
				list.add(vo); //최종 갯수만큼 저장
			}
			return list;
		}finally{
			//6.닫기  - finally로 잡으면 뭐행시키기 위해 잡아주기
			rs.close();
			ps.close(); 
			con.close();
		}//end try,fal
	}// end of selectAll








	//멘 위에 메인타이틀에 새로운 추상 2개를 안만들어서 애러남
	//메인구문 빨간줄에서 add ~ 눌러주면 자동으로 아래 2개의 메소드를 만들어줌

	//3번째 함수만들기 - 전화번호를 넘겨받아서 해당하는 사람의 정보를 검색
	@Override
	public InfoVo selectByTell(String tel) throws SQLException {
		//2.연결객체 얻어오기
		Connection con =null; //지역변수 초기화 해야함
		PreparedStatement ps = null; 
		InfoVo vo = new InfoVo();
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			//3.sql 문장
			String sql ="SELECT * FROM info_tab WHERE tel=?";
			//4.전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, tel); //tel은 함수 매겨변수로 받아온 tel을 의미
			//5.전송
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				vo.setName(rs.getString("NAME"));
				vo.setId(rs.getString("JUMIN"));
				vo.setTel(rs.getString("TEL"));
				vo.setGender(rs.getString("GENDER"));
				vo.setAge(rs.getInt("AGE"));
				vo.setHome(rs.getString("HOME"));
			}
		}finally{
			//6.닫기 
			ps.close(); 
			con.close();
		}//end of try.fal
		return vo;

	}// end of selectByTell








	//4번함수
	//메소드명 delete
	//인자 : 전화번호
	// 리턴값 : 삭제한 행수
	@Override
	public int delete(String tel) throws SQLException {
		Connection con =null; //지역변수 초기화 해야함
		PreparedStatement ps = null; 
		int ret = 0;

		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			//3.sql 문장
			String sql ="DELETE  FROM info_tab WHERE tel = ?";
			//4.전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, tel); //tel은 함수 매겨변수로 받아온 tel을 의미
			//5.전송
			ret = ps.executeUpdate();

		}finally{

			//6.닫기 
			ps.close(); 
			con.close();
		}
		return ret;
	}//end of delete


	//5번째 함수
	//수정 exit가 눌렸을때 // 틀려서 수정해야함
	public int update(InfoVo vo) throws SQLException{ 
		//2.연결객체 얻어오기
		 Connection con = null;
	      PreparedStatement ps = null; 
	      int upd = 0;
	      
	      try {
	    	  con = DriverManager.getConnection(URL, USER, PASS);
	    	  
	    	// 3.sql 문장
	          String sql = " UPDATE info_tab SET name=?,jumin=?,gender=?,age=?,home=?  WHERE tel = ?";
	       // 4. 전송객체 얻어오기
	          ps = con.prepareStatement(sql);
	          ps.setString(3, vo.getTel()); //
	          
	          ps.setString(1, vo.getName());
	          ps.setString(2, vo.getId());
	          ps.setString(4, vo.getGender());
	          ps.setInt(5, vo.getAge());
	          ps.setString(6, vo.getHome());
	          // 5.전송
	          upd = ps.executeUpdate();
	      }finally {
	    	  // 6. 닫기 무조건 finally 안에 넣어주기
	          ps.close();
	          con.close();  
	      }
	      return upd ;

	}





}//end of infoModelImpl main class