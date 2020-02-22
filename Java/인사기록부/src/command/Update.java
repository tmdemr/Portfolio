package command;

import strategy.Client;
import strategy.Function;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Update {

	void Updatable(JTextField textFieldEID,JTextField textFieldName, JTextField textFieldSurname, JTextField textFieldAge,JTable table) {
		try {
			
			String query = "update Employeeinfo set EID = '" + textFieldEID.getText() + "', Name = '" + textFieldName.getText() + "', Surname = '" + textFieldSurname.getText() +"', Age = '" + textFieldAge.getText() + "' where EID = '" + textFieldEID.getText() + "'";
			PreparedStatement pst = Function.getcon().prepareStatement(query);									
			pst.execute();				
			JOptionPane.showMessageDialog(null, "데이터 업데이트");	
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		Function.refreshTable(table);
		Function.New(textFieldEID, textFieldName, textFieldSurname, textFieldAge);
		
		//Function.Update(Client.getTextField(), Client.getTextField2(), Client.getTextField3(), Client.getTextField4(), Client.getTable());
	}
	
	void UpdateDisable() {
		JOptionPane.showMessageDialog(null,"권한이 없습니다!\n");
	}
}
