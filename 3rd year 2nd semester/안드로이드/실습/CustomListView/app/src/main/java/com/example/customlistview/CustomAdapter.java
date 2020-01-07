package com.example.customlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<CustomItem> listViewItemList = new ArrayList<>();
    Bitmap bitmap;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public CustomItem getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context mContext = viewGroup.getContext();
        //추가한 XML의 위젯들을 연결해주는 역할을 하는 메소드
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();

            //레이아웃 위에 신규 레이아웃 덮어씌우는 서비스
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout, null);

            holder.tv_id = view.findViewById(R.id.tv_id);
            holder.tv_name= view.findViewById(R.id.tv_name);
            holder.iv_image = view.findViewById(R.id.iv_image);


            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();

        CustomItem mData = getItem(i);

        holder.tv_id.setText(mData.getId());
        holder.tv_name.setText(mData.getName());
        final String img = mData.getImgUrl();

        Thread mThread = new Thread() { // 웹 관련 기능을 수행할땐 메인 스레드에서 작업 X
            @Override
            public void run() {
                try {
                    URL url = new URL(img);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // 서버로 부터 응답 수신
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is); // 인풋 스트림을 Bitmap으로 변환

                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        mThread.start(); // Thread 실행

        try {
            // 메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야한다
            // join()를 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리게 한다
            mThread.join();

            // 작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
            // UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지를 지정한다
            holder.iv_image.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return view;
    }

    class ViewHolder{
        public TextView tv_id;
        public TextView tv_name;
        public ImageView iv_image;
    }

    public void addItem(String _id, String _name, String _img){
        CustomItem ci = new CustomItem(_id, _name, _img);
        listViewItemList.add(ci);
    }
}
