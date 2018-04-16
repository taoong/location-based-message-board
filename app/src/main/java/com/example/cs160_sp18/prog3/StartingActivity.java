package com.example.cs160_sp18.prog3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by taoong on 4/15/18.
 */

public class StartingActivity extends AppCompatActivity {

    Toolbar mToolbar;
    CoordinatorLayout layout;
    Button startButton;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        layout = (CoordinatorLayout) findViewById(R.id.landmark_layout);

        mToolbar = (Toolbar) findViewById(R.id.starting_toolbar);
        setSupportActionBar(mToolbar);

        username = (EditText) findViewById(R.id.username_input);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("username", username.getText().toString());
                view.getContext().startActivity(intent);
            }
        });
    }
}

