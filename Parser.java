import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;




import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



public class Parser {
	
	SAXBuilder builder = new SAXBuilder();
	

    public static void main(String[] args) {
        BufferedReader br = null;
        try{            
        	String urlstr = "http://openapi.gg.go.kr/FishingPlaceStatus?KEY=be1bef2d1c684ec3942bb5a9b23f35b1&pIndex=1&pSize=1";
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
            
            
            String result = "";
            String line;
            
            

        	//doc.setRootElement(thrRoot);
        	
        	Element FISHPLC_NM = new Element("FISHPLC_NM");
        	Element FISHPLC_AR = new Element("FISHPLC_AR");
        	
        	Element theRoot = new Element("SIGUN_CD");
            
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            
            
            while((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
            System.out.println(result);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
	
	
	

	

