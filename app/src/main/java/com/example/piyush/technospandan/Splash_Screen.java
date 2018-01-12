package com.example.piyush.technospandan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Thread t= new Thread(){
            public void run(){
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException ie){}
                finally {
                    Intent i=new Intent(Splash_Screen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();
    }
}
