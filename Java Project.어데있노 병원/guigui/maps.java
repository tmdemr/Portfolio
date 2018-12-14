package guigui;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;

public class maps {
	
		static int mpsize = 11;
		static String maker; 
		static String maker1;
		static String maker2;
		static String makerB = "&markers=color:yellow%7Clabel:S%7C35.158000,129.060005"
		+"&markers=color:yellow%7Clabel:D%7C35.146002,129.035095"
		+"&markers=color:yellow%7Clabel:R%7C35.141002,129.032095";
		static String makerR = "&markers=color:red%7Clabel:S%7C35.157710,129.059175"
		+"&markers=color:red%7Clabel:D%7C35.143002,129.034095";
		
		
		public void downloadMap(String location) {
			try {		
				String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=" 
				+ URLEncoder.encode(location,"UTF-8")  
				+ ",NY&zoom="+mpsize+"&size=750x750&maptype=roadmap"
				+ maker + "&key=AIzaSyBEllQNMhEO3PbwN1NgopB0K5C8kHEsVvs";
				//지도를 받아 온다 .
				URL url = new URL(imageURL);//주소를 저장하고
				InputStream is = url.openStream();
				OutputStream os = new FileOutputStream(location);
				byte[] b = new byte[2048];
				int length;
				
				while ((length = is.read(b)) != -1) {
					os.write(b,0,length);
				}
				is.close();
				os.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		public ImageIcon getMap(String location) {
			return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(750,750,java.awt.Image.SCALE_SMOOTH));
		}
		public void fileDelete(String fileName) {
			File f = new File(fileName);
			f.delete();
		}
};
	 
	