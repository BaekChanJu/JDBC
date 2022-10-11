// infoVo 생성자들 겟세터들 모아놓은곳
package c_info2;

//일단 항상 InfoVo 라는 무언가를 작동하기 위한 구문들
//겟세터, 생성자함수, toString 같은 부분들을 만들어 사용하는 공간

public class InfoVo {
	//선언은 다른사람이 접근하지 못하게 private 으로 지정을해둔다
	//private 으로 선언하면 대게는 셋게터를 많이 이용한다
	private String name;
	private String id;
	private String tel;
	private String gender;
	private int age;
	private String home;


	
	

	//1)기본생성자
	//생성자가1개라도 존재하면 자바가 기본으로 컴파일로 자동기본생성자를 안만들어준다
	//그래서 기본생성자 그냥한개 만들면된다
	public InfoVo() {

	}//end InfoVo() - 1)기본생성자


	
	
	
	//2) 생성자
	//자동으로 우클릭 소스에 유징필드 들어가면 자동으로 생성자를 만들어준다
	public InfoVo(String name, String id, String tel, String gender, int age, String home) {
		super();
		this.name = name;
		this.id = id;
		this.tel = tel;
		this.gender = gender;
		this.age = age;
		this.home = home;
		
	}//end InfoVo() - 2)생성자


	
	
	
	//3) toString
		//출력구문을 위해 만들었으며 우클릭 소스 toString에서 자동으로 만들 수 있다
		//가장 최상의 부모인 오브젝트에서 오버라이딩이 이미된 함수이다
	@Override
	public String toString() {
		return "InfoVo [name=" + name + ", id=" + id + ", tel=" + tel + ", gender=" + gender + ", age=" + age
				+ ", home=" + home + "]";
		
	}//end toString() - 3)toString



	
	
	//4)겟세터
		//데이터 넣다 뺏다용 으로 사용하고 우클릭 소스에서 자동으로 만들 수 있다
		//프라이빗은 그냥 넘길수 없기에 겟세터를 많이 이용한다

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}//end 겟세터들 - 4)겟세터

	
	

}//end InfoVo main class