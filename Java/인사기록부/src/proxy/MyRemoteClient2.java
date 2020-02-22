package proxy;

/**
 * 프록시 클라이언트 RealSubject
 */

import proxy.MyRemote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import GUI.LoginGUI;
import command.Login;
import command.Main;

public class MyRemoteClient2 {
	private MyRemote stub;
	private static final Logger logger = Logger.getLogger(MyRemoteClient2.class.getName());
	
	/**
	 * 클라이언트 실행부분
	 */
	public static void main(String[] args) {
		MyRemoteClient2 client = new MyRemoteClient2();
		client.lookup();
		
		MyServer.DBConnection();
		LoginGUI a = new LoginGUI();
		a.setVisible(true);
	}
	
	/**
	 * stub 부분
	 */
	public void lookup() {
		try {
			//rmiregistry에 서버IP, port를 설정
			Registry registry = LocateRegistry.getRegistry("localhost");
			
			//RemoteHello라는 이름을 찾아서 stub을 가져온다
			stub = (MyRemote) registry.lookup("RemoteHello");
			logger.info("원격 객체 스텁 찾았음!");
		}catch(NotBoundException | RemoteException ex) {
			logger.severe(ex.getMessage());
		}
	}
	public void callRemoteMethod() {
		try {
			//서버의 함수를 호출
			System.out.println("callRemote");
			String s = stub.sayHello();
			logger.info(s);
		}catch(RemoteException ex) {
			logger.severe(ex.getMessage());
		}
	}
	public static void DB() {
		try {
			MyRemote.DBConnection();
		}catch(RemoteException ex) {
			logger.severe(ex.getMessage());
		}
	}
}
