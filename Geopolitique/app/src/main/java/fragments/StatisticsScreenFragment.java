package fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.LinkedList;

import data.PlayerRepo;
import geopolitique.id11699156.com.geopolitique.R;
import model.Economy;
import util.NumberHelper;

public class StatisticsScreenFragment extends Fragment {

    public StatisticsScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setUpPieChart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpPieChart();
    }



    void setUpPieChart(){
        Economy economy = PlayerRepo.getCurrentPlayer().getCountry().getEconomy();
        PieChart chart = (PieChart)getActivity().findViewById(R.id.statistics_screen_piechart);

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