package com.example.cs160_sp18.prog3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CardView> mLandmarks = new ArrayList<CardView>();

    // UI elements
    Toolbar mToolbar;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landmark_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.landmark_recycler);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        layout = (RelativeLayout) findViewById(R.id.landmark_layout);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        setAdapterAndUpdateData();
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new MainAdapter(mLandmarks, this);
        mRecyclerView.setAdapter(mAdapter);

        // scroll to the last comment
        mRecyclerView.smoothScrollToPosition(mLandmarks.size() - 1);
    }

}
