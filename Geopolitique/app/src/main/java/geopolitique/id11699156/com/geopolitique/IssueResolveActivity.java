package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import data.IssueRepo;
import data.PlayerRepo;
import data.RealmHelper;
import model.Issue;
import model.Model;
import util.Constants;
import util.SetupHelper;

public class IssueResolveActivity extends AppCompatActivity {

    Issue mIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_resolve);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        long issueID = intent.getLongExtra(Constants.INTENT_ISSUE_ID, (long)0.0f);

        mIssue = IssueRepo.getIssueByID(issueID);

        setViews();

        setUpToolBar();
    }

    private void setUpToolBar(){
        /*
        TOOL BAR
         */
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.issue_resolve_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 3);

        final Context context = this;
        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0: {
                        final Intent cabinetIntent = new Intent(context, CabinetActivity.class);
                        startActivity(cabinetIntent);
                        finish();
                        break;
                    }
                    case 1: {
                        final Intent policiesIntent = new Intent(context, PoliciesScreen.class);
                        startActivity(policiesIntent);
                        finish();
                        break;
                    }

                    case 2: {
                        finish();
                        break;
                    }

                    case 3: {

                        break;
                    }

                    case 4: {
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        startActivity(pollsIntent);
                        finish();
                        break;
                    }

                    case 5: {
                        final Intent statisticsIntent = new Intent(context, StatisticsActivity.class);
                        startActivity(statisticsIntent);
                        finish();
                        break;
                    }

                    default: {
                        ;
                        break;
                    }
                }
            }
        });
    }

    private void setViews(){
        TextView name = (TextView) findViewById(R.id.issue_resolve_screen_name);
        TextView description = (TextView) findViewById(R.id.issue_resolve_screen_description);
        Button button1 = (Button) findViewById(R.id.issue_resolve_screen_option_1);
        Button button2 = (Button) findViewById(R.id.issue_resolve_screen_option_2);
        Button button3 = (Button) findViewById(R.id.issue_resolve_screen_option_3);


        name.setText(mIssue.getName());

        description.setText(mIssue.getDescription());


        final Context mContext = this;

        button1.setText(mIssue.getOptions().get(0).getDescription());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.beginTransaction();
                mIssue.selectOption(0);
                PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addIssue(mIssue);
                RealmHelper.endTransaction();
                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });

        button2.setText(mIssue.getOptions().get(1).getDescription());
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.beginTransaction();
                mIssue.selectOption(1);
                PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addIssue(mIssue);
                RealmHelper.endTransaction();
                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });

        button3.setText(mIssue.getOptions().get(2).getDescription());
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.beginTransaction();
                mIssue.selectOption(2);
                PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addIssue(mIssue);
                RealmHelper.endTransaction();
                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

}
