package com.khawrizmi.iliaalizadeh.foodipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Fetchcalorie {
    public int id;
    public String title, img, data,calorie;


    public Fetchcalorie(JSONObject jsonObject) {
        try {
            this.calorie= new String(jsonObject.getString("calorie").getBytes("UTF-8"));
            this.id = jsonObject.getInt("id");
            this.title = new String(jsonObject.getString("title").getBytes( "UTF-8"));
            this.img = new String(jsonObject.getString("img").getBytes( "UTF-8"));
            this.data = new String(jsonObject.getString("data").getBytes( "UTF-8"));


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



}
