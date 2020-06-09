package com.khawrizmi.iliaalizadeh.foodipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Fetchadvice {
    public int id;
    public String date, drname, data,title;


    public Fetchadvice(JSONObject jsonObject) {
        try {
            this.title= new String(jsonObject.getString("title").getBytes("UTF-8"));
            this.id = jsonObject.getInt("id");
            this.date = new String(jsonObject.getString("date").getBytes( "UTF-8"));
            this.drname = new String(jsonObject.getString("drname").getBytes( "UTF-8"));
            this.data = new String(jsonObject.getString("data").getBytes( "UTF-8"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
