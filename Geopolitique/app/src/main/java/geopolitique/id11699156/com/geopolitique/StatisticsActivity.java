package geopolitique.id11699156.com.geopolitique;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.LinkedList;

import model.Economy;
import model.Model;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpPieChart();
    }

    void setUpPieChart(){
        Economy economy = Model.getCountry().getEconomy();
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
        chart.setCenterText("$" + NumberHelper.getWordedVersion(Model.getCountry().getEconomy().getGDP()));
    }

}
