package com.dream.xukuan.xks2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

/**
 * @author
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] numbers = {2,4,5,7,1};
        Arrays.parallelSort(numbers);
    }
}
