/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import adapters.IssuesAdapter;
import data.IssueRepo;
import util.ToolbarHelper;

/**
 * Issues Activity to display all the Issues the government must resolve
 */
public class IssuesActivity extends AppCompatActivity {

    IssuesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialise Issues list
        setUpList();

        //Initialise toolbar
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.issues_screen_bottom_navigation);
        ToolbarHelper.setUpToolbar(bottomNavigation, this, 3);
    }

    /**
     * Set up the Issue list to display
     */
    private void setUpList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.issues_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new IssuesAdapter(this, IssueRepo.getAllUnresolvedIssues(), this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
