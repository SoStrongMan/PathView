package com.example.mrxu.pathview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button attrMapBtn = (Button) findViewById(R.id.btn_attr_map);
        Button pieMapBtn = (Button) findViewById(R.id.btn_pie_map);
        Button taiChiBtn = (Button) findViewById(R.id.btn_tai_chi);
        Button fingerMapBtn = (Button) findViewById(R.id.btn_finger_map);
        attrMapBtn.setOnClickListener(this);
        pieMapBtn.setOnClickListener(this);
        taiChiBtn.setOnClickListener(this);
        fingerMapBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.btn_attr_map:
                i.setClass(this, AttributeMapActivity.class);
                break;
            case R.id.btn_pie_map:
                i.setClass(this, PieMapActivity.class);
                break;
            case R.id.btn_tai_chi:
                i.setClass(this, TaiChiActivity.class);
                break;
            case R.id.btn_finger_map:
                i.setClass(this, FingerMapActivity.class);
                break;
        }
        startActivity(i);
    }
}
