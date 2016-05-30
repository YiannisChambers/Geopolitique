package geopolitique.id11699156.com.geopolitique;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PoliciesScreen extends AppCompatActivity {

    private PoliciesAdapter mAdapter;

    private PoliciesAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpList();

    }

    private void setUpList(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.policies_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new PoliciesAdapter(this, Model.getPolicies());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        //Current policies list
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.current_policies_screen_recycler_view);
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);

        //Create and populate adapter
        mAdapter2 = new PoliciesAdapter(this, Model.getCountry().getGovernment().getCabinet().getTotalPolicies());
        recyclerView2.setAdapter(mAdapter2);
        mAdapter2.notifyDataSetChanged();
    }

}
