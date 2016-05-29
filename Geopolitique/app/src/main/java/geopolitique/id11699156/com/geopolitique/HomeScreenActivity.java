package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView mTimerText;
    private boolean mScreenIsRunning = true;
    public int mDays, mWeeks, mMonths;

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTimerText = (TextView)findViewById(R.id.home_screen_time_text);
        new UpdateTimerAsyncTask().execute();


        //THIS IS TEST DATA
        Leader leader = new Leader("Francis", "Urquhart", "Prime Minister");
        Model.setUp(leader);

        TextView welcomeText = (TextView)findViewById(R.id.home_screen_leader_text);
        welcomeText.setText(leader.getShortNameWithTitle());


        Button cabinetButton = (Button)findViewById(R.id.home_screen_cabinet_button);
        final Intent cabinetIntent = new Intent(this, CabinetActivity.class);
        cabinetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(cabinetIntent);
            }
        });


        Button pollsButton = (Button)findViewById(R.id.home_screen_polls_button);
        final Intent pollsIntent = new Intent(this, PollsScreen.class);
        pollsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pollsIntent);
            }
        });

        updateStats();

    }

    void updateStats(){

        TextView updateText = (TextView)findViewById(R.id.TEMP_OUTPUT);
        Country country = Model.getCountry();
        Economy economy = country.getEconomy();
        String nationStats = "POPUL: " + NumberHelper.getWordedVersion(country.getPopulation())+ "\n";
        String economyStats = "GDP: " + NumberHelper.getWordedVersion(economy.getGDP()) + ", UNEMP: " + economy.getUnemploymentRate() + "%, \n AVGINC: $" + NumberHelper.getWordedVersion(economy.getAverageIncome())
                + ", GOVT.  INCOME: " + NumberHelper.getWordedVersion(economy.getTaxIncome()) + "\n DEFICIT: " + NumberHelper.getWordedVersion(economy.getDeficitSurplusFigure()) + ", DEBT: $" + NumberHelper.getWordedVersion(economy.getDebt()) + "\n";
        String popularity = "TOTAL SPENDING: " +  NumberHelper.getWordedVersion(country.getGovernment().getGovernmentSpending()) + "\n POPULARITY: " + NumberHelper.getWordedVersion(country.getGovernment().getPopularity()) +  "%, INT. POP: " + NumberHelper.getWordedVersion(country.getGovernment().getInternationalPopularity()) + "%"+ "\n";
        String days = "DAYS: " + mDays +", WEEKS: " + mWeeks + ", MONTHS: "+ mMonths;
        updateText.setText(nationStats + economyStats + popularity + days);
    }

    private class UpdateTimerAsyncTask extends AsyncTask<Void, Void, Void>{

        private boolean mIsRunning = true;
        private Calendar mCalendar;

        private int seconds, minutes, hours, days, weeks, months, years;
        private boolean checkedWeek = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
        protected Void doInBackground(Void... params) {
            while(mIsRunning) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                mCalendar.add(Calendar.HOUR, 1);
                hours +=1;

                //mCalendar.add(Calendar.MINUTE, 1);
                //minutes += 1;
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

            if(minutes >= 60){
                hours += 1;
                minutes = 0;
            }
            if (hours >= 24){
                days += 1;
                hours = 0;
                minutes = 0;
                Model.getCountry().updateDaily();
            }
            if(days == 7 || (days != 0 && days % 7 == 0)){
                if(!checkedWeek) {
                    checkedWeek = true;
                    minutes = 0;
                    weeks += 1;
                    Model.getCountry().updateWeekly();
                }
            }
            else
            {
                checkedWeek = false;
            }
            if(days >= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
                months += 1;
                days = 0;
                weeks = 0;
                Model.getCountry().updateMonthly();
            }


            mDays = days;
            mWeeks = weeks;
            mMonths = months;

            mTimerText.setText(mCalendar.getTime().toString());//(mCalendar.HOUR_OF_DAY + ":" + mCalendar.MINUTE + ":" + mCalendar.SECOND + "\n" +
                    //mCalendar.DAY_OF_MONTH + " " + mCalendar.getDisplayName(mCalendar.MONTH, Calendar.LONG, Locale.getDefault()) + ", " + mCalendar.YEAR);


            mIsRunning = mScreenIsRunning;

            updateStats();

            super.onProgressUpdate(values);

        }

        String GetMonthText(int i){
            return getResources().getStringArray(R.array.months)[i];
        }
    }

}
