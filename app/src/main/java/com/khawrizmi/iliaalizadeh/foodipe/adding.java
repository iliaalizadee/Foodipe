package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class adding extends AppCompatActivity {

    Button upload;
    ImageView imageview;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST= 99;
    Bitmap bitmap;
    String myurl = "http://192.168.1.2/myimage/upload.php";
    EditText title_edt;
    EditText ings_edt;
    EditText recipe_edt;
    EditText sender_edt;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.data_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.data_back){
            super.onBackPressed();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adding);
        Toolbar toolbar=findViewById(R.id.send_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title_edt=findViewById(R.id.title_edt);
        ings_edt=findViewById(R.id.ings_edt);
        recipe_edt=findViewById(R.id.recipe_edt);
        sender_edt=findViewById(R.id.sender_edt);
        upload = (Button) findViewById(R.id.upload_btn);
        imageview = (ImageView) findViewById(R.id.add_img_view);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaduserimage();
            }
        });


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission();
            }
        }

    }


    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(adding.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(adding.this, "لطفا به اپلیکیشن دسترسی بدهید", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(adding.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(adding.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(adding.this, "دسترسی داده شد!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(adding.this, "دسترسی داده نشد ):", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "انتخاب تصویر"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Toast.makeText(adding.this,"تصویر انتخاب شد",Toast.LENGTH_SHORT).show();
                imageview.setImageBitmap(bitmap);
                upload.setEnabled(true);
                upload.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploaduserimage(){


        RequestQueue requestQueue = Volley.newRequestQueue(adding.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("Myresponse",""+response);
                Toast.makeText(adding.this,"با موفقیت ارسال شد",Toast.LENGTH_LONG).show();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Mysmart",""+error);
                Toast.makeText(adding.this, "خطا در ارسال", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();

                String images = getStringImage(bitmap);
                Log.i("Mynewsam",""+images);
                param.put("image",images);
                param.put("title",title_edt.getText().toString());
                param.put("ings",ings_edt.getText().toString());
                param.put("text",recipe_edt.getText().toString());
                param.put("sender",sender_edt.getText().toString());
                return param;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(stringRequest);


    }


    public String getStringImage(Bitmap bitmap){
        Log.i("MyHitesh",""+bitmap);
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);


        return temp;
    }



}