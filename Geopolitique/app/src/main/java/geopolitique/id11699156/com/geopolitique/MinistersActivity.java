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

public class MinistersActivity extends AppCompatActivity {

    private static Context mContext;
    private static int ministerPosition;
    private MinistersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ministerPosition = intent.getIntExtra(Constants.INTENT_MINISTER_NUMBER, 0);

        mContext = this;

        setUpList();
    }

    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ministers_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new MinistersAdapter(this, Model.getMinisters(), true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static void OnSetClick(View v, int position){
        Minister minister = Model.getMinisters().get(position);
        Model.getCountry().getGovernment().getCabinet().setMinister(ministerPosition, minister);
        Intent intent = new Intent(mContext, CabinetActivity.class);
        mContext.startActivity(intent);
    }

}
