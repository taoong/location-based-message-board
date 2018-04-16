package com.example.cs160_sp18.prog3;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

// Displays a list of comments for a particular landmark.
public class CommentFeedActivity extends AppCompatActivity {

    private static final String TAG = CommentFeedActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Comment> mComments = new ArrayList<Comment>();

    // UI elements
    EditText commentInputBox;
    RelativeLayout layout;
    Button sendButton;
    Toolbar mToolbar;
    String landmarkName;
    String username;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_feed);

        // hook up UI elements
        layout = (RelativeLayout) findViewById(R.id.comment_layout);
        commentInputBox = (EditText) layout.findViewById(R.id.comment_input_edit_text);
        sendButton = (Button) layout.findViewById(R.id.send_button);

        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            landmarkName = (String) intentExtras.get("title");
            username = (String) intentExtras.get("username");
            getSupportActionBar().setTitle(landmarkName + ": Posts");

            // create a reference to Firebase database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("comments").child(landmarkName);

            // create a database listener
            ValueEventListener myDataListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Set a breakpoint in this method and run in debug mode!!
                    // this will be called each time `someRef` or one of its children is modified

                    HashMap<String, Object> initialMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    if (initialMap != null) {
                        SortedMap<String, Object> value = new TreeMap<String, Object>(initialMap);
                        ArrayList<Comment> mNewComments = new ArrayList<Comment>();
                        for (String key : value.keySet()) {
                            HashMap<String, String> newComment = (HashMap<String, String>) value.get(key);
                            mNewComments.add(new Comment(newComment.get("text"), newComment.get("username"), newComment.get("date")));
                        }
                        mComments = mNewComments;
                        setAdapterAndUpdateData();
                        mRecyclerView.smoothScrollToPosition(mComments.size() - 1);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("0", "cancelled");
                }
            };
            myRef.addValueEventListener(myDataListener);
        }

        setOnClickForSendButton();

        setAdapterAndUpdateData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setOnClickForSendButton() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInputBox.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    // don't do anything if nothing was added
                    commentInputBox.requestFocus();
                } else {
                    // clear edit text, post comment
                    commentInputBox.setText("");
                    postNewComment(comment);
                }
            }
        });
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new CommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mAdapter);

        // scroll to the last comment
        // mRecyclerView.smoothScrollToPosition(mComments.size() - 1);

    }

    private void postNewComment(String commentText) {
        Comment newComment = new Comment(commentText, username, Long.toString(new Date().getTime()));
        myRef.child(newComment.date).setValue(newComment);
        setAdapterAndUpdateData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
