package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainAdapter extends RecyclerView.Adapter {

    ArrayList<CardView> mArray;
    Context mContext;

    MainAdapter(ArrayList<CardView> array, Context context) {
        mArray = array;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }
}

class LandmarkViewHolder extends RecyclerView.ViewHolder {

    TextView mCellTitle;

    LandmarkViewHolder(View itemView) {
        super(itemView);

    }

}
