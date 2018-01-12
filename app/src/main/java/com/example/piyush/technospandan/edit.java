package com.example.piyush.technospandan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class edit extends AppCompatActivity {

    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";


    private Spinner s1,s2,s3;
    private EditText e1,e2,e3,e4;
    private String id,name,date,mob,brnch,btch,evnt,branch[],batch[],event[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        e1= (EditText) findViewById(R.id.editText16);
        e2= (EditText) findViewById(R.id.editText17);
        e3= (EditText) findViewById(R.id.editText18);
        e4= (EditText) findViewById(R.id.editText19);
        s1= (Spinner) findViewById(R.id.spinner7);
        s2= (Spinner) findViewById(R.id.spinner8);
        s3= (Spinner) findViewById(R.id.spinner9);
        branch=getResources().getStringArray(R.array.branch);
        batch=getResources().getStringArray(R.array.batch);
        event=getResources().getStringArray(R.array.event);

        ArrayAdapter a1=ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter a2=ArrayAdapter.createFromResource(this,R.array.batch,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter a3=ArrayAdapter.createFromResource(this,R.array.event,android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(a1);
        s2.setAdapter(a2);
        s3.setAdapter(a3);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                brnch=branch[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                btch=batch[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                evnt=event[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void editRecord(View view){
        id=e1.getText().toString();
        name=e2.getText().toString();
        date=e3.getText().toString();
        mob=e4.getText().toString();
        Send obj=new Send();
        obj.execute("");
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Updation Failed !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(edit.this, "Updating Data...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection(DB_URL,USER,PASS);
                if(con==null){
                    msg="Connection goes wrong";
                }
                else{
                    String query="UPDATE `event_manager` SET `Name`='"+name+"',`Branch`='"+brnch+"',`Batch`='"+btch+"',`Event`='"+evnt+"',`Date`='"+date+"',`Mobile`='"+mob+"' WHERE`Cand_ID`='"+id+"'; ";
                    PreparedStatement stm = con.prepareStatement(query);
                    stm.executeUpdate();
                    msg="Updation Successful";
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
            Toast.makeText(edit.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
