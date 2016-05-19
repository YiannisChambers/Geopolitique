package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CreateNationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_nation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button chooseExistingButton = (Button)findViewById(R.id.create_nation_activity_choose_existing_button);
        final Intent chooseExistingIntent = new Intent(this, CreateExistingNationActivity.class);
        chooseExistingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(chooseExistingIntent);

            }
        });

        Button createNewButton = (Button)findViewById(R.id.create_nation_activity_create_new_button);
        final Intent createNewIntent = new Intent(this, CreateNewNationActivity.class);
        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(createNewIntent);

            }
        });
    }

}
