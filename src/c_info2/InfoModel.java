//인터페이스 페이지

package c_info2;

//  리팩터에서 인터페이스 만든거임
//원래는 이렇게 인터페이스를 먼저 만들어줘야함
import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoModel  {
	
	//친절하게 이렇게 아까만든 메소드 틀들을 만들어줌 
		//원래는 이걸 이렇게 먼저 만들어야하는데 우리는 초보

	//1번 함수 만든거임 사용자 입력값을 받아서 DB에 저장하는역활
	//INSERT INTO info_tab 이거는 사용자입력값을 받아서 디비를 저장하는역활로 만든거임
	//이렇게 무엇무엇마다 역활마다 함수를 하나씩 만들어줘야함
	void insertInfo(InfoVo vo) throws SQLException; //end of insertInfo

	//2번함수 만든거임selectAll 만들어주기 
	//"SELECT * FROM info_tab"; 라고 전체 검색 역활을 하기위해서 만들어준거임
	ArrayList<InfoVo> selectAll() throws SQLException;// end of selectAll
	
	
	//3번함수 전화번호를 넘겨받아서 해당하는 사람의 정보를 검색하는 함수만들기
		//전화 번호를 받은걸 InfoVo에 넣는다라는 뜻으로 맨앞에
	InfoVo selectByTell(String tel) throws SQLException;
	
	
	//4번함수 전화번호를 넘겨받아 해당하는 사람의 정보를 삭제
		//이건 그냥 int라고 지정
	int delete(String tel) throws SQLException; //여기 함수이름 은 model. 뒤에 붇는거임

	//5번 함수 전화번호 수정 / 틀린듯 수정해야함
	int update(InfoVo vo) throws SQLException;

}