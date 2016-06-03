package services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Calendar;

import data.PlayerRepo;
import io.realm.Realm;
import model.Player;
import util.Constants;

/**
 * Created by yiannischambers on 4/06/2016.
 */
public class TimerIntentService extends IntentService {

    private boolean mIsRunning = true;
    private Calendar mCalendar;

    private int seconds, minutes, hours, days, weeks, months, years;
    private int issueNotes, statisticNotes;
    private boolean checkedWeek = false;


    public TimerIntentService(){
        super(Constants.UPDATE_STATS);
    }

    public TimerIntentService(String name) {
        super(name);
        mCalendar = Calendar.getInstance();
        seconds = mCalendar.get(Calendar.SECOND);
        minutes = mCalendar.get(Calendar.MINUTE);
        hours = mCalendar.get(Calendar.HOUR);
        days = mCalendar.get(Calendar.DAY_OF_MONTH);
        weeks = mCalendar.get(Calendar.WEEK_OF_MONTH);
        months = mCalendar.get(Calendar.MONTH);
        years = mCalendar.get(Calendar.YEAR);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {


            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //mCalendar.add(Calendar.HOUR, 1);
            hours += 1;

            if (minutes >= 60) {
                hours += 1;
                minutes = 0;
            }
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
                    statisticNotes += 1;
                    //bottomNavigation.setNotification(statisticNotes + "", 4);
                }
            } else {
                checkedWeek = false;
            }
            /*if (days >= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                months += 1;
                days = 0;
                weeks = 0;
                updateMonthly();
            }*/


            //mDays = days;
            //mWeeks = weeks;
            //mMonths = months;

            //mTimerText.setText(mCalendar.getTime().toString());//(mCalendar.HOUR_OF_DAY + ":" + mCalendar.MINUTE + ":" + mCalendar.SECOND + "\n" +

            mIsRunning = true;


            Intent localIntent = new Intent();
            localIntent.setAction(Constants.UPDATE_STATS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);//Constants.UPDATE_STATS);
                            // Puts the status into the Intent
            // Broadcasts the Intent to receivers in this app.
            sendBroadcast(localIntent);

            //mCalendar.add(Calendar.MINUTE, 1);
            //minutes += 1;


            //publishProgress();

            //CHANGED TO MINUTE FOR TESTING PURPOSES

            //mCalendar.add(Calendar.SECOND, 1);

            //OTHER
            //mCalendar.set(mCalendar.YEAR, mCalendar.MONTH, mCalendar.DAY_OF_MONTH, mCalendar.HOUR_OF_DAY, mCalendar.MINUTE, mCalendar.SECOND + 1);

    }

    /*
    @Override
    protected void onProgressUpdate(Void... values) {

        //AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.home_screen_bottom_navigation);

        if (minutes >= 60) {
            hours += 1;
            minutes = 0;
        }
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
                statisticNotes += 1;
                //bottomNavigation.setNotification(statisticNotes + "", 4);
            }
        } else {
            checkedWeek = false;
        }
        if (days >= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            months += 1;
            days = 0;
            weeks = 0;
            updateMonthly();
        }


        //mDays = days;
        //mWeeks = weeks;
        //mMonths = months;

        //mTimerText.setText(mCalendar.getTime().toString());//(mCalendar.HOUR_OF_DAY + ":" + mCalendar.MINUTE + ":" + mCalendar.SECOND + "\n" +

        mIsRunning = true;

        //updateStats();

    }

*/


    private void updateDaily() {
        //RealmHelper.beginTransaction();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Player player = PlayerRepo.getCurrentPlayer();
                player.getCountry().updateDaily();
            }
        });
    }

    private void updateWeekly() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Player player = PlayerRepo.getCurrentPlayer();
                player.getCountry().updateWeekly();
            }
        });
    }



    private void updateMonthly() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Player player = PlayerRepo.getCurrentPlayer();
                player.getCountry().updateMonthly();
            }
        });
    }


}