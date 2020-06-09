package com.khawrizmi.iliaalizadeh.foodipe;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.OnPositiveButtonClickedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private OnPositiveButtonClickedListener listener = new OnPositiveButtonClickedListener() {
        @Override
        public void onClicked(int rate, String comment) {
            SendComment sendComment = new SendComment(MainActivity.this);
            JSONObject requestJsonObject= new JSONObject();
            try {
                requestJsonObject.put("rate", rate);
                requestJsonObject.put("comment", comment);
                sendComment.send(requestJsonObject, new SendComment.OnSendComplate() {
                    @Override
                    public void onSend(boolean success) {
                        if(success == true){
                            Toast.makeText(MainActivity.this, "بازخورد با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "بازخورد ارسال نشد", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mtoolbar = findViewById(R.id.m_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView btmview;
        btmview = findViewById(R.id.bnd);
        final PrescriptionFragment prescriptionFragment = new PrescriptionFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frgholder, prescriptionFragment);
        ft.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mtoolbar, 0, 0);
        drawerToggle.syncState();
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                 if (item.getItemId() == R.id.menuitem1) {
                                                                     new MaterialStyledDialog.Builder(MainActivity.this)
                                                                             .setStyle(Style.HEADER_WITH_TITLE)
                                                                             .setTitle("فودیپه")
                                                                             .setDescription("آیا از خروج اطمینان دارید؟")
                                                                             .setCancelable(true)
                                                                             .setPositiveText("بله")
                                                                             .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                                 @Override
                                                                                 public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                                     MainActivity.super.onBackPressed();
                                                                                 }
                                                                             })
                                                                             .setNegativeText("خیر")
                                                                             .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                                                 @Override
                                                                                 public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                                     dialog.cancel();
                                                                                 }
                                                                             })
                                                                             .show();
                                                                 } else if (item.getItemId() == R.id.menuitem2) {
                                                                     Intent i=new Intent(MainActivity.this,AboutActivity.class);
                                                                     startActivity(i);

                                                                 } else if (item.getItemId() == R.id.menuitem3) {
                                                                     new AppRatingDialog.Builder()
                                                                             .setPositiveButtonText("ثبت")
                                                                             .setNegativeButtonText("لغو")
                                                                             .setNoteDescriptions(Arrays.asList("خیلی بد", "بد", "تقریبا خوب", "خیلی خوب", "فوق العاده"))
                                                                             .setDefaultRating(2)
                                                                             .setTitle("به اپلیکیشن نظر دهید")
                                                                             .setDescription("لطفا رای خود از 5 را مشخص کنید و همچنین نظر و پیشنهادات خود را بنویسید")
                                                                             .setTitleTextColor(R.color.colorPrimaryDark)
                                                                             .setDescriptionTextColor(R.color.colorPrimaryDark)
                                                                             .setCommentTextColor(R.color.commentTextColor)
                                                                             .setCommentBackgroundColor(R.color.colorPrimaryDark)
                                                                             .setPositiveButtonClickedListener(listener)
                                                                             .create(MainActivity.this)
                                                                             .show();
                                                                 }
                                                                 return true;
                                                             }
                                                         });
        btmview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.prescription:
                    {
                        PrescriptionFragment prescriptionFragment=new PrescriptionFragment();
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.frgholder,prescriptionFragment);
                        ft.commit();
                        return true;
                    }
                    case R.id.knows:
                    {
                        advice_frag Advice_frag =new advice_frag();
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.frgholder, Advice_frag);
                        ft.commit();
                        return true;
                    }

                    case R.id.ings:
                    {
                        ings_frag Ings_frag=new ings_frag();
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.frgholder,Ings_frag);
                        ft.commit();
                        return true;
                    }
                    case R.id.calories:
                    {
                        calorie Calorie = new calorie();
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frgholder, Calorie);
                        ft.commit();
                        return true;
                    }
                    case R.id.account:
                    {
                        accountFragment accountfragment=new accountFragment();
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.frgholder,accountfragment);
                        ft.commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {

        new MaterialStyledDialog.Builder(this)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle("فودیپه")
                .setDescription("آیا از خروج اطمینان دارید؟")
                .setCancelable(true)
                .setPositiveText("بله")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeText("خیر")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}