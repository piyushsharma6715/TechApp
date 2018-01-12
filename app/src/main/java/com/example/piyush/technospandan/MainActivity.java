package com.example.piyush.technospandan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";
    private String eml,password;
    private EditText email,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= (EditText) findViewById(R.id.editText);
        pwd= (EditText) findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public void sgn(View view){
        startActivity(new Intent(MainActivity.this,Signup.class));
        finish();
    }
    public void adminlogin(View V)
    {
        startActivity(new Intent(MainActivity.this,admin.class));
    }

    public void lgn(View view){
        eml=email.getText().toString();
        password=pwd.getText().toString();
        Send obj=new Send();
        obj.execute("");
    }
    public void forget(View v){
        Intent intent=new Intent(MainActivity.this,Forget.class);
        startActivity(intent);
        finish();
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Please check email and password...";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Logging u in...", Toast.LENGTH_SHORT).show();
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
                    String query="SELECT * FROM record WHERE Email='"+eml+"' AND Password='"+password+"'";
                    Statement stm = con.createStatement();
                    final ResultSet rs=stm.executeQuery(query);

                    if(rs!=null) {
                        rs.next();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    msg="Login Successful!!!";
                                    Toast.makeText(MainActivity.this, "Welcome "+rs.getString("Name"), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    }

                    else if(rs==null){
                        msg = "Please Check your Email and Password...";
                    }
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
            Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
