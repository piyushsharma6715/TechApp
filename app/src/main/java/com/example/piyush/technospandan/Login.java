package com.example.piyush.technospandan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private LinearLayout layout;
    private TextView mTextMessage;
    private ListView mlistView;

    String technical[]={"Byte the Bits","Constructomania","Electromatrix","Padarth"};
    String nontechnical[]={"Rocket War","Spell Bee","ShowBuzz","XYZ"};
    String sport[]={"Cricket","Football","Badminton","Chess"};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.sign_out){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    layout.setBackground(ContextCompat.getDrawable(Login.this,R.drawable.tech));
                    mTextMessage.setText(R.string.title_technical);
                    ArrayAdapter adapter=new ArrayAdapter(Login.this,android.R.layout.simple_dropdown_item_1line,technical);
                    mlistView.setAdapter(adapter);
                    mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         Intent idn=new Intent(Login.this,Event.class);
                            idn.putExtra("type","technical");
                            idn.putExtra("index",i);
                            startActivity(idn);
                        }
                    });
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_non_technical);
                    ArrayAdapter adapter1=new ArrayAdapter(Login.this,android.R.layout.simple_dropdown_item_1line,nontechnical);
                    mlistView.setAdapter(adapter1);
                    mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent idn=new Intent(Login.this,Event.class);
                            idn.putExtra("type","nontechnical");
                            idn.putExtra("index",i);
                            startActivity(idn);
                        }
                    });
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_sports);
                    ArrayAdapter adapter2=new ArrayAdapter(Login.this,android.R.layout.simple_dropdown_item_1line,sport);
                    mlistView.setAdapter(adapter2);
                    mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent idn=new Intent(Login.this,Event.class);
                            idn.putExtra("type","sport");
                            idn.putExtra("index",i);
                            startActivity(idn);

                        }
                    });
                    return true;

            }
            return false;
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout= (LinearLayout) findViewById(R.id.container);
        mTextMessage = (TextView) findViewById(R.id.message);
        mlistView =(ListView)findViewById(R.id.listView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mlistView.setAdapter(new ArrayAdapter(Login.this,android.R.layout.simple_dropdown_item_1line,technical));
        layout.setBackground(ContextCompat.getDrawable(this,R.drawable.tech));
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent idn=new Intent(Login.this,Event.class);
                idn.putExtra("type","technical");
                idn.putExtra("index",i);
                startActivity(idn);
            }
        });
    }

}
