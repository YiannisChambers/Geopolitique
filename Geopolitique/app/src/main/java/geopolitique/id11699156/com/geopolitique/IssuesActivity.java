package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import adapters.IssuesAdapter;
import adapters.PoliciesAdapter;
import data.IssueRepo;
import model.Model;
import util.SetupHelper;

public class IssuesActivity extends AppCompatActivity {

    IssuesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpList();

        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.issues_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 3);

        final Context context = this;
        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0: {
                        final Intent cabinetIntent = new Intent(context, CabinetActivity.class);
                        startActivity(cabinetIntent);
                        finish();
                        break;
                    }
                    case 1: {
                        final Intent policiesIntent = new Intent(context, PoliciesScreen.class);
                        startActivity(policiesIntent);
                        finish();
                        break;
                    }

                    case 2: {
                        finish();
                        break;
                    }

                    case 3: {
                        break;
                    }

                    case 4: {
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        startActivity(pollsIntent);
                        finish();
                        break;
                    }

                    case 5: {
                        final Intent statisticsIntent = new Intent(context, StatisticsActivity.class);
                        startActivity(statisticsIntent);
                        finish();
                        break;
                    }

                    default: {
                        ;
                        break;
                    }
                }
            }
        });
    }

    private void setUpList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.issues_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new IssuesAdapter(this, IssueRepo.getAllUnresolvedIssues());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
