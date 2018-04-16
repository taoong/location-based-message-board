package com.example.cs160_sp18.prog3;

import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Landmark> mLandmarks = new ArrayList<>();
    public ArrayList<Object> mLandmarkData = new ArrayList<>();

    // UI elements
    Toolbar mToolbar;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_feed);

        layout = (CoordinatorLayout) findViewById(R.id.landmark_layout);

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.landmark_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getLandmarks();

        setAdapterAndUpdateData();
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new MainAdapter(mLandmarks, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getLandmarks() {
        JSONObject jsonObj = null;

        try {
            String jsonStr = "[ {\"landmark_name\": \"Class of 1927 Bear\",\"coordinates\": \"37.869288, -122.260125\",\"filename\": \"mlk_bear\"},{\"landmark_name\": \"Stadium Entrance Bear\",\"coordinates\": \"37.871305, -122.252516\",\"filename\": \"outside_stadium\"},{\"landmark_name\": \"Macchi Bears\",\"coordinates\": \"37.874118, -122.258778\", \"filename\": \"macchi_bears\"}, {\"landmark_name\": \"Les Bears\",\"coordinates\": \"37.871707, -122.253602\",\"filename\": \"les_bears\"}, {\"landmark_name\": \"Strawberry Creek Topiary Bear \",\"coordinates\": \"37.869861, -122.261148\",\"filename\": \"strawberry_creek\"}, {\"landmark_name\": \"South Hall Little Bear\",\"coordinates\": \"37.871382, -122.258355\",\"filename\": \"south_hall\"}, {\"landmark_name\": \"Great Bear Bell Bears\",\"coordinates\": \"37.872061599999995,-122.2578123\",\"filename\": \"bell_bears\"}, {\"landmark_name\": \"Campanile Esplanade Bears\",\"coordinates\": \"37.87233810000001,-122.25792999999999\",\"filename\": \"bench_bears\"}]";
            JSONArray jsonArr = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArr.length(); i++) {
                jsonObj = jsonArr.getJSONObject(i);
                Landmark mNewLandmark = new Landmark(jsonObj.getString("landmark_name"), jsonObj.getString("coordinates"), getResources().getIdentifier(jsonObj.getString("filename"), "drawable", getPackageName()));
                mLandmarks.add(mNewLandmark);
            }

            Log.d("0", jsonObj.toString());
            Log.d("1", "ALSKDJLAKSDJLAKSDJLAKSDJLAKSDJLASKDJ");
        } catch (JSONException e) {
            Log.d("2", "BADBADBADBADBADBADBAD");
        }
    }

}
