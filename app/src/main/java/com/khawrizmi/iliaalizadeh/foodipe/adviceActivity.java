package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class adviceActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView data_tv;
    TextView tool_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        data_tv=findViewById(R.id.adv_data);
        tool_text=findViewById(R.id.adv_tool_txt);
        Intent intent=getIntent();
        String title= intent.getExtras().getString("title");
        String data= intent.getExtras().getString("data");
        toolbar=findViewById(R.id.advtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        data_tv.setText(data);
        tool_text.setText(title);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.data_menu,menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.data_back)
        {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
