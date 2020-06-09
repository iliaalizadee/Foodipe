package com.khawrizmi.iliaalizadeh.foodipe;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adviceAdapter extends RecyclerView.Adapter<adviceAdapter.PhoneHolder>{

    List<Fetchadvice> mDataset;
    Context context;

    public adviceAdapter(Context context, List<Fetchadvice> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public PhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advicelist, parent, false);
        PhoneHolder dataObjectHolder = new PhoneHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(PhoneHolder holder, int position) {

        holder.title.setText(mDataset.get(position).title);
        holder.drname.setText(mDataset.get(position).drname);
        holder.date.setText(mDataset.get(position).date);






    }

    public void update(List<Fetchadvice> list) {
        mDataset = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class PhoneHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView drname;



        public PhoneHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.adv_title);
            date = (TextView) itemView.findViewById(R.id.datetxt);
            drname = (TextView) itemView.findViewById(R.id.drname);





        }
    }
}
