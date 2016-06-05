package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import adapters.PoliciesAdapter;
import data.PlayerRepo;
import data.PolicyRepo;
import model.Model;
import util.Constants;
import util.SetupHelper;

public class PoliciesScreen extends AppCompatActivity {

    private PoliciesAdapter mAdapter;

    private PoliciesAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpList();

        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.policies_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 1);

        final Context context = this;
        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch(position){
                    case 0:{
                        final Intent cabinetIntent = new Intent(context, CabinetActivity.class);
                        startActivity(cabinetIntent);
                        finish();
                        break;
                    }
                    case 1:{
                        break;
                    }

                    case 2:{
                        finish();
                        break;
                    }

                    case 3:{
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        startActivity(issuesIntent);
                        finish();
                        break;
                    }

                    case 4:{
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        startActivity(pollsIntent);
                        finish();
                        break;
                    }

                    default: {; break;}
                }
            }
        });
    }

    private void setUpList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.policies_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new PoliciesAdapter(this, PolicyRepo.getUnadoptedPolicies(), this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        //Current policies list
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.current_policies_screen_recycler_view);
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);

        //Create and populate adapter
        mAdapter2 = new PoliciesAdapter(this, PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getTotalPolicies(), this);
        recyclerView2.setAdapter(mAdapter2);
        mAdapter2.notifyDataSetChanged();
    }


}
