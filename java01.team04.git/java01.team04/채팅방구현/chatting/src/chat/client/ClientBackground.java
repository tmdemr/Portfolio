package chat.client;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
 
public class ClientBackground {
 
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGUI gui;
    private String msg;
 
    public final void setGui(ClientGUI gui) {
        this.gui = gui;
    }
 
    public void connet() {
        try {
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("���� �����.");
             
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             
            out.writeUTF("�ȳ��ϼ���. Ŭ���̾�Ʈ���� �����߽��ϴ�.\n");
            System.out.println("Ŭ���̾�Ʈ : �޽��� ���ۿϷ�");
            
            while(in != null) {
            	msg = in.readUTF();
            	gui.appendMsg(msg);
            }
             
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connet();
    }

	public void sendMessage(String msg2) {
		try {
			out.writeUTF("Ŭ���̾�Ʈ : " + msg2);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
}
