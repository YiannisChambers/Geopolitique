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
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.text.DateFormat;
import java.util.Calendar;

import data.PlayerRepo;
import data.RealmHelper;
import io.realm.Realm;
import model.TestData;
import model.Player;
import util.NotificationsHelper;
import util.ToolbarHelper;

/**
 * The Home Activity for the application
 */
public class HomeScreenActivity extends AppCompatActivity {

    //WARNING: HORRIBLE CODE BELOW
    private static TextView mTimerText;
    private static TextView mDateText;
    private static UpdateTimerAsyncTask mUpdateTask;

    private AHBottomNavigation bottomNavigation;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //Initialise the views on the screen.
        setUpViews();

        //Set up the bottom toolbar at the bottom of the Screen
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.home_screen_bottom_navigation);
        ToolbarHelper.setUpToolbar(bottomNavigation, this, 2);
        bottomNavigation = ToolbarHelper.getToolbar();
        //setUpToolBar();

        //If an AsyncTask is not already running, run it!
        if (mUpdateTask == null) {
            mUpdateTask = new UpdateTimerAsyncTask();
            mUpdateTask.execute();
        }

        mContext = this;
    }

    /**
     * Initialise all the views on the Home Screen
     */
    private void setUpViews(){
        mTimerText = (TextView) findViewById(R.id.home_screen_time_text);
        mDateText = (TextView) findViewById(R.id.home_screen_date_text);

        //Set welcome text.
        TextView welcomeText = (TextView) findViewById(R.id.home_screen_leader_text);
        welcomeText.setText(PlayerRepo.getCurrentPlayer().getCountry().getLeader().getShortNameWithTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * AsyncTask to run the game - incrementing time and creating issues as you go
     */
    private class UpdateTimerAsyncTask extends AsyncTask<Void, Void, Void> {

        private boolean mIsRunning = true;
        private Calendar mCalendar;

        private int minutes, hours, days, weeks, months, years;

        private boolean checkedWeek = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(PlayerRepo.getCurrentPlayer().getTime());
            mCalendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(mCalendar.MONTH), mCalendar.DAY_OF_MONTH, mCalendar.HOUR_OF_DAY, 0, 0);

            hours = mCalendar.get(Calendar.HOUR);
            days = mCalendar.get(Calendar.DAY_OF_MONTH);
            weeks = mCalendar.get(Calendar.WEEK_OF_MONTH);
            months = mCalendar.get(Calendar.MONTH);
            years = mCalendar.get(Calendar.YEAR);

        }

        @Override
        protected Void doInBackground(Void... params) {
            while (mIsRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mCalendar.add(Calendar.HOUR, 1);

                hours += 1;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            if (hours >= 24) {
                days += 1;
                hours = 0;
                minutes = 0;
                updateDaily();

            }
            if (days == 7 || (days != 0 && days % 7 == 0)) {
                if (!checkedWeek) {
                    checkedWeek = true;
                    minutes = 0;
                    weeks += 1;
                    updateWeekly();
                }
            } else {
                checkedWeek = false;
            }
            if (days >= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                months += 1;
                days = 0;
                weeks = 0;
                updateMonthly();
                NotificationsHelper.sendStatisticsNotification(mContext);
            }

            DateFormat format = DateFormat.getTimeInstance();
            mTimerText.setText(format.format(mCalendar.getTime()));

            mDateText.setText(DateFormat.getDateInstance().format(mCalendar.getTime()));

            mIsRunning = true;

            RealmHelper.beginTransaction();
            PlayerRepo.getCurrentPlayer().setTime(mCalendar.getTimeInMillis());
            RealmHelper.endTransaction();

            super.onProgressUpdate(values);
        }

        /**
         * Update the country for a day
         */
        private void updateDaily() {
            //Asynchronously run the update method
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    Player player = PlayerRepo.getCurrentPlayer();
                    player.getCountry().updateDaily();
                    player.setTime(mCalendar.getTimeInMillis());
                }
            });
            //Potentially add a random issue
            addRandomIssue();


        }

        /**
         * Update the country for a week
         */
        private void updateWeekly() {
            //Asynchronously run the update method
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    Player player = PlayerRepo.getCurrentPlayer();
                    player.getCountry().updateWeekly();
                }
            });

            ToolbarHelper.incrementStatisticsNumber();
            NotificationsHelper.sendPollsNotification(mContext);
        }

        /**
         * Update the country for a month
         */
        private void updateMonthly() {
            //Asynchronously run the update method
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    Player player = PlayerRepo.getCurrentPlayer();
                    player.getCountry().updateMonthly();
                }
            });
        }

        /**
         * Add a random issue to the government
         */
        private void addRandomIssue() {
            //If a random issue is generated...
            if(TestData.addRandomIssue()) {
                //...create an Issues notification
                ToolbarHelper.incrementIssueNumber();
                NotificationsHelper.sendIssueNotification(mContext);
            }
        }

        /**
         * Set the AsynTask to running or not
         * @param value
         */
        public void setIsRunning(boolean value){
            mIsRunning = value;
        }

    }
}