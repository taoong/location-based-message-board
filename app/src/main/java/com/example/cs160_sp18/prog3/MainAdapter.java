package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainAdapter extends RecyclerView.Adapter {

    ArrayList<Landmark> mLandmarks;
    Context mContext;
    String mUsername;

    MainAdapter(ArrayList<Landmark> landmarks, Context context, String username) {
        mLandmarks = landmarks;
        mContext = context;
        mUsername = username;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.landmark_layout, parent, false);
        return new LandmarkViewHolder(view, mUsername);
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
    public String title;

    LandmarkViewHolder(final View itemView, final String username) {
        super(itemView);
        mLandmarkLayout = itemView.findViewById(R.id.landmark_cell_layout);
        mThumbnailImageView = mLandmarkLayout.findViewById(R.id.thumbnail);
        mNameTextView = mLandmarkLayout.findViewById(R.id.location_name);
        mDistanceTextView = mLandmarkLayout.findViewById(R.id.location_distance);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), CommentFeedActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("username", username);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    void bind(Landmark landmark) {
        mNameTextView.setText(landmark.name);
        title = landmark.name;
        mDistanceTextView.setText(landmark.distance);
        mThumbnailImageView.setImageResource(landmark.thumbnail);
    }

}
