package com.example.piyush.technospandan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class record extends AppCompatActivity {
    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";


    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        tableLayout= (TableLayout) findViewById(R.id.table1);
        Send obj=new Send();
        obj.execute("");
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Display Record Failed !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(record.this, "Displaying Data...", Toast.LENGTH_SHORT).show();
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
                    String query="SELECT * FROM `event_manager`;";
                    Statement stm = con.createStatement();
                    msg="Search Query Performed!!!";
                    ResultSet rs=stm.executeQuery(query);
                    while(rs.next())
                    {
                        final String n = rs.getString("Name");
                        final String branch =rs.getString("Branch");
                        final String batch =rs.getString("Batch");
                        final String event =rs.getString("Event");
                        final String date =rs.getString("Date");
                        final String mobile =rs.getString("Mobile");
                        final String id =rs.getString("Cand_ID");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TableRow tr=new TableRow(record.this);
                                TextView t1=new TextView(record.this);
                                t1.setText("\t"+id+"\t\t");
                                tr.addView(t1);
                                TextView t2=new TextView(record.this);
                                t2.setText("\t"+n+"\t\t");
                                tr.addView(t2);
                                TextView t3=new TextView(record.this);
                                t3.setText("\t"+branch+"\t\t");
                                tr.addView(t3);
                                TextView t4=new TextView(record.this);
                                t4.setText("\t"+batch+"\t\t");
                                tr.addView(t4);
                                TextView t5=new TextView(record.this);
                                t5.setText("\t"+event+"\t\t");
                                tr.addView(t5);
                                TextView t6=new TextView(record.this);
                                t6.setText("\t"+date+"\t\t");
                                tr.addView(t6);
                                TextView t7=new TextView(record.this);
                                t7.setText("\t"+mobile+"\t\t");
                                tr.addView(t7);
                                tableLayout.addView(tr, 1);

                                //Toast.makeText(record.this, " " + n + "\n", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(record.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
