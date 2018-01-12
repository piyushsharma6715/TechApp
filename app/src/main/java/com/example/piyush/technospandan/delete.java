package com.example.piyush.technospandan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class delete extends AppCompatActivity {

    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";
    String s;

    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        et= (EditText) findViewById(R.id.editText15);
    }
    public void deleteRecord(View view){
        s=et.getText().toString();
        Send obj =new Send();
        obj.execute("");
        et.setText("");
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Deletion Failed !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(delete.this, "Deleting Data...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection(DB_URL,USER,PASS);
                if(con==null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            msg="Connection goes wrong";
                        }
                    });

                }
                else{
                    String query="DELETE FROM `event_manager` WHERE Cand_Id='"+s+"';";
                    Statement stm = con.createStatement();
                    stm.executeUpdate(query);
                    msg="Deletion Operation Performed!!";
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
            Toast.makeText(delete.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }

}
