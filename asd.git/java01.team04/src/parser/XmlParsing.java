package parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlParsing {
	public static List<HospitalDB> hosData = new LinkedList<>(); // 병원의 데이터를 모은 연결 리스트 생성
	//public static List<HospitalDB> HosData = new ArrayList<HospitalDB>();

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
				String url = "http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytListInfoInqire?serviceKey=z78AYjaYuuQ8e58v4VEYfL95N4Gf74fK%2FiJHHKns6tvcPv77DsZZzDZ0uuxE5y33xLhzkLYcJKifHI8bb3c4zg%3D%3D";
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				System.out.println("파싱할 리스트 수 : "+ nList.getLength());

				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;

						String dutyaddr = getTagValue("dutyAddr", eElement);
						String dutyEmcls = getTagValue("dutyEmcls", eElement);
						String dutyEmclsName = getTagValue("dutyEmclsName", eElement);
						String dutyName = getTagValue("dutyName", eElement);
						String dutyTel1 = getTagValue("dutyTel1", eElement);
						String dutyTel3 = getTagValue("dutyTel3", eElement);
						String wgs84Lat = getTagValue("wgs84Lat", eElement);
						String wgs84Lon = getTagValue("wgs84Lon", eElement);

						HospitalDB e = new HospitalDB(dutyaddr, dutyEmcls, dutyEmclsName, dutyName, dutyTel1, dutyTel3, wgs84Lat, wgs84Lon);
						hosData.add(e); // 반복문 내에서 병원의 정보를 담은 객체를 생성해 연결 리스트에 담음.


						//(dutyaddr, dutyEmcls, dutyEmclsName, dutyName, dutyEmcls, dutyEmcls);
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());

						System.out.println("기관주소  : " + getTagValue("dutyAddr", eElement));
						System.out.println("응급의료기관분류  : " + getTagValue("dutyEmcls", eElement));
						System.out.println("응급의료기관분류명  : " + getTagValue("dutyEmclsName", eElement));
						System.out.println("기관명 : " + getTagValue("dutyName", eElement));
						System.out.println("대표전화   : " + getTagValue("dutyTel1", eElement));
						System.out.println("응급실전화  : " + getTagValue("dutyTel3", eElement));
						System.out.println("병원경도  : " + getTagValue("wgs84Lat", eElement));
						System.out.println("병원위도  : " + getTagValue("wgs84Lon", eElement));
					}	// for end
				}	// if end
				
				page += 1;
				System.out.println("page number : "+page);
				System.out.println(hosData);
				if(page > 12){	
					break;
				}
			}	// while end
			
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}	// main end
}	// class end
