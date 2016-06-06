/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import adapters.MinistersAdapter;
import data.PlayerRepo;
import model.Cabinet;
import util.ToolbarHelper;

/**
 * Activity to display the current members of the Player's Government,
 * and to allow the player to select them.
 */
public class CabinetActivity extends AppCompatActivity {

    private MinistersAdapter mAdapter;
    private Cabinet mCabinet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the player's current Cabinet
        mCabinet = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet();

        //Set up the list of Ministers in the Player's Cabinet
        setUpList();

        //Set TextViews on screen to show the current Workload of the Cabinet
        setUpWorkloadTextViews();

        //Set up the bottom Toolbar
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.cabinet_screen_bottom_navigation);
        ToolbarHelper.setUpToolbar(bottomNavigation, this, 0);
    }

    private void setUpWorkloadTextViews(){
        //Set the Workload TextView to the total potential workload of the Cabinet
        TextView totalWorkload = (TextView) findViewById(R.id.cabinet_screen_total_workload_text);
        totalWorkload.setText(mCabinet.getTotalWorkload() + "");

        //Set the Current Workload TextView to the total potential workload of the Cabinet
        TextView currentWorkload = (TextView) findViewById(R.id.cabinet_screen_current_workload_text);

        //If the total Workload cannot handle the currently assigned Workload...
        if (mCabinet.getTotalCurrentWorkload() > mCabinet.getTotalWorkload()) {
            //... make the text Red as a warning.
            currentWorkload.setTextColor(Color.RED);
        } else {
            currentWorkload.setTextColor(Color.GREEN);
        }
        currentWorkload.setText(mCabinet.getTotalCurrentWorkload() + "");
    }

    /**
     * Set up the list of Ministers in the Cabinet
     */
    private void setUpList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cabinet_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, mCabinet, false, this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
