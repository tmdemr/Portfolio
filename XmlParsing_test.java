import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.net.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParsing_test {

    // tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

	public static void main(String[] args) {
		int page = 1;	// 페이지 초기값 
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서)
				String url = "https://openapi.gg.go.kr/FishingPlaceStatus?KEY=be1bef2d1c684ec3942bb5a9b23f35b1&pIndex=1&pSize=1";
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("row");
				//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());
						System.out.println("시군명  : " + getTagValue("SIGUN_NM", eElement));
						System.out.println("낚시터명  : " + getTagValue("FISHPLC_NM", eElement));
						System.out.println("이용료(원) : " + getTagValue("UTLZ_CHRG", eElement));
						System.out.println("낚시터 위치 도로명 주소  : " + getTagValue("REFINE_ROADNM_ADDR", eElement));
						System.out.println("낚시터 위치 지번 주소  : " + getTagValue("REFINE_LOTNO_ADDR", eElement));
					}	// for end
				}	// if end
				/*
				page += 1;
				System.out.println("page number : "+page);
				if(page > 12){	*/
					break;
//				}
			}	// while end
			
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}	// main end
}	// class end