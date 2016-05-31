package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Model;
import model.Policy;

public class PolicyActivity extends AppCompatActivity {

    int mPolicyPosition;
    Policy mPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mPolicyPosition = intent.getIntExtra(Constants.INTENT_POLICY_NUMBER, 0);

        mPolicy = Model.getPolicies().get(mPolicyPosition);

        setViews();
        setBarChart();
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

        if(Model.getCountry().getGovernment().checkIfMinister(mPolicy.getMinistry())){
            button.setEnabled(false);
            button.setText("Minister Not Selected");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getCountry().getGovernment().addPolicy(mPolicy);

                Intent intent = new Intent(mContext, PoliciesScreen.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void setBarChart(){
        HorizontalBarChart barChart = (HorizontalBarChart)findViewById(R.id.policy_screen_bar_chart);

        ArrayList<String> XValues = new ArrayList<String>();

        for(int i = 0; i < mPolicy.getEffect().size(); i++){
            XValues.add(mPolicy.getEffect().get(i).getProperty());
        }

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        //ArrayList<IBarDataSet> datasets = new ArrayList<IBarDataSet>();

        for(int i = 0; i < mPolicy.getEffect().size(); i++){
            float effect = 0;
            if(mPolicy.getEffect().get(i).getProperty() == Constants.AVERAGE_INCOME){
                double avgIncome = (Model.getCountry().getEconomy().getAverageIncome());
                int effectNumber = mPolicy.getEffect().get(i).getEffect();
                effect = (float)(((avgIncome - (effectNumber + avgIncome)) / avgIncome) * 100);
            }
            else
            {
                effect = (float)(mPolicy.getEffect().get(i).getEffect());
            }
            BarEntry entry = new BarEntry((float)effect, i);
            entries.add(entry);
        }

        for(int i = 0; i < entries.size(); i++) {
            LinkedList<BarEntry> entry = new LinkedList<BarEntry>();
            entry.add(entries.get(i));
            BarDataSet set = new BarDataSet(entry, (mPolicy.getEffect().get(i).getProperty()));
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
