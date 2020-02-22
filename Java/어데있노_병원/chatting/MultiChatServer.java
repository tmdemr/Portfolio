package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

//클라이언트로 부터 전송된 문자열을 받아서 다른 클라이언트에게 문자열을 보내주는 스레드

class EchoThread extends Thread{
	Socket socket;
	Vector<Socket> vec;
	public EchoThread(Socket socket, Vector<Socket> vec){
		this.socket = socket;
		this.vec = vec;
	}
	public void run(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String str =null;
			while(true){
				//클라이언트로 부터 문자열 받기
				str=br.readLine();
				//상대가 접속을 끊으면 break;
				if(str==null){
					//벡터에서 없애기
					vec.remove(socket);
					break;
				}
				//연결된 소켓을 통해서 다른 클라이언트에게 문자열 보내기
				sendMsg(str);				
			}
			
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}finally{
			try{
				if(br != null) br.close();
				if(socket != null) socket.close();
			}catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}
	
	//전송받은 문자열 다른 클라이언트들에게 보내주는 메서드
	public void sendMsg(String str){
		try{
			for(Socket socket:vec){
				//for문을 돌되 현재의 socket이 데이터를 보낸 클라이언트인 경우르 제외하고 
				//나머지 socket들에게만 데이터를 보낸다
				if(socket != this.socket){
					PrintWriter pw = 
						new PrintWriter(socket.getOutputStream(), true);
					pw.println(str);
					pw.flush();
					//단, 여기서 얻어온 소켓은 남의것이라서 닫으면 안됌
				}
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}


public class MultiChatServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket =null;
		//클라이언트와 연결된 소켓을 배열처럼 저장할 벡터객체 생성
		Vector<Socket> vec = new Vector<Socket>();
		try{
			server= new ServerSocket(4000);
			while(true){
				System.out.println("기다리는중..");
				socket = server.accept();
				//클라이언트와 연결된 소켓을 벡터에 담기
				vec.add(socket);
				//스레드 구동
				new EchoThread(socket, vec).start();
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}
