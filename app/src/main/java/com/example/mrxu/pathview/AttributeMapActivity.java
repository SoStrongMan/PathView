package com.example.mrxu.pathview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mrxu.pathview.view.AttributeMapView;

import java.util.Random;

public class AttributeMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_map);

        AttributeMapView mPathView = (AttributeMapView) findViewById(R.id.view);
        mPathView.setValues(rand());
    }

    /**
     * 产生随机数
     *
     * @return 随机数
     */
    private int[] rand() {
        int[] values = new int[6];
        for (int i = 0; i < 6; i++) {
            int value = new Random().nextInt(301) + 100;
            values[i] = value;
        }
        return values;
    }
}
