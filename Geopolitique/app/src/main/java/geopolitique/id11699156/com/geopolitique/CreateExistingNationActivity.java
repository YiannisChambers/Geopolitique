package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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
        Intent startIntent = new Intent(this, HomeScreenActivity.class);
        startActivity(startIntent);
    }
}
