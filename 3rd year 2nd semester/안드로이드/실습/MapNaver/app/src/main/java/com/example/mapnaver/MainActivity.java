package com.example.mapnaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    NaverMap mMap;
    String[] city = {"서울", "부산"};
    LatLng[] latlng = {new LatLng(37.566506, 126.977977), new LatLng(35.184912, 129.076744)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        String api = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?serviceKey=z78AYjaYuuQ8e58v4VEYfL95N4Gf74fK%2FiJHHKns6tvcPv77DsZZzDZ0uuxE5y33xLhzkLYcJKifHI8bb3c4zg%3D%3D&numOfRows=10&pageNo=1&itemCode=PM10&dataGubun=HOUR&searchCondition=MONTH";
        DownloadWebpageTask task = new DownloadWebpageTask();
        task.execute(api);
    } //onCreate

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                String txt = (String) downloadUrl((String) strings[0]);
                return txt;
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            boolean bSet_itemCode = false;
            boolean bSet_city = false;

            String itemCode = "";
            String pollution_degree = "";
            String tag_name = "";

            int cnt = 0;
            int cityNum = 0;

            tv.setText("");

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(s));

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) ;
                    else if (eventType == XmlPullParser.START_TAG) {
                        tag_name = xpp.getName();
                        if (tag_name.equals(itemCode))
                            bSet_itemCode = true;
                        if (tag_name.equals("seoul") || tag_name.equals(("busan")))
                            bSet_city = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_itemCode) {
                            itemCode = xpp.getText();

                            if (itemCode.equals("PM10")) {
                                cnt++;
                                bSet_itemCode = false;
                            }

                        }
                        if (bSet_city) {
                            pollution_degree = xpp.getText();

                            addInfo(latlng[cityNum], city[cityNum], pollution_degree);
                            //tv.append("" + cnt + " : " + tag_name + " , " + pollution_degree + "\n");
                            bSet_city = false;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) ;

                eventType = xpp.next();
                }
            }
            catch (IOException e){}
            catch (XmlPullParserException e) {}


        }
    }

    public void addInfo(LatLng latlng, String city, final String pollution_degree){
        Marker marker = new Marker();
        marker.setPosition(latlng);
        marker.setMap(mMap);

        marker.setCaptionText(city);

        InfoWindow infoWindow = new InfoWindow();

        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this){
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow){
                return pollution_degree;
            }
        });
        infoWindow.open(marker);
    }
}

