/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.LinkedList;

import data.PlayerRepo;
import data.PolicyRepo;
import data.RealmHelper;
import model.Player;
import model.Policy;
import util.Constants;
import util.NumberHelper;
import util.SetupHelper;

public class PolicyActivity extends AppCompatActivity {

    /**
     * TODO: Add a check to see if policy is in Governement, and if so, make a remove button
     */

    long mPolicyID;
    Policy mPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mPolicyID = intent.getLongExtra(Constants.INTENT_POLICY_ID, (long) 0.0);

        mPolicy = PolicyRepo.getPolicyByID(mPolicyID);

        setViews();
        setBarChart();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.policy_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 1);

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
                        break;
                    }

                    case 2: {
                        finish();
                        break;
                    }

                    case 3: {
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        startActivity(issuesIntent);
                        finish();
                        break;
                    }

                    case 4: {
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        startActivity(pollsIntent);
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

    private void setViews(){
        TextView name = (TextView) findViewById(R.id.policy_screen_name);
        TextView department = (TextView) findViewById(R.id.policy_screen_department);
        TextView description = (TextView) findViewById(R.id.policy_screen_description);
        TextView size  = (TextView) findViewById(R.id.policy_screen_size);
        TextView cost = (TextView) findViewById(R.id.policy_screen_cost);
        TextView timeToComplete = (TextView) findViewById(R.id.policy_screen_time);
        Button button = (Button) findViewById(R.id.policy_screen_adopt_button);

        name.setText(mPolicy.getName());
        department.setText(mPolicy.getMinistry());
        description.setText(mPolicy.getDescription());
        size.setText(mPolicy.getSize() + "");
        cost.setText(NumberHelper.getWordedVersion(mPolicy.getCost()));
        timeToComplete.setText(mPolicy.getTimeToComplete() + " weeks");

        final Context mContext = this;

        Player player = PlayerRepo.getCurrentPlayer();
        if(player.getCountry().getGovernment().checkIfMinister(mPolicy.getMinistry())){
            button.setEnabled(false);
            button.setText("Minister Not Selected");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.beginTransaction();
                PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addPolicy(mPolicy);
                RealmHelper.endTransaction();

                Intent intent = new Intent(mContext, PoliciesScreen.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void setBarChart(){
        HorizontalBarChart barChart = (HorizontalBarChart)findViewById(R.id.policy_screen_bar_chart);

        ArrayList<String> XValues = new ArrayList<String>();

        for(int i = 0; i < mPolicy.getEffects().size(); i++){
            XValues.add(mPolicy.getEffects().get(i).getProperty());
        }

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        //ArrayList<IBarDataSet> datasets = new ArrayList<IBarDataSet>();

        for(int i = 0; i < mPolicy.getEffects().size(); i++){
            float effect = 0;
            String property = mPolicy.getEffects().get(i).getProperty();
            if( property.equals(Constants.AVERAGE_INCOME)){
                double avgIncome = (PlayerRepo.getCurrentPlayer().getCountry().getEconomy().getAverageIncome());
                int effectNumber = mPolicy.getEffects().get(i).getEffect();
                effect = (float)(((avgIncome - (effectNumber + avgIncome)) / avgIncome) * 100);
            }
            else
            {
                effect = (float)(mPolicy.getEffects().get(i).getEffect());
            }
            BarEntry entry = new BarEntry((float)effect, i);
            entries.add(entry);
        }

        for(int i = 0; i < entries.size(); i++) {
            LinkedList<BarEntry> entry = new LinkedList<BarEntry>();
            entry.add(entries.get(i));
            BarDataSet set = new BarDataSet(entry, (mPolicy.getEffects().get(i).getProperty()));
            sets.add(set);
        }

        //datasets.add(set);

        BarData data = new BarData(XValues, sets);

        YAxis axis = barChart.getAxisLeft();
        axis.setDrawLabels(false); // no axis labels
        axis.setDrawAxisLine(false); // no axis line
        axis.setDrawGridLines(false); // no grid lines
        axis.setDrawZeroLine(true); // draw a zero line


        axis = barChart.getAxisRight();
        axis.setDrawLabels(false); // no axis labels
        axis.setDrawAxisLine(false); // no axis line
        axis.setDrawGridLines(false); // no grid lines
        axis.setDrawZeroLine(true); // draw a zero line

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.setDescription("");              // Hide the description
        barChart.setDrawGridBackground(false);

        barChart.getLegend().setEnabled(false);   // Hide the legend

        barChart.setData(data);
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }

}
