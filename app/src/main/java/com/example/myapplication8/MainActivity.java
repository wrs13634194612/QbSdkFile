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

        findViewById(R.id.btn_pdf).setOnClickListener(v -> {
            getNewActivity("http://www.doublemax.com.tw/image/files/111%20pdf_test(1).pdf");
        });
        findViewById(R.id.btn_word).setOnClickListener(v -> {
            getNewActivity("https://www.zamzar.com/download.php?uid=a0ad826b05d977d6681b5fdfe65bdc-5126b03912ab1f7&targetId=pmVo4ovEokn3e0OeWCcFSQ_I_I&fileID=p1f261q05l1hq0etv1ufl9mv9ce4.docx");
        });
        findViewById(R.id.btn_excel).setOnClickListener(v -> {
            getNewActivity("http://www.jinzhongyi.net/usr/uploads/2020/03/2709539982.xlsx");
        });
    }

    private void getNewActivity(String fileUrl) {
        Intent intent = new Intent(this, FileDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fileUrl", fileUrl);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
