package com.example.cs160_sp18.prog3;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by taoong on 4/13/18.
 */

public class Landmark {
    public String name;
    public String distance;
    public int thumbnail;

    Landmark(String text, String username, int thumbnail) {
        this.name = text;
        this.distance = username;
        this.thumbnail = thumbnail;
    }

    protected void getDistance() {

    }
}
