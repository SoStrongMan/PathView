package com.example.mrxu.pathview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mrxu.pathview.view.PieMapView;

public class PieMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_map);

        PieMapView view = (PieMapView) findViewById(R.id.view);
        view.setValues(
                new float[]{11, 22, 33, 44, 55},
                new int[]{0xffff0000, 0xff00ff00, 0xff0000ff, 0xff000000, 0xffcccccc});
    }
}
