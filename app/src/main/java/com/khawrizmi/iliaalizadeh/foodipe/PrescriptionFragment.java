package com.khawrizmi.iliaalizadeh.foodipe;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class PrescriptionFragment extends Fragment {


    public PrescriptionFragment() {

    }
    RecyclerView recyclerView;
    adviceAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Fetchadvice> array;

    SwipeRefreshLayout swipeRefreshLayout;
    EndlessRecyclerViewScrollListener scrollListener;
    SQLiteHandler db;
    TextView advtitle;
    RelativeLayout relativeLayout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_prescription,container,false);
        db = new SQLiteHandler(getActivity().getApplicationContext());


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.advice_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.advice_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        array = new ArrayList<>();
        adapter = new adviceAdapter(getActivity().getBaseContext(), array);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        loadData(0);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadData(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                array = new ArrayList<>();
                loadData(0);
                scrollListener.resetState();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view,final int position) {


                advtitle=view.findViewById(R.id.adv_title);
                relativeLayout=view.findViewById(R.id.ex_re_lay);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(),adviceActivity.class);
                        i.putExtra("data",array.get(position).data.toString());
                        i.putExtra("title",array.get(position).title.toString());
                        startActivity(i);
                    }
                });

                advtitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(),adviceActivity.class);
                        i.putExtra("data",array.get(position).data.toString());
                        i.putExtra("title",array.get(position).title.toString());
                        startActivity(i);

                    }
                });





            }
        }));

        return view;
    }
    public void loadData(int page) {
        NetUtils.get_advices("?data=advices&limit=10&page=" + (page+1) + "&target=" + db.getUserDetails().get("name"), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray phones = response;

                try {
                    for (int i = 0; i < phones.length(); i++) {
                        array.add(new Fetchadvice(phones.getJSONObject(i)));
                    }
                    adapter.update(array);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }



            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                try {

                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

        });
    }

}
