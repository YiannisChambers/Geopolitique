package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import data.PlayerRepo;
import data.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import model.Issue;
import model.Minister;
import model.Policy;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        new StartUpAsyncTask(this).execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class StartUpAsyncTask extends AsyncTask<Void, Void, Void> {
        Context mContext;

        public StartUpAsyncTask(Context context) {
            mContext = context;
        }

        protected Void doInBackground(Void... params) {
            RealmConfiguration config = new RealmConfiguration
                    .Builder(mContext)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(config);

            //Realm realm = Realm.getDefaultInstance();

            //FOR TESTING - THIS WILL STOP PERSISTENCE
            /*RealmHelper.beginTransaction();
            realm.deleteAll();
            realm.delete(Issue.class);
            realm.delete(Policy.class);
            realm.delete(Minister.class);
            RealmHelper.endTransaction();*/

            if (PlayerRepo.checkIfPlayerExists()) {
                Intent intent = new Intent(mContext, HomeScreenActivity.class);
                mContext.startActivity(intent);
            } else {
                setUpData();
                Intent intent = new Intent(mContext, CreateExistingNationActivity.class);
                mContext.startActivity(intent);
            }
            return null;
        }

        private void setUpData() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


