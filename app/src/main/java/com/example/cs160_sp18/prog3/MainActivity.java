package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by taoong on 4/6/18.
 */

public class MainActivity extends AppCompatActivity implements LocationListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Landmark> mLandmarks = new ArrayList<>();
    private LocationManager locationManager;
    String username;
    Location mLocation;

    // UI elements
    Toolbar mToolbar;
    CoordinatorLayout layout;
    Button updateLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_feed);

        layout = (CoordinatorLayout) findViewById(R.id.landmark_layout);

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        updateLocation = (Button) findViewById(R.id.toolbar_btn);
        updateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location sampleLocation = new Location("");
                sampleLocation.setLatitude(37.869288d);
                sampleLocation.setLongitude(-122.260125d);
                onLocationChanged(sampleLocation);
                SystemClock.sleep(1500);
                setAdapterAndUpdateData();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.landmark_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            username = (String) intentExtras.get("username");
        }

        getLandmarks();

        setAdapterAndUpdateData();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("12345678999999", "BADBADBADBADBAD");
            Location sampleLocation = new Location("");
            sampleLocation.setLatitude(37.789d);
            sampleLocation.setLongitude(-122.084d);
            onLocationChanged(sampleLocation);

            setAdapterAndUpdateData();
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);

        setAdapterAndUpdateData();
    }



    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
        Log.d("123456789", location.toString());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new MainAdapter(mLandmarks, this, username, mLocation);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getLandmarks() {
        JSONObject jsonObj;

        try {
            String jsonStr = "[ {\"landmark_name\": \"Class of 1927 Bear\",\"coordinates\": \"37.869288, -122.260125\",\"filename\": \"mlk_bear\"},{\"landmark_name\": \"Stadium Entrance Bear\",\"coordinates\": \"37.871305, -122.252516\",\"filename\": \"outside_stadium\"},{\"landmark_name\": \"Macchi Bears\",\"coordinates\": \"37.874118, -122.258778\", \"filename\": \"macchi_bears\"}, {\"landmark_name\": \"Les Bears\",\"coordinates\": \"37.871707, -122.253602\",\"filename\": \"les_bears\"}, {\"landmark_name\": \"Strawberry Creek Topiary Bear \",\"coordinates\": \"37.869861, -122.261148\",\"filename\": \"strawberry_creek\"}, {\"landmark_name\": \"South Hall Little Bear\",\"coordinates\": \"37.871382, -122.258355\",\"filename\": \"south_hall\"}, {\"landmark_name\": \"Great Bear Bell Bears\",\"coordinates\": \"37.872061599999995, -122.2578123\",\"filename\": \"bell_bears\"}, {\"landmark_name\": \"Campanile Esplanade Bears\",\"coordinates\": \"37.87233810000001, -122.25792999999999\",\"filename\": \"bench_bears\"}]";
            JSONArray jsonArr = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArr.length(); i++) {
                jsonObj = jsonArr.getJSONObject(i);
                Landmark mNewLandmark = new Landmark(jsonObj.getString("landmark_name"), jsonObj.getString("coordinates"), getResources().getIdentifier(jsonObj.getString("filename"), "drawable", getPackageName()));
                mLandmarks.add(mNewLandmark);
            }
        } catch (JSONException e) {
            Log.d("0", "NOT ABLE TO LOAD JSON");
        }
    }

}
