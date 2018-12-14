package chat.server;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 
public class ServerBackGround {
     
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerGUI gui;
    private String msg;
    
    public final void setGui(ServerGUI gui) {
        this.gui = gui;
    }
 
    public void setting(){
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("���� �����...");
            socket = serverSocket.accept();
            System.out.println(socket.getInetAddress()+"���� �����߽��ϴ�.");
         
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             
            String msg = in.readUTF();
            System.out.println("Ŭ���̾�Ʈ�κ����� �޽��� : "+msg);
            gui.appendMsg(msg); //�޼��� �����ּ���
             
            while(in != null) {
            	msg = in.readUTF();
            	gui.appendMsg(msg);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
        ServerBackGround serverBackground= new ServerBackGround();
        serverBackground.setting();
             
    }

	public void sendMessage(String msg) {
		try{
			out.writeUTF("���� : " + msg);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
     
}
