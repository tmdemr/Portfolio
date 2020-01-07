package client;

/* author : 김광호
 * 코드 생성일 : 2019/05/06
 * 코드 마지막 수정일 : 2019/05/06
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import Database.DBConnection;
import GUI.LoginGUI;
import utill.SHA256;


public class clients {
	
	public static void main(String[] args)throws Exception {
		Socket c_socket = new Socket("127.0.0.1", 8888);	//소켓 주소 및 포트 설정
		try {
			String str;
			OutputStream ops = c_socket.getOutputStream();
			InputStream ips = c_socket.getInputStream();
			DataInputStream dis = new DataInputStream(ips);
			DataOutputStream dos = new DataOutputStream(ops);
			while(true) {
				LoginGUI frame = new LoginGUI();
				frame.setVisible(true);
				synchronized(c_socket) {
					try {
						c_socket.wait();
						DBConnection a = new DBConnection();
						if(a.getvalue() == 1) {
							c_socket.notify();
							System.out.println("동기화 제한 해제");
						}
						else {}
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				String sendMessage = "Test Set_Client";
				dos.writeUTF(sendMessage);
				str = dis.readUTF();
				System.out.println(str);
			}	//소켓이 닫히기 전 까지 ProcessThread와 계속해서 주어진 문자를 통신
				//조건 값이나 input값을 설정하여 상황에 맞게 출력하도록 변경해야 함
		}catch(Exception e1) {
			System.out.println(e1);
		}
		finally {
			c_socket.close();
		}
	}
}

