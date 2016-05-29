package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CabinetActivity extends AppCompatActivity{

    static Context mContext;
    private MinistersAdapter mAdapter;
    private Cabinet mCabinet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;

        mCabinet = Model.getCountry().getGovernment().getCabinet();
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
    }

    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cabinet_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, mCabinet, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static void OnChangeClick(View v, int pos) {
        Intent intent = new Intent(mContext, MinistersActivity.class);
        intent.putExtra(Constants.INTENT_MINISTER_NUMBER, pos);
        mContext.startActivity(intent);
    }


}
