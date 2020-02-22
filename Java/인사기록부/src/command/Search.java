package command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import strategy.Client;
import strategy.Function;

public class Search{

	
	
	void SearchAllData(JTable table) {
		
		try {
			String query = "select EID, Name, Surname, Age from Employeeinfo";
			PreparedStatement pst = Function.getcon().prepareStatement(query);
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
		
		//Function.LoadAllData(Client.getTable());
		
	}
	
	void SearchRestrictData(JTable table) {
		
		try {
			String query = "select EID, Name, Surname from Employeeinfo";
			PreparedStatement pst = Function.getcon().prepareStatement(query);
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
	
	void SearchRestrictData2(JTable table) {
		
		try {
			String query = "select EID from Employeeinfo";
			PreparedStatement pst = Function.getcon().prepareStatement(query);
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
	
}

