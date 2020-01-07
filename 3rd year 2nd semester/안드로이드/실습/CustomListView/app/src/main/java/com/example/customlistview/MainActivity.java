package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ch.boye.httpclientandroidlib.HttpEntity;
import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.NameValuePair;
import ch.boye.httpclientandroidlib.client.HttpClient;
import ch.boye.httpclientandroidlib.client.entity.UrlEncodedFormEntity;
import ch.boye.httpclientandroidlib.client.methods.HttpPost;
import ch.boye.httpclientandroidlib.impl.client.DefaultHttpClient;
import ch.boye.httpclientandroidlib.message.BasicNameValuePair;


public class MainActivity extends AppCompatActivity {

    ListView list;

    String getMsg = "";
    InputStream is;

    CustomAdapter mAdapter;
    AsyncTask_Test test = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new CustomAdapter();

        list = findViewById(R.id.list);

        list.setAdapter(mAdapter);

        test = new AsyncTask_Test();
        test.execute();
    }

    class AsyncTask_Test extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                ArrayList<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("userId", "Yongjjang"));

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://113.198.235.231/Login.php");

                httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
                //위에서 작성한 list 객체 값들의 인코딩 형식을 변경

                HttpResponse response = httpclient.execute(httppost);
                //아래 코드부터는 요청에 대한 응답을 받아와서 처리하는 코드
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line = ""; // 결과를 한 줄씩 읽어서 저장할 변수
                getMsg = ""; //getMsg 변수 초기화(리스트 갱신등의 이유로 기존의 값이 남아 있지 않도록 하기 위해)
                //더 이상 읽어들일 내용이 없을 때
                while ((line = reader.readLine()) != null) {
                    getMsg += line;
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getMsg;
        }

        @Override
        protected void onPostExecute(String _result) {
            try {
                JSONObject root = new JSONObject(_result);
                JSONArray results = new JSONArray(root.getString("results"));
                for (int i = 0; i < results.length(); i++) {
                    JSONObject content = results.getJSONObject(i);
                    String id = content.getString("id");
                    String name = content.getString("name");
                    String img = content.getString("img");

                    mAdapter.addItem(id, name, img);
                    mAdapter.notifyDataSetChanged();
                }
                test = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
