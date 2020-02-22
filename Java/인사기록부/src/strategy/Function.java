package strategy;
import java.awt.Font;
/**
 * author 최시창
 * 클라이언트 의 기능을 수행하는 클래스
 * 2019/05/24
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import memento.User;
import net.proteanit.sql.DbUtils;


public class Function {
	protected static Connection con;
	private Statement st; // 특정한 데이터 베이스에 SQL 문장을 실행하는 방법을 가능하게 해주는 객체
	private ResultSet rs; // 실행된 결과를 받아오는 객체
	private  String secret = "2019001";

	public static Connection getcon() {
		return con;
		
	}
	
	public void connect(){

			try{
				Class.forName("oracle.jdbc.driver.OracleDriver"); // 해당 오라클 드라이버를 메모리에 업로드 시킴
				con = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "ST2019001" , secret);
				st = con.createStatement();
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
				e.getStackTrace();
			}

	}
	
	public static void LoadAllData(JTable table) {
		try {
			String query = "select EID, Name, Surname, Age from Employeeinfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.getMessage();
			e.getStackTrace();
		}	
	}
	
	public static void LoadRestrictData(JTable table) {
		try {
			String query = "select EID, Name from Employeeinfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.getMessage();
			e.getStackTrace();
		}	
	}
	
	public static void LoadRestrictData2(JTable table) {
		try {
			String query = "select EID from Employeeinfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.getMessage();
			e.getStackTrace();
		}	
	}
	
	public void Search(JComboBox comboBoxSelect, JTextField textFieldSearch, JTable table ) {
		
		try {
			String selection = (String)comboBoxSelect.getSelectedItem();
	        String query = "select EID, Name, Surname, Age from Employeeinfo where " + selection +" = ? ";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, textFieldSearch.getText());
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}

	public static void New(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge ) {
		textFieldEID.setText("");
		textFieldName.setText("");
		textFieldSurname.setText("");
		textFieldAge.setText("");
		
	}	

	public static void Save(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		try {
			
			String query = "insert into Employeeinfo (EID, Name, SurName, Age) values (?, ?, ?, ?) ";
			PreparedStatement pst = con.prepareStatement(query);					
			pst.setString(1, textFieldEID.getText());
			pst.setString(2, textFieldName.getText());
			pst.setString(3, textFieldSurname.getText());
			pst.setString(4, textFieldAge.getText());					
			pst.execute();					
			JOptionPane.showMessageDialog(null, "데이터 저장완료");					
			pst.close();		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}	
		refreshTable(table);
		New(textFieldAge, textFieldAge, textFieldAge, textFieldAge);
	}

	public static void Delete(JTextField textFieldEID, JTable table ) {
		int action = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Delete", JOptionPane.YES_NO_OPTION);
		if(action == 0){
		try {
			
			String query = "delete from Employeeinfo where EID = '" + textFieldEID.getText() + "' ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.execute();
			JOptionPane.showMessageDialog(null, "데이터 삭제완료");
			pst.close();
			User.pushed(textFieldEID);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		refreshTable(table);
		}
	
	}
	
	public static void Update(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		try {
			
			String query = "update Employeeinfo set EID = '" + textFieldEID.getText() + "', Name = '" + textFieldName.getText() + "', Surname = '" + textFieldSurname.getText() +"', Age = '" + textFieldAge.getText() + "' where EID = '" + textFieldEID.getText() + "'";
			PreparedStatement pst = con.prepareStatement(query);									
			pst.execute();				
			JOptionPane.showMessageDialog(null, "데이터 업데이트");	
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		refreshTable(table);
		New(textFieldEID, textFieldName, textFieldSurname, textFieldAge);
		
	}

	
	
	
	
	public static void refreshTable(JTable table){
		
		try {
			String query = "select EID, Name, Surname, Age from Employeeinfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public static void grantRefreshTable(JTable table) {
		try {
			String query = "select ID,LEV from Login";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void fillComboBox(JComboBox comboBoxName){
		try {
			String query ="select * from Employeeinfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				comboBoxName.addItem(rs.getString("Name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void LoadList(JList<String> listName){
		try {
			String query = "select * from Employeeinfo ";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next()){
				DLM.addElement(rs.getString("Name"));
			}
			listName.setModel(DLM);
			pst.close();	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void GetComboBoxData(JComboBox comboBoxName,JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		try {
			
			String query = "select * from Employeeinfo where name = ? ";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, (String)comboBoxName.getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				textFieldEID.setText(rs.getString("EID"));
				textFieldName.setText(rs.getString("Name"));
				textFieldSurname.setText(rs.getString("Surname"));
				textFieldAge.setText(rs.getString("Age"));
			}
			pst.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void Clock(JLabel lblClock){
	
		Thread clock = new Thread()
		{
			public void run(){
				try {
				while(true){
				Calendar cal = new GregorianCalendar();
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				
				int second = cal.get(Calendar.SECOND);
				int minute = cal.get(Calendar.MINUTE);
				int hour = cal.get(Calendar.HOUR);
				
				month=month+1;
				
				lblClock.setText("Time " + hour +" : "+ minute + " : " + second +" Date " + year + " / " + month + " / " + day );
				sleep(1000);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e);
			}
		}
	};
	clock.start();
}

	public void MouseCliked(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		try {
			int row = table.getSelectedRow();
			String EID = (table.getModel().getValueAt(row, 0).toString());				
			String query = "select * from Employeeinfo where EID = '" + EID + "' ";
			PreparedStatement pst = con.prepareStatement(query);					
			ResultSet rs = pst.executeQuery();					
			while(rs.next()){
				textFieldEID.setText(rs.getString("EID"));
				textFieldName.setText(rs.getString("Name"));
				textFieldSurname.setText(rs.getString("Surname"));
				textFieldAge.setText(rs.getString("Age"));
			}
			pst.close();					
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}		
	}
	
}



