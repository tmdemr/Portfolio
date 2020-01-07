package command;

import strategy.Client;
import strategy.Function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import memento.User;

public class Delete {
	
	void Deletable(JTextField textFieldEID, JTable table) {
		int action = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Delete", JOptionPane.YES_NO_OPTION);
		if(action == 0){
		try {		

			
			String query = "delete from Employeeinfo where EID = '" + textFieldEID.getText() + "' ";
			PreparedStatement pst = Function.getcon().prepareStatement(query);
			pst.execute();
			JOptionPane.showMessageDialog(null, "데이터 삭제완료");
			pst.close();
			User.pushed(textFieldEID);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		Function.refreshTable(table);
		}
		//Function.Delete(Client.getTextField(), Client.getTable());
		
	}
	
	void DeleteDisable() {
		JOptionPane.showMessageDialog(null,"권한이 없습니다!\n");
	}
}
