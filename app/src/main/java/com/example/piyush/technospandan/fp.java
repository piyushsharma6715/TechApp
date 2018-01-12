package com.example.piyush.technospandan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp);
    }
    public void show(View view){
        startActivity(new Intent(fp.this,record.class));
    }
    public void search(View view){
        startActivity(new Intent(fp.this,search.class));
    }
    public void delete(View view){
        startActivity(new Intent(fp.this,delete.class));
    }
    public void edit(View v){
        startActivity(new Intent(fp.this,edit.class));
    }

}
