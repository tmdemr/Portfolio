/**
 * 메멘토 패턴이 적용된 Information을 실제로 사용하는 클래스
 * final modify : 2019-05-26
 * @author : Kim Kwang Ho
 */

package memento;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	static Information info = new Information();
	static CareTaker caretaker = new CareTaker();
	static String secret = "2019001";
	static Connection con;
	static Statement st;
	static int EID = 0;
	static String Name = "abc";
	static String Surname = "ABC";
	static int Age = 0;
	
	static int i = 0;
	
	public static void pushed(JTextField textFieldEID) {
		/*EID, Name, Surname, Age*/
		try{	/**
				 * 커넥터 함수 불러와서 써야되는 문제와 Delete에서는 textfield를 찾아서 삭제하는데
				 * 여기에선 textfield값을 모름. 그래서 일단 모든 값 출력하도록 했고 순차적으로 저장되다가
				 * 마지막 데이터만 저장된 것을 확인할 수 있음. 따라서 바로 이전의 값만 복구가 가능.
				 * 즉, 실수로 데이터를 수정하거나 삭제했을 경우 바로 복구가 가능하게끔 ..
				 */
			con = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "ST2019001" , secret);
			String query = "select * from Employeeinfo where EID = '" + textFieldEID.getText() + "' ";
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				EID = rs.getInt("EID");
				Name = rs.getString("Name");
				Surname = rs.getString("Surname");
				Age = rs.getInt("Age");
			
				info.set_EID(EID)
					.set_Name(Name)
					.set_Surname(Surname)
					.set_Age(Age);
				caretaker.push(info.build());	//현재 Information의 상태정보를 가지는 Memento를 생성하여 CareTaker에 추가
			}
			st.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public static void poped() throws SQLException {
		//가장 최근에 생성된 Memento를 가지고 와서 상태정보를 복원
		info.RestorMemento(caretaker.pop());
		EID = info.getEID();
		Name = info.getName();
		Surname = info.getSurname();
		Age = info.getAge();
		
		String query = "insert into Employeeinfo (EID, Name, SurName, Age) values (?, ?, ?, ?) ";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, EID);
		pst.setString(2, Name);
		pst.setString(3, Surname);
		pst.setInt(4, Age);
		pst.execute();
		JOptionPane.showMessageDialog(null, "데이터 복구 완료");
		pst.close();
	}
}
