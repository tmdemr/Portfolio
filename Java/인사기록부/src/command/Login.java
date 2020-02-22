package command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import proxy.MyServer;
import strategy.Client;
import strategy.UserClient;

public class Login extends MyServer{

	/*protected static Connection con;
	protected static Statement st; // 특정한 데이터 베이스에 SQL 문장을 실행하는 방법을 가능하게 해주는 객체
	protected static ResultSet rs; // 실행된 결과를 받아오는 객체
	protected static String secret = "2019001";
	protected static String ID;
	protected static int LEV = 0;
	
	public static void DBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 해당 오라클 드라이버를 메모리에 업로드 시킴
			con = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "ST2019001" , secret);
			st = con.createStatement();
						
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"데이터 베이스 연결오류\n" + e.getMessage());
			
		}
	}*/
	
	public static boolean DuplicateCheck(String userid) { // 아이디 중복체크 함수
		
		try {
			String SQL = "SELECT* FROM LOGIN WHERE ID = '" + userid + "'";
						
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"이미 사용중인 아이디 입니다!");
				return false;
			}
			
			else {
				return true;
				
			}
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"오류발생!\n" + e.getMessage());
		}
		return false;
		
	}
	
	public static void join(String userid,String userpwd,String phone,String question1,String question2) {  // 회원가입 함수
		try {
			
			String SQL = "INSERT INTO LOGIN(ID,PASSWORD,PHONE,QUESTION1,QUESTION2) VALUES('"+ userid +"','"+ userpwd +"','"+ phone +"','" + question1 +"','"+ question2 +"')";
			rs = st.executeQuery(SQL);
			JOptionPane.showMessageDialog(null,"회원가입 완료!");
			
		
		}catch(Exception e) {
		JOptionPane.showMessageDialog(null,"회원가입 실패!\n" + e.getMessage());
		
		
	}
	}
	
	public static boolean CheckNullData(String pwd,String number,String answer) { // 회원가입 필드들 중에 빈칸이 있는지 체크
		
		if(pwd.isEmpty() == true || pwd.isEmpty() == true || number.isEmpty() == true || answer.isEmpty() == true) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public static void FindID(String number,String answer,String answer2) { // 회원 아이디를 찾는데 사용하는 함수
		
		try {
			String SQL = "SELECT ID FROM LOGIN WHERE PHONE = '" + number + "'and (QUESTION1 ='" + answer +"' OR QUESTION2 = '" + answer2 +"')";
						
			rs = st.executeQuery(SQL);
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"아이디 : " + rs.getString(1));
				
				
			}
			else {
				JOptionPane.showMessageDialog(null,"일치하는 아이디가 없습니다!");
			}
	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"오류 발생 : " + e.getMessage());
		}
	
		
	}
	
	public static boolean FindPWD(String id,String number,String answer,String answer2) {      // 회원 비밀번호를 찾는데 사용하는 함수
		
		try {
			String SQL = "SELECT * FROM LOGIN WHERE ID = '" + id + "' and PHONE = '" + number + "'and (QUESTION1 ='" + answer +"' OR QUESTION2 = '" + answer2 +"')";
						
			rs = st.executeQuery(SQL);
			
			if(rs.next()) {
				
				return true;
				
				
				
			}
			else {
				
				
				return false;
			}
	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"오류 발생 : " + e.getMessage());
			return false;
			
		}
		
	}
	
	public static void SetPWD(String pwd,String id) {           // 비밀번호 재설정 함수
		
		try {
			String SQL = "update login set password = '" + pwd + "' where ID = '"+ id + "'";
			rs = st.executeQuery(SQL);
			JOptionPane.showMessageDialog(null,"비밀번호 변경에 성공하였습니다.");
				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"오류 발생 : " + e.getMessage());
		}
		
	}
	
	public static Users returnUser() {
		Users user = new Users();
		user.setID(ID);
		user.setLEV(LEV);
		
		return user;
	}
	
	public static boolean login(String loginid,String loginpwd) { // 사용자 로그인 함수
		try {
			String SQL = "SELECT* FROM LOGIN WHERE ID = '" + loginid + "'and PASSWORD ='" + loginpwd +"'";
			rs = st.executeQuery(SQL);
			if(rs.next()) {

				ID = rs.getString(1);
				LEV = rs.getInt(6);
						
				
				
				if(LEV == 1) {
					Users a = returnUser();
					
					JOptionPane.showMessageDialog(null,"Staff 계정으로 로그인 성공!");
					Client clients = new UserClient();	
					clients.setVisible(true);
				}
				else if(LEV == 2) {
					Users a = returnUser();
					JOptionPane.showMessageDialog(null,"Mannager 계정으로 로그인 성공!");
					Client clients = new UserClient();	
					clients.setVisible(true);
				}
				else {
					Users a = returnUser();
					JOptionPane.showMessageDialog(null,"CEO 계정으로 로그인 성공!");
					Client clients = new UserClient();	
					clients.setVisible(true);
				}
				
				
				
				
			}
			else {
				JOptionPane.showMessageDialog(null,"로그인 실패!");
			}
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"오류 발생" + e.getMessage());
		}
		return false;
	}
	
	
	
	
	
}
