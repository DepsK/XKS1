package com.dream.xukuan.stu8.util;

import android.os.AsyncTask;

import com.dream.xukuan.stu8.bean.CityEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/2/26.
 */
public class MyJsonTask extends AsyncTask<String,Void,List<CityEntity>> {

    private OnLoadCompletedListener listener;

    public interface OnLoadCompletedListener{
        void onLoadCompleted(List<CityEntity> list);
    }

    public MyJsonTask(OnLoadCompletedListener listener){
        this.listener = listener;
    }

    @Override
    protected List<CityEntity> doInBackground(String... params) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            while (true){
                int ret = inputStream.read(buff,0,buff.length);
                if(ret == -1){
                    break;
                }
                bos.write(buff,0,ret);
            }
            String json = bos.toString();
            List<CityEntity> list = parsJson(json);
            return list;
        } catch (IOException e) {

        }
        return null;
    }

    private List<CityEntity> parsJson(String json) {
        List<CityEntity> cityEntityList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String imageUrl = jsonObject1.getString("fcover").replace("/","");
                String name = jsonObject1.getString("fname");
                String address = jsonObject1.getString("faddress");
                String price_pre = jsonObject1.getString("price_pre");
                String price_value = jsonObject1.getString("price_value");
                String price_unit = jsonObject1.getString("price_unit").replace("/","");
                cityEntityList.add(new CityEntity(imageUrl,name,address,price_pre,price_value,price_unit));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CityEntity> list) {
        if(listener != null){
            listener.onLoadCompleted(list);
        }
    }
}