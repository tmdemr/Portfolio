package command;

import strategy.Client;
import strategy.Function;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Insert {
	
	void Insertable(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		
		try {
			
			String query = "insert into Employeeinfo (EID, Name, SurName, Age) values (?, ?, ?, ?) ";
			PreparedStatement pst = Function.getcon().prepareStatement(query);					
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
		Function.refreshTable(table);
		Function.New(textFieldAge, textFieldAge, textFieldAge, textFieldAge);
		//Function.Save(Client.getTextField(), Client.getTextField2(), Client.getTextField3(), Client.getTextField4(), Client.getTable());
	}
	
	void InsertDisable() {
		JOptionPane.showMessageDialog(null,"권한이 없습니다!\n");
	}
}
