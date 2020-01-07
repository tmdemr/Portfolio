package server;

/* author : 김광호
 * 코드 생성일 : 2019/05/06
 * 코드 마지막 수정일 : 2019/05/06
 */

import java.net.*;

public class Server{
	public static void main(String[] args)throws Exception {
		ServerSocket s_Socket = new ServerSocket(8888);
		System.out.println("서버소켓 생성 완료!");
		try {
			while(true) {
				Socket socket = s_Socket.accept();	//서버 접속
				ProcessThread MyThread = new ProcessThread(socket);	//socket에 대한 ProcessThread 객체 생성
				MyThread.start();
				System.out.println("클라이언트 연결 승인!");	//클라이언트와 서버 연결 확인
			}
		}
		catch(Exception e) {
			s_Socket.close();
			System.out.println(e);
		}
	}
}