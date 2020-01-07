package server;

/* author : 김광호
 * 코드 생성일 : 2019/05/06
 * 코드 마지막 수정일 : 2019/05/06
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ProcessThread extends Thread{
	Socket client;
	ProcessThread(Socket client){
		this.client = client;
	}
	
	public void run() {
		try {
		InputStream ips = client.getInputStream();
		OutputStream ops = client.getOutputStream();
		DataOutputStream dos = new DataOutputStream(ops);
		DataInputStream dis = new DataInputStream(ips);
		while(true) {
			String p_str;
			p_str = dis.readUTF();			
			System.out.println(p_str);
			
			String sendMessage = "Test Set_Process";
			dos.writeUTF(sendMessage);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

