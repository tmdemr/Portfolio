package proxy;

/**
 * 프록시 서버 Proxy
 */

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import command.*;

public class MyServer implements MyRemote {
	private static final Logger logger = Logger.getLogger(MyRemote.class.getName());
	protected static Connection con;
	protected static Statement st; // 특정한 데이터 베이스에 SQL 문장을 실행하는 방법을 가능하게 해주는 객체
	protected static ResultSet rs; // 실행된 결과를 받아오는 객체
	protected static String secret = "2019001";
	protected static String ID;
	protected static int LEV = 0;
	
	@Override
	public String sayHello() {
		
		return "abc";
	}

	public static void DBConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 해당 오라클 드라이버를 메모리에 업로드 시킴
			con = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "ST2019001" , secret);
			st = con.createStatement();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"데이터 베이스 연결오류\n" + e.getMessage());
			
		}
	}
	
	public static void main(String[] args) {
		try {
			//원격 객체 생성 및 stub 생성
			MyServer obj = new MyServer();
			MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(obj, 0);
			
			//클라이언트에서 서버의 stub을 찾을 수 있도록 rmiregistry에 등록하는 부분
			Registry registry = LocateRegistry.getRegistry("localhost");
			registry.bind("RemoteHello", stub);
			
			logger.info("바인딩 완료!");	//logger.info => 정보
			
		}catch(RemoteException | AlreadyBoundException ex) {
			logger.severe(ex.toString());	//logger.severe => 심각
		}
	}
}
