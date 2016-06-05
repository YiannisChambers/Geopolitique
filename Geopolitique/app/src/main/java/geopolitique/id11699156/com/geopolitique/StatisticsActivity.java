package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.LinkedList;

import data.PlayerRepo;
import model.Economy;
import util.NumberHelper;
import util.SetupHelper;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpPieChart();
        setUpToolBar();
    }

    private void setUpToolBar(){
        // Get the toolbar
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.statistics_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 4);

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
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        startActivity(issuesIntent);
                        finish();
                        break;
                    }

                    case 4: {
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

    void setUpPieChart(){
        Economy economy = PlayerRepo.getCurrentPlayer().getCountry().getEconomy();
        PieChart chart = (PieChart)findViewById(R.id.statistics_screen_piechart);

        Entry incomeEntry = new Entry((float)economy.getIncome(), 0);
        Entry consumptionEntry = new Entry((float)economy.getConsumption(), 1);
        Entry taxEntry = new Entry((float)economy.getTaxIncome(), 1);

        LinkedList<Entry> entries = new LinkedList<>();
        entries.add(incomeEntry);
        entries.add(consumptionEntry);
        entries.add(taxEntry);
        PieDataSet set = new PieDataSet(entries, "GDP");
        set.setColors(new int[] {Color.rgb(128, 255, 0), Color.rgb(0, 255, 0), Color.rgb(0, 255, 128)});

        LinkedList<String> xValues = new LinkedList<>();
        xValues.add("Total Income"); xValues.add("Consumption"); xValues.add("Tax Income");

        PieData data = new PieData(xValues, set);

        chart.setData(data);
        chart.setCenterText("$" + NumberHelper.getWordedVersion(economy.getGDP()));
    }

}
