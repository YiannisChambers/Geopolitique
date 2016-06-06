/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import adapters.MinistersAdapter;
import data.MinisterRepo;
import util.Constants;

/**
 * Ministers Activity to select a new Minister for a
 * specific Government Department
 */
public class MinistersActivity extends AppCompatActivity {

    private Context mContext;
    private int ministerPosition;
    private MinistersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the Minister that we're setting from the position passed in from the Intent
        Intent intent = getIntent();
        ministerPosition = intent.getIntExtra(Constants.INTENT_MINISTER_NUMBER, 0);

        mContext = this;

        setUpList();
    }

    /**
     * Creates and populates the RecyclerView of Ministers to choose from
     */
    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ministers_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, MinisterRepo.getAllMinistersNotInCabinet(), true, this, ministerPosition);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
