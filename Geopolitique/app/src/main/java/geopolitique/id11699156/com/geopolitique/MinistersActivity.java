package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import adapters.MinistersAdapter;
import data.MinisterRepo;
import data.PlayerRepo;
import data.RealmHelper;
import model.Minister;
import model.Model;
import util.Constants;

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
        mAdapter = new MinistersAdapter(this, MinisterRepo.getAllMinistersNotInCabinet(), true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static void OnSetClick(View v, long ID){
        Minister minister = MinisterRepo.getMinisterByID(ID);

        RealmHelper.beginTransaction();
        PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().setMinister(ministerPosition, minister);
        RealmHelper.endTransaction();

        Minister treasurer = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getTreasurer();

        Intent intent = new Intent(mContext, CabinetActivity.class);
        mContext.startActivity(intent);
    }

}
