package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.wang.avi.AVLoadingIndicatorView;

public class Splash_Screen extends AppCompatActivity {

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    private void intent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);

                finish();

            }
        }, 3000);
    }
    public void alert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("خطا ! اتصال خود را بررسی کنید");
                alertDialogBuilder.setPositiveButton("تلاش مجدد",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                check();
                            }
                        });

        alertDialogBuilder.setNegativeButton("خروج",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void check(){
        if (isNetworkConnected()) {
            intent();


        }
        else if(!isNetworkConnected()){
            alert();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.show();
        check();

    }
}
