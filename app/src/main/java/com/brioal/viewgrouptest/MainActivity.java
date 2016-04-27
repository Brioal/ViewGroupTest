package com.brioal.viewgrouptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brioal.viewgrouptest.util.BrioalUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrioalUtil.init(this);
        setContentView(R.layout.activity_main);
    }
}
