package com.example.piyush.technospandan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class register extends AppCompatActivity {

    private static final String DB_URL="jdbc:mysql://192.168.43.62/signup";
    private static final String USER="piyush";
    private static final String PASS="piyush123456";


    private EditText name_e,date_e ,mob_e;
    private Spinner branch_s,batch_s,event_s;
    String name,branch,batch,event,date,mobile;
    String branches[]={"CSE","EE","MSME","CHE","MEC","IT"};
    String batches[]={"2K17","2k16","2k15"};
    String events[]={"bite the byte","constructomania","electromatrix","padarth","rocket war","spellbee","Show buzz","xyz","Cricket","FootBall","Badminton","Chess"};
    String type;
    int indx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        type=getIntent().getStringExtra("type").toString();
        indx=getIntent().getIntExtra("index",-1);
        name_e= (EditText) findViewById(R.id.editText3);
        date_e= (EditText) findViewById(R.id.editText4);
        mob_e= (EditText) findViewById(R.id.editText5);
        branch_s= (Spinner) findViewById(R.id.spinner);
        batch_s= (Spinner) findViewById(R.id.spinner2);
        event_s= (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter adapter,adapter1,adapter2;
        adapter=ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_dropdown_item);
        adapter1=ArrayAdapter.createFromResource(this,R.array.batch,android.R.layout.simple_spinner_dropdown_item);
        adapter2=ArrayAdapter.createFromResource(this,R.array.event,android.R.layout.simple_spinner_dropdown_item);
        branch_s.setAdapter(adapter);
        batch_s.setAdapter(adapter1);
        event_s.setAdapter(adapter2);
        if(type.equals("technical")){
            event_s.setSelection(indx);
        }
        else if(type.equals("nontechnical")) {
            event_s.setSelection(indx+4);
        }
        else {
            event_s.setSelection(indx+8);
        }

        branch_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                branch=branches[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                branch=branches[0];

            }
        });

        batch_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                batch=batches[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                batch=batches[0];

            }
        });

        event_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                event=events[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                event=events[0];

            }
        });

    }


    public void act(View view){
        name=name_e.getText().toString();
        date=date_e.getText().toString();
        mobile=mob_e.getText().toString();
        Send obj=new Send();
        obj.execute("");
        startActivity(new Intent(this,registerA.class));
    }
    private class Send extends AsyncTask<String,String,String> {

        String msg="Insertion Failed !!!";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(register.this, "Inserting Data...", Toast.LENGTH_SHORT).show();
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
                        }
                    });
                    msg="Connection goes wrong";
                }
                else{
                    String query="INSERT INTO `event_manager`(`Name`, `Branch`, `Batch`,`Event`, `Date`, `Mobile`) VALUES ('"+name+"','"+branch+"','"+batch+"','"+event+"','"+date+"','"+mobile+"');";
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
            Toast.makeText(register.this, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}

