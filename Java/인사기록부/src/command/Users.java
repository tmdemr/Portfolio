package command;

/* author : 이동현 
*  로그인 기능에 필요한 DBConnecter / 회원가입 / 아이디,비밀번호 찾기 / 중복확인 등의 함수가 정의되어있는 클래스
*  2019-05-20 최종수정
*/

public class Users {
	
	int LEVEL;
	String ID;
	

	
	void setID(String ID) {
		this.ID = ID;
	}
	
	void setLEV(int LEVEL) {
		this.LEVEL = LEVEL;
	}
	
	public String getID() {
		return ID;
	}
	
	public int getLEV() {
		return LEVEL;
	}
	

		
}
	
	
	
	

