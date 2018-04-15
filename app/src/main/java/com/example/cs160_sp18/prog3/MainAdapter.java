package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainAdapter extends RecyclerView.Adapter {

    ArrayList<Landmark> mLandmarks;
    Context mContext;

    MainAdapter(ArrayList<Landmark> landmarks, Context context) {
        mLandmarks = landmarks;
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.landmark_layout, parent, false);
        return new LandmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Landmark landmark = mLandmarks.get(position);
        ((LandmarkViewHolder) holder).bind(landmark);
    }

    @Override
    public int getItemCount() {
        return mLandmarks.size();
    }
}

class LandmarkViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout mLandmarkLayout;
    public ImageView mThumbnailImageView;
    public TextView mNameTextView;
    public TextView mDistanceTextView;

    LandmarkViewHolder(View itemView) {
        super(itemView);
        mLandmarkLayout = itemView.findViewById(R.id.landmark_cell_layout);
        mThumbnailImageView = mLandmarkLayout.findViewById(R.id.thumbnail);
        mNameTextView = mLandmarkLayout.findViewById(R.id.username_text_view);
        mDistanceTextView = mLandmarkLayout.findViewById(R.id.date_text_view);
    }

    void bind(Landmark landmark) {
        mNameTextView.setText(landmark.name);
        mDistanceTextView.setText(landmark.distance);
    }

}
