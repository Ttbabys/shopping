package com.gzcc.CodingGarfield.shopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.Login;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;

import java.util.List;

//欢迎界面
@SuppressLint("HandlerLeak")
public class SplashActivity extends FinalActivity {



    String Status="offline";
    private static final String S = "LoginIntoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.splash);
        //启动线程

        FinalDb finalDb = FinalDb.create(this);
        List<Login> list=finalDb.findAll(Login.class);
        if (list.size()!=0)
        {
            Status = list.get(0).getStatus();//判断是否online
        }

        if("online".equals(Status)) {
            Toast.makeText(getApplicationContext(), "自动登陆", Toast.LENGTH_LONG).show();
        }
        else if("offline".equals(Status)){
            Toast.makeText(getApplicationContext(), "跳转登陆", Toast.LENGTH_LONG).show();
        }
        Thread mt = new Thread(mThread);
        mt.start();
    }


    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            if((String)msg.obj == S) {
                //跳转

                if ("online".equals(Status)) {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, MainShoppingActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, LoginIntoActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
            }
        }
    };

    Runnable mThread = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Message msg = mHandler.obtainMessage();
            //延时3秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            msg.obj = S;
            mHandler.sendMessage(msg);
        }

    };

}