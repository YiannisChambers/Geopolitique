package geopolitique.id11699156.com.geopolitique;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import adapters.IssuesAdapter;
import adapters.PoliciesAdapter;
import data.IssueRepo;
import model.Model;

public class IssuesActivity extends AppCompatActivity {

    IssuesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpList();

    }

    private void setUpList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.issues_screen_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create and populate adapter
        mAdapter = new IssuesAdapter(this, IssueRepo.getAllUnresolvedIssues());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
