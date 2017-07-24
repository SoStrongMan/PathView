package com.example.mrxu.pathview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mrxu.pathview.view.TaiChiView;

public class TaiChiActivity extends AppCompatActivity {

    private static final int DELAY = 50;
    private int angle;
    private Handler mHandler;
    private TaiChiView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_chi);

        mView = (TaiChiView) findViewById(R.id.view);
        mHandler = new Handler();
        mHandler.post(mRunnable);
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, DELAY);
            mView.startRotate(angle += 5);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }
}
