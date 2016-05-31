package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.Issue;
import model.Model;

public class IssueResolveActivity extends AppCompatActivity {

    Issue mIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_resolve);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int issuePosition = intent.getIntExtra(Constants.INTENT_ISSUE_NUMBER, 0);

        mIssue = Model.getIssues().get(issuePosition);

        setViews();
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
                mIssue.selectOption(0);
                Model.getCountry().getGovernment().addIssue(mIssue);


                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });

        button2.setText(mIssue.getOptions().get(1).getDescription());
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIssue.selectOption(1);
                Model.getCountry().getGovernment().addIssue(mIssue);

                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });

        button3.setText(mIssue.getOptions().get(2).getDescription());
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIssue.selectOption(2);
                Model.getCountry().getGovernment().addIssue(mIssue);

                Intent intent = new Intent(mContext, IssuesActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

}
