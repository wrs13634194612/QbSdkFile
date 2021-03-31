package com.example.myapplication8;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(this).runtime()
                .permission(Permission.READ_PHONE_STATE)
                .permission(Permission.Group.STORAGE)
                .onGranted(permissions -> {
                    X5Utils.initWebView(MainActivity.this);
                })
                .onDenied(permissions -> {
                    android.os.Process.killProcess(android.os.Process.myPid());
                })
                .start();

        findViewById(R.id.btn_show).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FileDisplayActivity.class);
            startActivity(intent);
        });


    }

}
