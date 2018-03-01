package com.dream.xukuan.stu10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {


    EditText nameEdit;
    EditText priceEdit;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameEdit = (EditText) findViewById(R.id.name_ed);
        priceEdit = (EditText) findViewById(R.id.price_ed);

    }

    private void checkExternalPermission(String name, String price) {
        int permission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        } else {
            saveToExternal(name, price);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(SecondActivity.this, "存储权限已申请", Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }

    public void click(View view) {
        final String name = nameEdit.getText().toString();
        final String price = priceEdit.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price)) {
            Toast.makeText(SecondActivity.this, "输入内容不能为空", Toast.LENGTH_LONG).show();
        } else {
            //输入不为空
            switch (view.getId()) {
                case R.id.internal_btn:
                    //保存到内部存储
                    saveToInternal(name, price);
                    break;
                case R.id.external_btn:
                    checkExternalPermission(name, price);
                    break;
                default:
            }
        }

    }

    public void clickRead(View view) {
        try {
            FileInputStream fis = new FileInputStream("product.txt");
            DataInputStream dis = new DataInputStream(fis);
            String name = dis.readUTF();
            int price = dis.readInt();
            Log.i("qwe", "clickRead: "+name+"--->"+price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToExternal(String name, String price) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File dir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
            File file = new File(dir,"save.txt");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(name.getBytes());
                fos.write(price.getBytes());
            } catch (Exception e) {

            }finally {
                if(fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {

                    }
                }
            }

        }else {
            Toast.makeText(SecondActivity.this,"SD卡不可用",Toast.LENGTH_SHORT).show();
        }

    }

    private void saveToInternal(String name, String price) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), "product.txt"));
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(name);
            dos.writeInt(Integer.parseInt(price));
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
