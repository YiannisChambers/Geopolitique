package geopolitique.id11699156.com.geopolitique;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import data.IssueRepo;
import data.PlayerRepo;
import data.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import model.Country;
import model.Economy;
import model.Issue;
import model.Leader;
import model.Minister;
import model.Model;
import model.Player;
import model.Policy;

import services.TimerIntentService;
import util.Constants;
import util.NumberHelper;
import util.SetupHelper;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView mTimerText;
    private TextView mDateText;
    // private boolean mScreenIsRunning;
    public int mDays, mWeeks, mMonths;

    Model model;


    AHBottomNavigation bottomNavigation;
    private int issueNotes, statisticNotes;
    private static boolean isRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTimerText = (TextView) findViewById(R.id.home_screen_time_text);
        mDateText = (TextView) findViewById(R.id.home_screen_date_text);

        if (!isRunning) {
            new UpdateTimerAsyncTask().execute();
            isRunning = true;
        }

        TextView welcomeText = (TextView) findViewById(R.id.home_screen_leader_text);
        welcomeText.setText(PlayerRepo.getCurrentPlayer().getCountry().getLeader().getShortNameWithTitle());


        setUpToolBar();


        updateStats();

    }


    private void setUpToolBar() {
        /*
        TOOL BAR
         */
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.home_screen_bottom_navigation);

        // Create items
        SetupHelper.setUpToolBar(bottomNavigation, 2);

        final Context context = this;
        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0: {
                        final Intent cabinetIntent = new Intent(context, CabinetActivity.class);
                        cabinetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(cabinetIntent, Constants.NEW_SCREEN);
                        onPause();
                        //bottomNavigation.setCurrentItem(2);
                        break;
                    }
                    case 1: {
                        final Intent policiesIntent = new Intent(context, PoliciesScreen.class);
                        policiesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(policiesIntent, Constants.NEW_SCREEN);
                        onPause();
                        //bottomNavigation.setCurrentItem(2);
                        break;
                    }

                    case 2: {
                        break;
                    }

                    case 3: {
                        issueNotes = 0;
                        bottomNavigation.setNotification("", 3);
                        final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                        issuesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(issuesIntent, Constants.NEW_SCREEN);
                        onPause();
                        //bottomNavigation.setCurrentItem(2);
                        break;
                    }

                    case 4: {
                        statisticNotes = 0;
                        bottomNavigation.setNotification("", 4);
                        final Intent pollsIntent = new Intent(context, PollsScreen.class);
                        pollsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(pollsIntent, Constants.NEW_SCREEN);
                        onPause();
                        //bottomNavigation.setCurrentItem(2);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigation != null)
            bottomNavigation.setCurrentItem(2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bottomNavigation.setCurrentItem(2);

    }

    void updateStats() {
        /*
        TextView updateText = (TextView) findViewById(R.id.TEMP_OUTPUT);
        Country country = PlayerRepo.getCurrentPlayer().getCountry();   //Model.getCountry();
        Economy economy = country.getEconomy();
        String nationStats = "POPUL: " + NumberHelper.getWordedVersion(country.getPopulation()) + "\n";
        String economyStats = "GDP: " + NumberHelper.getWordedVersion(economy.getGDP()) + ", UNEMP: " + economy.getUnemploymentRate() + "%, \n AVGINC: $" + NumberHelper.getWordedVersion(economy.getAverageIncome())
                + ", GOVT.  INCOME: " + NumberHelper.getWordedVersion(economy.getTaxIncome()) + "\n DEFICIT: " + NumberHelper.getWordedVersion(economy.getDeficitSurplusFigure()) + ", DEBT: $" + NumberHelper.getWordedVersion(economy.getDebt()) + "\n";
        String popularity = "TOTAL SPENDING: " + NumberHelper.getWordedVersion(country.getGovernment().getGovernmentSpending()) + "\n POPULARITY: " + NumberHelper.getWordedVersion(country.getGovernment().getPopularity()) + "%, INT. POP: " + NumberHelper.getWordedVersion(country.getGovernment().getInternationalPopularity()) + "%" + "\n";
        String days = "DAYS: " + mDays + ", WEEKS: " + mWeeks + ", MONTHS: " + mMonths;
        updateText.setText(nationStats + economyStats + popularity + days);
        */
    }

    public void onFixMeSomeDay(View view) {
    }

    void sendPollsNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_poll_white_24dp)
                        .setContentTitle("Polls released!")
                        .setContentText("The latest opinion polls for your Government have been released.");

        Intent resultIntent = new Intent(this, PollsScreen.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(PollsScreen.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    void sendStatisticsNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_trending_up_white_24dp)
                        .setContentTitle("Statistics released!")
                        .setContentText("The latest national economic figures have been released.");
        Intent resultIntent = new Intent(this, PollsScreen.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(PollsScreen.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    void sendIssueNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_account_balance_white_24dp)
                        .setContentTitle("Issue encountered!")
                        .setContentText("An issue has developed that requires your attention.");
        Intent resultIntent = new Intent(this, IssuesActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(IssuesActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    public void addRandomIssue(){
        if (new Random().nextBoolean()) {
            LinkedList<Issue> issues = IssueRepo.getAllIssues();

            RealmHelper.beginTransaction();
            PlayerRepo.getCurrentPlayer().getCountry().getGovernment().addIssue(issues.get(new Random().nextInt(issues.size())));
            RealmHelper.endTransaction();
            issueNotes += 1;
            bottomNavigation.setNotification(issueNotes + "", 3);
            sendIssueNotification();
        }
    }
    /*
    private class ResponseReceiver extends BroadcastReceiver {
        // Prevents instantiation
        private ResponseReceiver() {
        }
        // Called when the BroadcastReceiver gets an Intent it's registered to receive

        @Override
        public void onReceive(Context context, Intent intent) {
            updateStats();
        /*
         * Handle Intents here.

        }
    }
    */


    private class UpdateTimerAsyncTask extends AsyncTask<Void, Void, Void> {

        private boolean mIsRunning = true;
        private Calendar mCalendar;

        private int seconds, minutes, hours, days, weeks, months, years;

        private boolean checkedWeek = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(PlayerRepo.getCurrentPlayer().getTime());
            mCalendar.set(mCalendar.get(Calendar.YEAR),  mCalendar.get(mCalendar.MONTH), mCalendar.DAY_OF_MONTH, mCalendar.HOUR_OF_DAY, 0, 0);

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
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //mCalendar.add(Calendar.HOUR, 1);
                //hours += 1;

                mCalendar.add(Calendar.HOUR, 1);
                hours += 1;
                publishProgress();

                //CHANGED TO MINUTE FOR TESTING PURPOSES

                //mCalendar.add(Calendar.SECOND, 1);

                //OTHER
                //mCalendar.set(mCalendar.YEAR, mCalendar.MONTH, mCalendar.DAY_OF_MONTH, mCalendar.HOUR_OF_DAY, mCalendar.MINUTE, mCalendar.SECOND + 1);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.home_screen_bottom_navigation);

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
                    bottomNavigation.setNotification(statisticNotes + "", 4);
                    sendPollsNotification();
                }
            } else {
                checkedWeek = false;
            }
            if (days >= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                months += 1;
                days = 0;
                weeks = 0;
                updateMonthly();
                sendStatisticsNotification();
            }


            mDays = days;
            mWeeks = weeks;
            mMonths = months;

            DateFormat format = DateFormat.getTimeInstance();
            mTimerText.setText(format.format(mCalendar.getTime()));//(mCalendar.HOUR_OF_DAY + ":" + mCalendar.MINUTE + ":" + mCalendar.SECOND + "\n" +

            mDateText.setText(DateFormat.getDateInstance().format(mCalendar.getTime()));

            //mCalendar.DAY_OF_MONTH + " " + mCalendar.getDisplayName(mCalendar.MONTH, Calendar.LONG, Locale.getDefault()) + ", " + mCalendar.YEAR);

            mIsRunning = true;

            updateStats();

            super.onProgressUpdate(values);

        }

        String GetMonthText(int i) {
            return getResources().getStringArray(R.array.months)[i];
        }


        private void updateDaily() {
            //RealmHelper.beginTransaction();
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    Player player = PlayerRepo.getCurrentPlayer();
                    player.getCountry().updateDaily();
                    player.setTime(mCalendar.getTimeInMillis());
                }
            });
            addRandomIssue();


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
}