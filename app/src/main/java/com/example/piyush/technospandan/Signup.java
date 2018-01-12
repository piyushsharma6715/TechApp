package com.example.piyush.technospandan;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Signup extends AppCompatActivity {

    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";

    private RadioButton r1;
    private EditText e_name,e_email,e_password,e_mobile;
    private String name,email,password,mobile,batch,branch,branches[],batches[],gender;
    private Spinner s_batch,s_branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e_name= (EditText) findViewById(R.id.editText6);
        e_email= (EditText) findViewById(R.id.editText7);
        e_password= (EditText) findViewById(R.id.editText8);
        e_mobile= (EditText) findViewById(R.id.editText9);
        r1= (RadioButton) findViewById(R.id.radioButton2);
        s_batch= (Spinner) findViewById(R.id.spinner4);
        s_branch= (Spinner) findViewById(R.id.spinner5);
        Resources resources=getResources();
        branches=resources.getStringArray(R.array.branch);
        batches=resources.getStringArray(R.array.batch);
        ArrayAdapter adapter,adapter1;

        adapter=ArrayAdapter.createFromResource(this,R.array.batch,android.R.layout.simple_spinner_dropdown_item);
        adapter1=ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_dropdown_item);
        s_branch.setAdapter(adapter1);
        s_batch.setAdapter(adapter);
         s_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 branch=branches[i];
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

         s_batch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                batch=batches[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
    public void mov(View v){
        name=e_name.getText().toString();
        email=e_email.getText().toString();
        password=e_password.getText().toString();
        mobile=e_mobile.getText().toString();
        if(r1.isChecked()){
            gender="male";
        }
        else
            gender="female";
        Send on=new Send();
        on.execute("");
        startActivity(new Intent(Signup.this,Login.class));
    }

    private class Send extends AsyncTask<String,String,String>{

        String msg="Insertion Failed !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Signup.this, "Inserting Data...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });

                Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
                if(con==null){
                    msg="Connection goes wrong";
                }
                else{
                    String query="INSERT INTO `record`(`Name`, `Email`, `Password`, `Branch`, `Batch`, `Mobile`, `Gender`) VALUES ('"+name+"','"+email+"','"+password+"','"+branch+"','"+batch+"','"+mobile+"','"+gender+"');";
                    Statement stm = con.createStatement();
                    stm.executeUpdate(query);
                    msg="Insertion Successful";
                }
                con.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return msg;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Signup.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
