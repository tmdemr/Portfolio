package utill;

/* author : 이동현 
*  비밀번호를 암호화하여 데이터 베이스에 저장하기 위한 함수를 정의한 클래스
*  2019-05-15 최종수정
*/

import java.security.MessageDigest;

public class SHA256 {
	
	

	public static String getHash(String input) {
		StringBuffer result = new StringBuffer(); // 문자열을 만들수 있는 객체
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes()); // update함수를 이용해 사용자가 입력한 값의 바이트를 가져옴
			byte bytes[] = md.digest();  // 해시 결과값을 digest함수를 이용해 byte 스트림으로 받아옴
			for(int i = 0; i<bytes.length; i++) { // 다시 문자열 형태로 화면에 출력
				result.append(
						Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		}
				
			catch(Exception e){
				e.printStackTrace();
			}
			
		return result.toString();
	
	
}
	
}
	
