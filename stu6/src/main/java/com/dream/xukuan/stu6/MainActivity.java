package com.dream.xukuan.stu6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author xk
 */
public class MainActivity extends AppCompatActivity {

    public  String baseUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey=";

    ImageView cityBtn;
    TextView cityNameText;
    TextView weather;
    TextView date;
    TextView wendu;
    TextView fengxiang;
    TextView yeType;
    TextView yeTopTP;
    TextView yeLowTP;
    TextView tmType;
    TextView tmTopTP;
    TextView tmLowTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViews();
        query("101010100");
    }

    private void query(String cityId) {
        String cityUrl = baseUrl + cityId;
        CityTask task = new CityTask();
        task.setOnLoadCompletedListener(new CityTask.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted(String result) {
                parseJson(result);
            }
        });
        task.execute(cityUrl);

    }

    private void parseJson(String json)  {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            wendu.setText(dataObject.get("wendu")+"℃");
            JSONObject yeObject = dataObject.getJSONObject("yesterday");
            yeType.setText(yeObject.getString("type"));
            yeLowTP.setText(yeObject.getString("low"));
            yeTopTP.setText(yeObject.getString("high"));
            JSONArray jsonArray = dataObject.getJSONArray("forecast");
            JSONObject todayObject = jsonArray.getJSONObject(0);
            weather.setText(todayObject.getString("type"));
            date.setText(todayObject.getString("date"));
            fengxiang.setText(todayObject.getString("fengxiang"));
            JSONObject tomorrowObject = jsonArray.getJSONObject(1);
            tmType.setText(tomorrowObject.getString("type"));
            tmLowTP.setText(tomorrowObject.getString("low"));
            tmTopTP.setText(tomorrowObject.getString("high"));
        } catch (JSONException e) {

        }

    }

    private void setViews() {
        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CityListActivity.class);
                startActivityForResult(intent,Constant.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == Constant.REQUEST_CODE){
            if (resultCode == Constant.RESULT_CODE){
                String cityId = data.getStringExtra("cityId");
                String cityName = data.getStringExtra("cityName");
                cityNameText.setText(cityName);
                query(cityId);
            }
        }
    }

    private void initViews() {
        cityBtn = (ImageView) findViewById(R.id.city_btn);
        cityNameText = (TextView) findViewById(R.id.address);
        date = (TextView) findViewById(R.id.date);
        weather = (TextView) findViewById(R.id.weather);
        wendu = (TextView) findViewById(R.id.wendu);
        fengxiang = (TextView) findViewById(R.id.fengxiang);
        yeType = (TextView) findViewById(R.id.yeType);
        yeTopTP = (TextView) findViewById(R.id.yeTopTP);
        yeLowTP = (TextView) findViewById(R.id.yeLowTP);
        tmType = (TextView) findViewById(R.id.tmType);
        tmTopTP = (TextView) findViewById(R.id.tmTopTP);
        tmLowTP = (TextView) findViewById(R.id.tmLowTP);
    }
}
