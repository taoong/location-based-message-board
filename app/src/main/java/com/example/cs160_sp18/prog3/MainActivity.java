package com.example.cs160_sp18.prog3;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Landmark> mLandmarks = new ArrayList<>();

    // UI elements
    Toolbar mToolbar;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_feed);

        // TODO: replace this with the name of the landmark the user chose
        String landmarkName = "test landmark";

        // sets the app bar's title
        setTitle(landmarkName + ": Posts");

        layout = (CoordinatorLayout) findViewById(R.id.landmark_layout);

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.landmark_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        makeTestLandmarks();

        setAdapterAndUpdateData();
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new MainAdapter(mLandmarks, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void makeTestLandmarks() {
        String randomString = "hello world hello world ";
        Landmark test1 = new Landmark(randomString, randomString);
        Landmark test2 = new Landmark(randomString, randomString);
        Landmark test3 = new Landmark(randomString, randomString);
        Landmark test4 = new Landmark(randomString, randomString);
        mLandmarks.add(test1);
        mLandmarks.add(test2);
        mLandmarks.add(test3);
        mLandmarks.add(test4);
    }

}
