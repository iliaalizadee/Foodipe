package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SendComment {

    private Context context;

    public SendComment(Context context) {
        this.context = context;
    }

    public void send(JSONObject requestJsonObject, final OnSendComplate onSendComplate){
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, "http://192.168.1.2/receiveData.php", requestJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean success= response.getBoolean("success");
                    onSendComplate.onSend(success);
                } catch (JSONException e) {
                    onSendComplate.onSend(false);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onSendComplate.onSend(false);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public interface OnSendComplate{
        void onSend(boolean success);
    }
}
