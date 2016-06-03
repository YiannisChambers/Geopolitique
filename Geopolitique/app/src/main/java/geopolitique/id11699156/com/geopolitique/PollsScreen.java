package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import data.PlayerRepo;
import model.Model;
import util.SetupHelper;

public class PollsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setPollBarChart();

        setPollLineChart();
        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.polls_screen_bottom_navigation);

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

    private void setPollBarChart(){

        BarChart barChart = (BarChart)findViewById(R.id.polls_screen_bar_chart);

        ArrayList<String> XValues = new ArrayList<String>();
        XValues.add("US");  XValues.add("THEM");

        ArrayList<BarEntry> ourEntries = new ArrayList<BarEntry>();
        BarEntry ourEntry = new BarEntry((int) PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getPopularity(), 0);
        ourEntries.add(ourEntry);

        ArrayList<BarEntry> theirEntries = new ArrayList<BarEntry>();
        BarEntry theirEntry = new BarEntry((int) (100 - PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getPopularity()), 1);
        theirEntries.add(theirEntry);

        BarDataSet ourSet = new BarDataSet(ourEntries, "US");
        ourSet.setColor(Color.BLUE);

        BarDataSet theirSet = new BarDataSet(theirEntries, "THEM");
        theirSet.setColor(Color.RED);

        ourSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        theirSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<IBarDataSet> datasets = new ArrayList<IBarDataSet>();
        datasets.add(ourSet); datasets.add(theirSet);

        BarData data = new BarData(XValues, datasets);

        YAxis axis = barChart.getAxisLeft();
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);
        axis.setDrawLabels(false); // no axis labels
        axis.setDrawAxisLine(false); // no axis line
        axis.setDrawGridLines(false); // no grid lines
        axis.setDrawZeroLine(true); // draw a zero line


        axis = barChart.getAxisRight();
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);
        axis.setDrawLabels(false); // no axis labels
        axis.setDrawAxisLine(false); // no axis line
        axis.setDrawGridLines(false); // no grid lines
        axis.setDrawZeroLine(true); // draw a zero line

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.setDescription("");              // Hide the description
        barChart.setDrawGridBackground(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getLegend().setEnabled(false);   // Hide the legend

        barChart.setData(data);
        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }

    void setPollLineChart(){
        LineChart lineChart = (LineChart)findViewById(R.id.polls_screen_line_chart);

        ArrayList<String> XValues = new ArrayList<String>();

        ArrayList polls = Backups.getPollBackups();

        ArrayList<Entry> ourEntries = new ArrayList<Entry>();
        ArrayList<Entry> theirEntries = new ArrayList<Entry>();

        for(int i =0; i < polls.size(); i++){
            Entry ourEntry = new Entry((float)((double)polls.get(i)), i);
            ourEntries.add(ourEntry);

            Entry theirEntry = new Entry(100 - (float)((double)polls.get(i)), i);
            theirEntries.add(theirEntry);

            XValues.add(i + ".");
        }

        LineDataSet ourSet = new LineDataSet(ourEntries, "US");
        ourSet.setColor(Color.BLUE);

        LineDataSet theirSet = new LineDataSet(theirEntries, "THEM");
        theirSet.setColor(Color.RED);

        ourSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        theirSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> datasets = new ArrayList<ILineDataSet>();
        datasets.add(ourSet); datasets.add(theirSet);

        LineData data = new LineData(XValues, datasets);

        lineChart.setData(data);
        lineChart.invalidate();

        YAxis axis = lineChart.getAxisLeft();
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);

        axis = lineChart.getAxisRight();
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);

        lineChart.getLegend().setEnabled(false);
    }

}
