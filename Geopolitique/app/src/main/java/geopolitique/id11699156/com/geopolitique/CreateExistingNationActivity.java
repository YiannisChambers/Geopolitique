package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import data.IssueRepo;
import data.MinisterRepo;
import data.PlayerRepo;
import data.PolicyRepo;
import model.Country;
import model.Leader;
import model.Model;
import model.Player;

public class CreateExistingNationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_existing_nation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Comment me what i am doing here!!!! verdammnochmal
     *
     * @param view
     */
    public void onCreateNationClick(View view) {
        Leader leader = new Leader();
        Country country = new Country(leader, "Australia", "Monarchy", 23000000, 1.4f);
        Player player = new Player(country);
        PlayerRepo.createNewPlayer(player);
        Country c = PlayerRepo.getCurrentPlayer().getCountry();
        Model.setUpIssues();
        Model.setUpMinisters();
        Model.setUpPolicy();

        IssueRepo.createNewIssues(Model.getIssues());
        MinisterRepo.createNewMinisters(Model.getMinisters());
        PolicyRepo.createNewPolicies(Model.getPolicies());

        Intent startIntent = new Intent(this, HomeScreenActivity.class);
        startActivity(startIntent);
    }
}
