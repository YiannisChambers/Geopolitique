/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import geopolitique.id11699156.com.geopolitique.Backups;
import geopolitique.id11699156.com.geopolitique.R;

/**
 * Fragment to display the country's polling data
 * in the Polls Tab on the PollsScreen
 */
public class PollsScreenFragment extends Fragment {

    /**
     * Required empty public constructor
     */
    public PollsScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_polls_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialise the charts on the fragment using user data.
        setPollLineChart();
        setPollBarChart();
    }

    /**
     * Create the Poll Bar Chart
     */
    private void setPollBarChart() {

        //Get bar chart
        BarChart barChart = (BarChart) getActivity().findViewById(R.id.polls_screen_bar_chart);

        //Create the XValues for the Bar Chart
        ArrayList<String> XValues = new ArrayList<String>();
        XValues.add(getActivity().getString(R.string.polls_fragment_us_text));
        XValues.add(getActivity().getString(R.string.polls_fragment_them_text));

        //Make the first entry with the Government popularity
        ArrayList<BarEntry> ourEntries = new ArrayList<BarEntry>();
        BarEntry ourEntry = new BarEntry((int) PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getPopularity(), 0);
        ourEntries.add(ourEntry);

        //Make the second entry with the Opposition popularity (which is 100 minus the Government's popularity)
        ArrayList<BarEntry> theirEntries = new ArrayList<BarEntry>();
        BarEntry theirEntry = new BarEntry((int) (100 - PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getPopularity()), 1);
        theirEntries.add(theirEntry);

        //Create datasets for both those entries, and set them with a respective color
        BarDataSet ourSet = new BarDataSet(ourEntries, getActivity().getString(R.string.polls_fragment_us_text));
        ourSet.setColor(getActivity().getColor(R.color.colorTextColor));

        BarDataSet theirSet = new BarDataSet(theirEntries, getActivity().getString(R.string.polls_fragment_them_text));
        theirSet.setColor(getActivity().getColor(R.color.colorAccent));

        //Set Axis Dependencies for datasets
        ourSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        theirSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ourSet.setValueTextColor(getActivity().getColor(R.color.colorTextColor));
        ourSet.setValueTextSize(10);
        theirSet.setValueTextColor(getActivity().getColor(R.color.colorTextColor));
        theirSet.setValueTextSize(10);

        //Add datasets to list
        ArrayList<IBarDataSet> datasets = new ArrayList<IBarDataSet>();
        datasets.add(ourSet);
        datasets.add(theirSet);

        //Create data
        BarData data = new BarData(XValues, datasets);

        //Format the axes of the chart
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

        //Add data to bar chart - and refresh for viewing
        barChart.setData(data);
        barChart.notifyDataSetChanged();
        barChart.invalidate();

        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);


    }

    void setPollLineChart() {
        LineChart lineChart = (LineChart) getActivity().findViewById(R.id.polls_screen_line_chart);

        ArrayList<String> XValues = new ArrayList<String>();

        ArrayList polls = Backups.getPollBackups();

        ArrayList<Entry> ourEntries = new ArrayList<Entry>();
        ArrayList<Entry> theirEntries = new ArrayList<Entry>();

        for (int i = polls.size() - 10 > 0 ? polls.size() - 10 : 0; i < polls.size(); i++) {
            Entry ourEntry = new Entry((float) ((double) polls.get(i)), i);
            ourEntries.add(ourEntry);

            Entry theirEntry = new Entry(100 - (float) ((double) polls.get(i)), i);
            theirEntries.add(theirEntry);

            XValues.add("Week " + i + ".");
        }

        LineDataSet ourSet = new LineDataSet(ourEntries, getActivity().getString(R.string.polls_fragment_us_text));
        ourSet.setColor(getActivity().getColor(R.color.colorTextColor));

        LineDataSet theirSet = new LineDataSet(theirEntries, getActivity().getString(R.string.polls_fragment_them_text));
        theirSet.setColor(getActivity().getColor(R.color.colorAccent));

        ourSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        theirSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> datasets = new ArrayList<ILineDataSet>();
        datasets.add(ourSet);
        datasets.add(theirSet);

        LineData data = new LineData(XValues, datasets);

        lineChart.setData(data);
        lineChart.invalidate();

        YAxis axis = lineChart.getAxisLeft();
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);

        axis = lineChart.getAxisRight();
        axis.setEnabled(false);
        axis.setAxisMaxValue(100);
        axis.setAxisMinValue(0);

        lineChart.getLegend().setEnabled(false);
        lineChart.setDrawGridBackground(false);

        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getAxisRight().setTextColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getLineData().setValueTextColor(Color.WHITE);

    }
}
