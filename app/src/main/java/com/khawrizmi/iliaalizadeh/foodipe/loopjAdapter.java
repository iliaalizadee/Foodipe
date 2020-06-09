package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class loopjAdapter extends RecyclerView.Adapter<loopjAdapter.PhoneHolder>{

    List<Fetchloopj> mDataset;
    Context context;

    public loopjAdapter(Context context, List<Fetchloopj> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public PhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_style, parent, false);
        PhoneHolder dataObjectHolder = new PhoneHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(PhoneHolder holder, int position) {

        holder.title.setText(mDataset.get(position).title);



        Picasso.with(context)
                .load(mDataset.get(position).img)
                .placeholder(R.drawable.load)
                .into(holder.img);
    }

    public void update(List<Fetchloopj> list) {
        mDataset = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class PhoneHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;


        public PhoneHolder(View itemView) {
                super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.img);



        }
    }
}