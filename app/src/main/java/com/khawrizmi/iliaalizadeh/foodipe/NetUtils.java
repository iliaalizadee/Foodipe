package com.khawrizmi.iliaalizadeh.foodipe;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetUtils {
    private static final String BASE_URL = "http://192.168.1.2/";
    private static final String RECOM_URL="http://192.168.1.2/recom/";
    private static final String CALORIES_URL="http://192.168.1.2/calories/";
    private static final String ADVICE_URL="http://192.168.1.2/advice/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void get_recom(String url,RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getRecomAbsoluteUrl(url), params, responseHandler);
    }
    public static void get_calories(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.get(getCaloriesAbsoluteUrl(url),params,responseHandler);
    }
    public static void get_advices(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.get(getAdvices(url),params,responseHandler);
    }


    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    private static String getRecomAbsoluteUrl(String relativeUrl){return RECOM_URL + relativeUrl;}
    private static String getCaloriesAbsoluteUrl(String relativeUrl){return CALORIES_URL + relativeUrl;}
    private static String getAdvices(String relativeUrl){return ADVICE_URL + relativeUrl;}

}

