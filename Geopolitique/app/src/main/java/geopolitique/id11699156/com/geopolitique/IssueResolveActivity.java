/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import data.IssueRepo;
import data.PlayerRepo;
import data.RealmHelper;
import model.Issue;
import util.Constants;

/**
 * Issue Resolve Activity to allow user to select a response to a particular issue
 */
public class IssueResolveActivity extends AppCompatActivity {

    private Issue mIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_resolve);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the issue ID that the user is responding to.
        Intent intent = getIntent();
        long issueID = intent.getLongExtra(Constants.INTENT_ISSUE_ID, (long)0.0f);

        //Get the issue from the database
        mIssue = IssueRepo.getIssueByID(issueID);

        //Initialise the views on the screen
        setViews();
    }

    private void setViews(){
        //Initialise the views
        TextView name = (TextView) findViewById(R.id.issue_resolve_screen_name);
        TextView description = (TextView) findViewById(R.id.issue_resolve_screen_description);
        Button button1 = (Button) findViewById(R.id.issue_resolve_screen_option_1);
        Button button2 = (Button) findViewById(R.id.issue_resolve_screen_option_2);
        Button button3 = (Button) findViewById(R.id.issue_resolve_screen_option_3);

        name.setText(mIssue.getName());

        description.setText(mIssue.getDescription());

        //Set up option buttons with option
        setOptionButton(button1, 0);
        setOptionButton(button2, 1);
        setOptionButton(button3, 2);
    }

    /**
     * Initialise an option button with the correct option text and action
     * @param button
     * @param o
     */
    private void setOptionButton(Button button, int o){
        final Context mContext = this;
        final int option = o;

        //Set text to the Option description
        button.setText(mIssue.getOptions().get(option).getDescription());

        //Set listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.beginTransaction();
                //Select the user option
                mIssue.selectOption(option);
                //Add the issue for calculation
                PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addIssue(mIssue);
                RealmHelper.endTransaction();

                //Return to the IssuesActivity
                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
                finish();
            }
        });
    }

}
