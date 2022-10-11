// 고객정보 입력받는 DB창을 만들고
// 각 실행하는 버튼들이 눌렸을때 발생되는 이벤트 함수들을 만들어
//메인함수에서 받아와 사용하는 역활을 하는 공간

package c_info2;


//임포트 영역
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoView {

	//이건 프레임,버튼,텍스트간 멤버변수 선언
	JFrame f;
	JTextField tfName, tfId, tfTel, tfGender,  tfage, tfHome;
	JTextArea ta;
	JButton bAdd, bShow, bSearch, bDelete, bCancel, bExit;


	//메인 infoModelImpl 들을 가져와 사용하기위해 model이라는 변수만들어서 사용하기
	//이걸 비지니스 로직이라고함
	//그런데 인터페이스 만드는 란에 인터페이스 생성하면 InfoModel로 자동으로 바뀜
	//InfoModel > 인터페이스 란 메인클래스의 이름
	InfoModel model; 

	InfoView(){ // 객체생성은 생성자 함수 안에서 해야해서  model 객체생성도 안에서 해주자

		f = new JFrame("DBTest"); //타이틀이름,f변수로 사용

		tfName = new JTextField(10); //한줄입력객체생성, 크기지정
		tfId = new JTextField(10);
		tfTel = new JTextField(10);
		tfGender = new JTextField(10);
		tfage = new JTextField(10);
		tfHome = new JTextField(10);


		ta = new JTextArea(40,20);//여러줄입력객체생성, 크기지정

		bAdd = new JButton("Add"); //버튼만들기 객체생성, 버튼이름설정
		bShow = new JButton("Show");
		bSearch = new JButton("Search");
		bDelete = new JButton("Delete");
		bCancel = new JButton("Cancel");
		bExit = new JButton("수정하기");

		ta = new JTextArea(40,20);



		//생성자 안에 아까 맨위에서 만든 infoModelImpl model를 객체생성을 하자
		//model 객체생성 아까 예외 던지기 <infoModelImpl implements InfoModel>이걸로
		//클래스 선언자체에서 예외를 던졌기에 예외처리까지 해줘야한다!
		try {
			model = new infoModelImpl(); //모델객체생성 생성자함수 안에서!
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}//end try.catch

	} //end of InfoView - 생성자


	//3.화면구성하고 출력하는 함수 // 1011에 배운내용 미포함
	public void addLayout() {
		//전체프레임을 지정하기위한 BorderLayout 사용
		f.setLayout(new BorderLayout());

		//1.버튼
		JPanel pSouth = new JPanel(); //버튼들 판때기 대주기
		f.add(pSouth, BorderLayout.SOUTH); //아래클릭바

		pSouth.add(bAdd);
		pSouth.add(bShow);
		pSouth.add(bSearch);
		pSouth.add(bDelete);
		pSouth.add(bCancel);
		pSouth.add(bExit);

		//2.왼쪽한줄입력부분
		JPanel pWest = new JPanel();
		pWest.setLayout(new GridLayout(6,2)); //전체규격만들고 , 안에따로사용가능
		f.add(pWest, BorderLayout.WEST);

		pWest.add(new JLabel("Name", JLabel.CENTER));//어차피 한번지정해놓으면 안쓸꺼니 변수선언안하고 바로 생성가능
		//위 녀석의 역활은 그칸에 모형을 만들어주는것이고
		pWest.add(tfName); //모형만들었으니 출력구문이 필요
		pWest.add(new JLabel("ID", JLabel.CENTER));
		pWest.add(tfId);
		pWest.add(new JLabel("Tel", JLabel.CENTER));
		pWest.add(tfTel);
		pWest.add(new JLabel("Gender", JLabel.CENTER));
		pWest.add(tfGender);
		pWest.add(new JLabel("age", JLabel.CENTER));
		pWest.add(tfage);
		pWest.add(new JLabel("Home", JLabel.CENTER));
		pWest.add(tfHome);

		//3.여러줄 센터부분
		f.add(ta,  BorderLayout.CENTER); //센터

		//4.기본규격 만들기
		f.setBounds(100,100,500,350); //창 사이즈 규격
		f.setVisible(true); //실행은 true
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 빨간정지버튼 누르지 않아도 자동 종료해줌
	}


	//~버튼이 눌렸을때 처리되는 이벤트들의 집합소 함수
	void eventProc() {
		//고객이 add 버튼을 눌렀을때의 경우
		//바로이안에 코딩을 쓰는게 아니라 어떠한 작동들을 할 함수를 만들어 불러오자

		//add를 누르면 정보를 하나의 덩어리로 만들어 대려올 수 있는 insertDate 를 만들어 대려오자
		bAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				insertDate(); //1번째 동작임 고객이 add를 누르면 insertDate를 대려와라
				// insertDate 함수는 이 함수문 바깥에 만들어놓았음
			}
		}); //and bAdd

		bShow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selectAll(); //2번째 동작임 고객이 bShow를 누르면 selectAll를 대려와라
			}
		}); //and bShow

		//bSearch 버튼이 눌렸을때!!

		bSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selectByTell(); //3번째 동작임 selectByTell 메인함수에 이미있고
				//여기서 사용해야하니 불러와서 만들어야겠군

				//즉메인함수꺼 불러와서 사용하려면 여기서 동일명으로 선언하고 객체선언하고 던지고받고
				//해서 사용해야하구나!
			}
		}); //end bSearch

		tfTel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selectByTell(); //bSearch 안누르고 그냥 엔터쳤을때
			}
		}); //end tfTel


		//bDelete 가 눌러졌을때
		bDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				deleteByTell(); //4번째 동작임 4번째동작 구문이미 메인함수에있으니
				//여기서 불러와야겠군!
			}
		}); //end bSearch

		//bExit(수정하기)가 눌러졌을때
		bExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update(); //5째 동작임 5번째동작 구문이미 메인함수에있으니
				//여기서 불러와야겠군!
			}
		}); //end bSearch

	}//end of eventProc - 함수

	//5번 업데이트 틀려서 수정해야함
	void update() {
		// (1) 사용자가 입력한 값들을 얻어오기
		String name = tfName.getText();
		String id   = tfId.getText();
		String tel = tfTel.getText();
		String gender   = tfGender.getText();
		int age      = Integer.parseInt(tfage.getText());
		String home = tfHome.getText();

		InfoVo vo = new InfoVo ();  
		vo.setName(name);
		vo.setId(id);
		vo.setTel(tel);
		vo.setGender(gender);
		vo.setAge(age);
		vo.setHome(home);
		// (2) 모델단에 delete() 호출
		try {
			int de = model.update(vo);
			ta.setText(de + "행이 수정되었습니다.");

			// (3) 성공시 화면을 지우기 
			clearText();
		}catch (SQLException e) {
			ta.setText("수정실패 : " + e.getMessage());
		}
	}// end of update


	//deleteByTell() - delete를 누르면 번호가 삭제되게 만드는 함수
	// model.delete(?); 로 이미 메인에있는 delete함수를 불러와 사용
	void deleteByTell() {
		// (1) 사용자가 입력한 전화번호 값을 얻어오기
		String tel = tfTel.getText();
		// (2) 모델단에 delete() 호출
		try {
			int de = model.delete(tel);
			if (de == 0) {
				ta.setText("잘못된 전화번호입니다. 삭제되지 않았습니다.");
			}else if (de != 0) {
				ta.setText(de +"행이 삭제 되었습니다.");
			}
			// (3) 성공시 화면을 지우기 
			clearText();
		}catch (SQLException e) {
			ta.setText("삭제실패 : " + e.getMessage());
		}

	}

	//selectByTell() - 입력한 번호로 그사람의 모든정보 추출하는 함수
	//model.selectByTell(?) 로 이미 메인함수에있는 selectByTell를 불러와사용
	void selectByTell() {
		try {
			//1. 입력한 전화번호 값을 얻어오기
			String tel = tfTel.getText(); //받아온값을 tel에 저장
			//2.모델단에  selectByTell() 호출
			InfoVo vo = model.selectByTell(tel); 
			//메인에 있는selectByTell 에 값 던져주기 
			//그럼 결과 Vo로 받기까지  //인터페이스에서 정한함수이름임
			//3. 받은 결과를 각각의 텍스트필드에 지정(출력)
			tfName.setText(vo.getName());
			tfId.setText(vo.getId());
			tfTel.setText(vo.getTel());
			tfGender.setText(vo.getGender());
			tfage.setText(String.valueOf(vo.getAge()));
			tfHome.setText(vo.getHome());

		}catch(Exception ex) {
			ta.setText("전화번호 검색 실패 : " + ex.getMessage());
		}//end try

	}//end of selectByTell


	void selectAll() {
		//bshow 에서 불러오려고 만든 함수 > 말그대로 고객이 쇼 즉 정보를 보려고 show를 누르면
		//구현되도록 만들면 된다

		try {
			ArrayList<InfoVo> data = model.selectAll(); // model.selectAll 함수 지금 메인에서 가져왔는데
			//리턴타입 동일하게 어레이리스트 지정해줘서 data에 지정해준거임
			ta.setText("========검색결과=========\n\n");
			for(InfoVo vo :data) {
				ta.append(vo.toString()); //어팬드 쓰면 계속 그전에꺼 안없어지고 출력됨
			}//end for
		} catch (SQLException e) {
			ta.setText("검색 실패" + e.getMessage());
		}//end try

	}

	void insertDate() {
		//add를 누르면 사용자의 정보를 덩어리로 만들어 대려오는것
		//그후 모델을처리 메인으로 넘겨줘야함 infoVo를 만들어 두었기에 여기에서 사용될꺼임
		//바로 셋게터!
		//1.사용자의 입력값을 얻어와야함 (사용자가 텍스트에 친내용들)
		//ex)받을준비 변수명 = 받는값 겟텍스트
		String name = tfName.getText(); //사용자가 입력한 이름값
		String id = tfId.getText();
		String tel = tfTel.getText();
		String gender = tfGender.getText();
		int age = Integer.parseInt(tfage.getText());
		String home = tfHome.getText();

		//2.위 입력값들을 하나의 Vo에 지정을 해줘야함 InfoVo 프라이빗변수 란에 값들을 지정해주는 것
		//값 지정은 셋게터나 생성자로 하면됌
		//생성자로 하려면 () 안에 매겨변수안에 다 넣어주면 됌
		InfoVo vo = new InfoVo(); // 셋게터 사용하기 위해 객체 만들어주기!

		vo.setName(name);
		vo.setId(id);
		vo.setTel(tel);
		vo.setGender(gender);
		vo.setAge(age);
		vo.setHome(home);

		//3.model(메인클래스객체생성한거) 의 insertInfo() 호출
		//실질적 데이터 베이스에 넣어주는것임
		//근데 아까 예외던졌으니 예외처리까지 해줘야함
		//insertInfo 함수는 메인모델클래스에 있고 여기서는 model이라고 객체만든걸 이용해주면됨
		try {
			model.insertInfo(vo); // model.insertInfo(vo); / 메인에 있는 insertInfo에 보내주기
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//4. 화면의 입력값들을 다 지워주기 남기지않고 깔끔하게 
		clearText();

	}// end of insertDate - 함수



	void clearText() { // 화면의 입력값 없어주는 함수
		tfName.setText(null);
		tfId.setText(null);
		tfTel.setText(null);
		tfGender.setText(null);
		tfage.setText(null);
		tfHome.setText(null);
	}//end of clearText - 함수





	public static void main(String[] args) {

		InfoView info = new InfoView();
		info.addLayout();
		info.eventProc(); 


	}//end of main
}///end of InfoView main class

