package javaProjectTest;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_fshlc_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=z78AYjaYuuQ8e58v4VEYfL95N4Gf74fK%2FiJHHKns6tvcPv77DsZZzDZ0uuxE5y33xLhzkLYcJKifHI8bb3c4zg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("s_page","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*조회 시작 지점*/
        urlBuilder.append("&" + URLEncoder.encode("s_list","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 번에 조회될 최대 row 갯수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/
        urlBuilder.append("&" + URLEncoder.encode("fshlc_nm","UTF-8") + "=" + URLEncoder.encode("신동지낚시터", "UTF-8")); /*낚시터명*/
        urlBuilder.append("&" + URLEncoder.encode("fshlc_type","UTF-8") + "=" + URLEncoder.encode("저수지", "UTF-8")); /*낚시터유형*/
        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시 인동가산로 595-1", "UTF-8")); /*소재지도로명주소*/
        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시 신동 61", "UTF-8")); /*소재지지번주소*/
        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("36.055070", "UTF-8")); /*위도*/
        urlBuilder.append("&" + URLEncoder.encode("hardness","UTF-8") + "=" + URLEncoder.encode("128.283300", "UTF-8")); /*경도*/
        urlBuilder.append("&" + URLEncoder.encode("fshlc_phone_number","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*낚시터전화번호*/
        urlBuilder.append("&" + URLEncoder.encode("water_ar","UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*수면적*/
        urlBuilder.append("&" + URLEncoder.encode("kdfsh","UTF-8") + "=" + URLEncoder.encode("잉어+향어 등", "UTF-8")); /*주요어종*/
        urlBuilder.append("&" + URLEncoder.encode("aceptnc_co","UTF-8") + "=" + URLEncoder.encode("150", "UTF-8")); /*최대수용인원*/
        urlBuilder.append("&" + URLEncoder.encode("wtrc_fclty_type","UTF-8") + "=" + URLEncoder.encode("부유형+고정형", "UTF-8")); /*수상시설물유형*/
        urlBuilder.append("&" + URLEncoder.encode("use_charge","UTF-8") + "=" + URLEncoder.encode("13000원", "UTF-8")); /*이용요금*/
        urlBuilder.append("&" + URLEncoder.encode("main_point","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주요포인트*/
        urlBuilder.append("&" + URLEncoder.encode("safentl","UTF-8") + "=" + URLEncoder.encode("관리실+소화기+구명부환 및 구명줄+구급약품+방송시설 등", "UTF-8")); /*안전시설현황*/
        urlBuilder.append("&" + URLEncoder.encode("cvntl","UTF-8") + "=" + URLEncoder.encode("화장실+분리수거함 등", "UTF-8")); /*편익시설현황*/
        urlBuilder.append("&" + URLEncoder.encode("cfr_trrsrt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주변관광지*/
        urlBuilder.append("&" + URLEncoder.encode("phone_number","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관리기관전화번호*/
        urlBuilder.append("&" + URLEncoder.encode("institution_nm","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시청", "UTF-8")); /*관리기관명*/
        urlBuilder.append("&" + URLEncoder.encode("reference_date","UTF-8") + "=" + URLEncoder.encode("2018-06-30", "UTF-8")); /*데이터기준일자*/
        urlBuilder.append("&" + URLEncoder.encode("instt_code","UTF-8") + "=" + URLEncoder.encode("3740000", "UTF-8")); /*제공기관코드*/
        urlBuilder.append("&" + URLEncoder.encode("instt_nm","UTF-8") + "=" + URLEncoder.encode("경기도 수원시", "UTF-8")); /*제공기관명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}