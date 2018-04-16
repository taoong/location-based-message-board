package com.example.cs160_sp18.prog3;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taoong on 4/13/18.
 */

public class Landmark {
    public String name;
    public String distance;
    public int thumbnail;

    Landmark(String name, String distance, int thumbnail) {
        this.name = name;
        this.distance = distance;
        this.thumbnail = thumbnail;
    }

    protected float getDistance(Location mLocation) {
        if (mLocation != null) {
            Location landmarkLocation = new Location("");
            List<String> latLongList = Arrays.asList(this.distance.split(", "));
            Log.d("0000", latLongList.get(0));
            Log.d("0000", latLongList.get(1));
            landmarkLocation.setLatitude(Double.parseDouble(latLongList.get(0)));
            landmarkLocation.setLongitude(Double.parseDouble(latLongList.get(1)));
            Log.d("1029384", Float.toString(landmarkLocation.distanceTo(mLocation)));
            return landmarkLocation.distanceTo(mLocation);
        } else {
            return 0;
        }
    }
}
