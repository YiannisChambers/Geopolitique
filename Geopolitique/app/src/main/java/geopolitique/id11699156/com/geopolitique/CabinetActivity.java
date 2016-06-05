package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import adapters.MinistersAdapter;
import data.PlayerRepo;
import model.Cabinet;
import model.Model;
import util.Constants;
import util.SetupHelper;

public class CabinetActivity extends AppCompatActivity{

   private static Context mContext;
    private MinistersAdapter mAdapter;
    private Cabinet mCabinet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;

        mCabinet = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet();
        setUpList();

        TextView totalWorkload = (TextView)findViewById(R.id.cabinet_screen_total_workload_text);
        totalWorkload.setText(mCabinet.getTotalWorkload() + "");

        TextView currentWorkload = (TextView)findViewById(R.id.cabinet_screen_current_workload_text);
        if(mCabinet.getTotalCurrentWorkload() > mCabinet.getTotalWorkload()){
            currentWorkload.setTextColor(Color.RED);
        }
        else
        {
            currentWorkload.setTextColor(Color.GREEN);
        }
        currentWorkload.setText(mCabinet.getTotalCurrentWorkload() + "");

        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.cabinet_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 0);

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
                        policiesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(policiesIntent);
                        finish();
                        break;
                    }

                    case 2:{
                        finish();
                        break;
                    }

                    case 3:{
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        issuesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(issuesIntent);
                        finish();
                        break;
                    }

                    case 4:{
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        pollsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(pollsIntent);
                        finish();
                        break;
                    }

                    case 5:{
                        final Intent statisticsIntent = new Intent(context, StatisticsActivity.class);
                        statisticsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(statisticsIntent);
                        finish();
                        break;
                    }

                    default: {; break;}
                }
            }
        });
    }

    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cabinet_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, mCabinet, false, this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void OnChangeClick(View v, int pos) {
        Intent intent = new Intent(mContext, MinistersActivity.class);
        intent.putExtra(Constants.INTENT_MINISTER_NUMBER, pos);
        mContext.startActivity(intent);
        finish();
    }


}
