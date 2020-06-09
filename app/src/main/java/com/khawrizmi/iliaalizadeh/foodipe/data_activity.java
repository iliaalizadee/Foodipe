package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Intent;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class data_activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_activity);



        Intent intent=getIntent();
        String title= intent.getExtras().getString("title");
        Toolbar toolbar=findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.coll_tool_lay);
        collapsingToolbarLayout.setTitle(title);
        TextView txt_view=findViewById(R.id.data_tv);
            String data = intent.getExtras().getString("data");
            txt_view.setText(data);
            String image=intent.getExtras().getString("image");
            ImageView imageView=findViewById(R.id.data_img);

            Picasso.with(this).load(image).placeholder(R.drawable.load).into(imageView);





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
