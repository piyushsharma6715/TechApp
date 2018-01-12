package com.example.piyush.technospandan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Forget extends AppCompatActivity {

    private String emailText,mobileText;
    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";

    private EditText email,mob;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        email= (EditText) findViewById(R.id.editText10);
        mob= (EditText) findViewById(R.id.editText11);
        t= (TextView) findViewById(R.id.textView9);
    }
    public void search(View view){
        emailText=email.getText().toString();
        mobileText=mob.getText().toString();
        Send obj=new Send();
        obj.execute("");
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Please check your email or password !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Forget.this, "Fetching Record...", Toast.LENGTH_SHORT).show();
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
                    String query="SELECT * FROM record WHERE Email = '"+emailText+"' AND Mobile = '"+mobileText+"'";
                    Statement stm = con.createStatement();
                    final ResultSet rs=stm.executeQuery(query);

                    while(rs.next()){
                            final String pass = rs.getString("password");
                            final String name=rs.getString("Name");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText("Name:"+name+"\nPassword:"+pass+"\nEmail:"+emailText+"\nMobile:"+mobileText);
                                    //Toast.makeText(Forget.this, "Name:"+name+"\nPassword:"+pass+"\nEmail:"+emailText+"\nMobile:"+mobileText, Toast.LENGTH_SHORT).show();
                                    msg="Insertion Successful";

                            }
                        });
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
            Toast.makeText(Forget.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
