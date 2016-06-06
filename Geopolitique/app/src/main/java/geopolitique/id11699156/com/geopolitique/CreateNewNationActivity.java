/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import util.SetupHelper;

public class CreateNewNationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_nation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpSpinners();

        Button startButton = (Button)findViewById(R.id.create_new_nation_activity_create_nation_button);
        final Intent startIntent = new Intent(this, HomeScreenActivity.class);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(startIntent);

            }
        });

    }

    private void setUpSpinners()
    {
        Spinner countryTypeSpinner = (Spinner) findViewById(R.id.create_new_nation_activity_country_type_spinner);
        countryTypeSpinner.setAdapter(SetupHelper.setUpSpinnerAdapter(this, R.array.nation_types));

        Spinner leaderTypeSpinner = (Spinner) findViewById(R.id.create_new_nation_activity_leader_type_spinner);
        leaderTypeSpinner.setAdapter(SetupHelper.setUpSpinnerAdapter(this, R.array.leader_types));
    }

}
