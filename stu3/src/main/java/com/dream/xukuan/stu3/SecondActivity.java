package com.dream.xukuan.stu3;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner proSpinner;
    Spinner citySpinner;
    Spinner countrySpinner;
    ArrayAdapter<String> proAdapter;
    ArrayAdapter<String> cityAdapter;
    ArrayAdapter<String> countryAdapter;

    private static String TAG = "stu3.MainActivity";
    private List<String> proList;
    private List<String> proId;
    private List<String> cityNameList;
    private List<String> cityIdList;
    private List<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setView();
        serProvinceList();
        setListener();
        setAdapter();
    }

    private void setListener() {
        proSpinner.setOnItemSelectedListener(this);
        citySpinner.setOnItemSelectedListener(this);
        countrySpinner.setOnItemSelectedListener(this);
    }

    private void setCountryList(String cityId) {
        try {
            XmlResourceParser parser = this.getResources().getXml(R.xml.citys_weather);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT: {
                        countryList = new ArrayList<>();
                    }
                    break;
                    case XmlPullParser.START_TAG: {
                        String nodeName = parser.getName();
                        if ("d".equals(nodeName)) {
                            String countryId = parser.getAttributeValue(0);
                            if ("0101".equals(cityId) || "0201".equals(cityId)
                                    || "0301".equals(cityId)
                                    || "0401".equals(cityId)) {
                                while (countryId.substring(3).indexOf(cityId.substring(0, 2)) == 0) {
                                    String countyName = parser.nextText();
                                    countryList.add(countyName);
                                }
                            }
                            if(countryId.substring(3).indexOf(cityId) == 0){
                                countryList.add(parser.getText());
                            }
                        }
                    }
                    break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCityList(String provinceId) {
        try {
            XmlResourceParser parser = this.getResources().getXml(R.xml.citys_weather);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT: {
                        cityNameList = new ArrayList<>();
                        cityIdList = new ArrayList<>();
                    }
                    break;
                    case XmlPullParser.START_TAG: {
                        String nodeName = parser.getName();
                        if ("c".equals(nodeName)) {
                            String cityId = parser.getAttributeValue(0);
                            if (cityId.substring(0, 2).indexOf(provinceId) == 0) {
                                parser.next();
                                String cityName = parser.nextText();
                                cityIdList.add(cityId);
                                cityNameList.add(cityName);
                            }
                        }
                    }
                    break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setView() {
        proSpinner = (Spinner) findViewById(R.id.province);
        citySpinner = (Spinner) findViewById(R.id.city);
        countrySpinner = (Spinner) findViewById(R.id.county);
    }

    private void serProvinceList() {
        try {
            XmlResourceParser parser = SecondActivity.this.getResources().getXml(R.xml.citys_weather);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT: {
                        proList = new ArrayList<>();
                        proId = new ArrayList<>();
                    }
                    break;
                    case XmlPullParser.START_TAG: {
                        String nodeName = parser.getName();
                        if ("p".equals(nodeName)) {
                            String provinceId = parser.getAttributeValue(0);
                            proId.add(provinceId);
                            parser.next();
                            String provinceName = parser.nextText();
                            proList.add(provinceName);
                        }
                    }
                    break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        proAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, proList);
        proAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proSpinner.setAdapter(proAdapter);
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.province :{
                String provinceId = proId.get(position);
                setCityList(provinceId);
                cityAdapter.clear();
                cityAdapter.addAll(cityNameList);
                //省会城市的ID
                String cityId = cityIdList.get(0);
                setCountryList(cityId);
                countryAdapter.clear();
                countryAdapter.addAll(countryList);
                citySpinner.setSelection(0);
                countrySpinner.setSelection(0);
            }
            break;
            case R.id.city :{
                String cityId2 = cityIdList.get(position);
                setCountryList(cityId2);
                countryAdapter.clear();
                countryAdapter.addAll(countryList);

            }
            break;
            case R.id.county :{

            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
