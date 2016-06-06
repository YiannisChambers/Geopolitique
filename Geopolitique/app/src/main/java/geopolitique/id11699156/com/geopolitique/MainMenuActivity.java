/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package geopolitique.id11699156.com.geopolitique;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import data.PlayerRepo;
import data.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import model.Economy;
import model.ExistingCountry;
import model.Government;
import model.Issue;
import model.Leader;
import model.Minister;
import model.Policy;
import model.TestData;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RealmConfiguration config = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        //THIS IS FOR TESTING
        //deleteAll();


        new StartUpAsyncTask(this).execute();
    }

    private void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmHelper.beginTransaction();
        realm.deleteAll();
        realm.delete(Issue.class);
        realm.delete(Policy.class);
        realm.delete(Minister.class);
        realm.delete(ExistingCountry.class);
        realm.delete(Government.class);
        realm.delete(Economy.class);
        realm.delete(Leader.class);
        RealmHelper.endTransaction();
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

            if (PlayerRepo.checkIfPlayerExists()) {
                TestData.setUpTestDataWithExistingDatabase();
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

            TestData.setUpTestData();

        }
    }
}


