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

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class advice_frag extends Fragment {


    public advice_frag() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    loopjAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Fetchloopj> array;

    SwipeRefreshLayout swipeRefreshLayout;
    EndlessRecyclerViewScrollListener scrollListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advice,container,false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.purp_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.purp_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        array = new ArrayList<>();
        adapter = new loopjAdapter(getActivity().getBaseContext(), array);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

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

                array = new ArrayList<Fetchloopj>();
                loadData(0);
                scrollListener.resetState();
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(getActivity(),data_activity.class);
                i.putExtra("title",array.get(position).title.toString());
                i.putExtra("image",array.get(position).img.toString());
                i.putExtra("data",array.get(position).data.toString());
                startActivity(i);
            }
        }));


        return view;
    }

    public void loadData(int page) {
        NetUtils.get_recom("?data=recommends&limit=10&page=" + (page+1), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray jarray = response;
                try {
                    for (int i = 0; i < jarray.length(); i++) {
                        array.add(new Fetchloopj(jarray.getJSONObject(i)));
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
