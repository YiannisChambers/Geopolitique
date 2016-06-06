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
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import adapters.MinistersAdapter;
import data.MinisterRepo;
import data.PlayerRepo;
import data.RealmHelper;
import model.Minister;
import util.Constants;

public class MinistersActivity extends AppCompatActivity {

    private static Context mContext;
    private static int ministerPosition;
    private MinistersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ministerPosition = intent.getIntExtra(Constants.INTENT_MINISTER_NUMBER, 0);

        mContext = this;

        setUpList();
        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.ministers_screen_bottom_navigation);


        final Context context = this;
        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch(position){
                    case 0:{
                        break;
                    }
                    case 1:{
                        final Intent policiesIntent = new Intent(context, PoliciesScreen.class);
                        startActivity(policiesIntent);
                        break;
                    }

                    case 2:{
                        finish();
                        break;
                    }

                    case 3:{
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        startActivity(issuesIntent);
                        break;
                    }

                    case 4:{
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        startActivity(pollsIntent);
                        break;
                    }


                    default: {; break;}
                }
            }
        });
    }


    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ministers_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, MinisterRepo.getAllMinistersNotInCabinet(), true, this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void OnSetClick(View v, long ID){
        Minister minister = MinisterRepo.getMinisterByID(ID);

        RealmHelper.beginTransaction();
        PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().setMinister(ministerPosition, minister);
        RealmHelper.endTransaction();

        //Minister treasurer = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getTreasurer();

        Intent intent = new Intent(mContext, CabinetActivity.class);
        mContext.startActivity(intent);

        finish();
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

}
