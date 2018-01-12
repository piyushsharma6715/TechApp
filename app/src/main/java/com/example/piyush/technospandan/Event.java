package com.example.piyush.technospandan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Event extends AppCompatActivity {
    TextView t,t2;
    ImageView i;
    String s,tec[],tec1[];
    int[] techi={R.drawable.bite,R.drawable.constru,R.drawable.electro,R.drawable.pa};
    int[] nontechi={R.drawable.rock,R.drawable.spell,R.drawable.show,R.drawable.xyz};
    int[] sporti={R.drawable.cricket,R.drawable.football,R.drawable.badmin,R.drawable.chess};
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        t2= (TextView) findViewById(R.id.textView22);
        t=(TextView) findViewById(R.id.textView37);
        i= (ImageView) findViewById(R.id.imageView);
        setSupportActionBar(toolbar);
        s=getIntent().getStringExtra("type");
        index=getIntent().getIntExtra("index",-1);

        if(s.equals("technical")){
            i.setBackgroundResource(techi[index]);
            tec1=getResources().getStringArray(R.array.technical);
            tec=getResources().getStringArray(R.array.technicaldescription);
            t.setText(tec[index]);
            t2.setText(tec1[index]);
        }
        else if(s.equals("nontechnical")){
            i.setBackgroundResource(nontechi[index]);
            tec1=getResources().getStringArray(R.array.nontechnical);
            tec=getResources().getStringArray(R.array.nontechnicaldescription);
            t.setText(tec[index]);
            t2.setText(tec1[index]);
        }
        else{
            i.setBackgroundResource(sporti[index]);
            tec1=getResources().getStringArray(R.array.sports);
            tec=getResources().getStringArray(R.array.sportsdescription);
            t.setText(tec[index]);
            t2.setText(tec1[index]);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Event.this,register.class);
                i.putExtra("index",index);
                i.putExtra("type",s);
                startActivity(i);
            }
        });
    }

}
