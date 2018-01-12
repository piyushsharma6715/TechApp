package com.example.piyush.technospandan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        e1= (EditText) findViewById(R.id.editText12);
        e2= (EditText) findViewById(R.id.editText13);

    }
    public void admnlogn(View V)
    {
        String a=e1.getText().toString();
        String b=e2.getText().toString();
        if(a.equals("admin")&&b.equals("12345"))
        startActivity(new Intent(admin.this,fp.class));
        else
            Toast.makeText(this, "Login Failed!!!\nPlease Check Email and Password", Toast.LENGTH_SHORT).show();

    }
}
