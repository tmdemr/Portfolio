package proxy;

/**
 * 공통 인터페이스 Subject
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface MyRemote extends Remote {
	String sayHello() throws RemoteException;

	static void DBConnection() throws RemoteException{}
}
