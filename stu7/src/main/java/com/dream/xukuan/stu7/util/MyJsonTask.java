package com.dream.xukuan.stu7.util;

import android.os.AsyncTask;

import com.dream.xukuan.stu7.bean.MyNewsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/2/10.
 */
public class MyJsonTask extends AsyncTask<String,Void,List<MyNewsEntity>> {

    private OnLoadCompletedListener listener;

    public interface  OnLoadCompletedListener{
        void onLoadCompleted(List<MyNewsEntity> list);
    }

    public MyJsonTask(OnLoadCompletedListener listener){
        this.listener = listener;
    }
    @Override
    protected List<MyNewsEntity> doInBackground(String... params) {
        //下载数据
        String jsonString = HttpUtil.loadJson(params[0]);
        if(jsonString != null){
            List<MyNewsEntity> list = parseJson(jsonString);
            return list;
        }
        return null;
    }

    private List<MyNewsEntity> parseJson(String jsonString) {
        List<MyNewsEntity> list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                String content = jsonObject2.getString("description");
                String fromName= jsonObject2.getString("fromname");
                String imageUrl= jsonObject2.getString("img");
                String title= jsonObject2.getString("title");
                MyNewsEntity newsEntity = new MyNewsEntity(title, fromName, content, imageUrl);
                list.add(newsEntity);
            }

            return list;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<MyNewsEntity> result) {
        if(listener !=null){
            listener.onLoadCompleted(result);
        }
    }
}