package server;

/* author : �豤ȣ
 * �ڵ� ������ : 2019/05/06
 * �ڵ� ������ ������ : 2019/05/06
 */

import java.net.*;

public class Server{
	public static void main(String[] args)throws Exception {
		ServerSocket s_Socket = new ServerSocket(8888);
		System.out.println("�������� ���� �Ϸ�!");
		try {
			while(true) {
				Socket socket = s_Socket.accept();	//���� ����
				ProcessThread MyThread = new ProcessThread(socket);	//socket�� ���� ProcessThread ��ü ����
				MyThread.start();
				System.out.println("Ŭ���̾�Ʈ ���� ����!");	//Ŭ���̾�Ʈ�� ���� ���� Ȯ��
			}
		}
		catch(Exception e) {
			s_Socket.close();
			System.out.println(e);
		}
	}
}