package client;

/* author : �豤ȣ
 * �ڵ� ������ : 2019/05/06
 * �ڵ� ������ ������ : 2019/05/06
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
		Socket c_socket = new Socket("127.0.0.1", 8888);	//���� �ּ� �� ��Ʈ ����
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
							System.out.println("����ȭ ���� ����");
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
			}	//������ ������ �� ���� ProcessThread�� ����ؼ� �־��� ���ڸ� ���
				//���� ���̳� input���� �����Ͽ� ��Ȳ�� �°� ����ϵ��� �����ؾ� ��
		}catch(Exception e1) {
			System.out.println(e1);
		}
		finally {
			c_socket.close();
		}
	}
}

