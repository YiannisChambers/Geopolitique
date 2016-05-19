package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView mTimerText;
    private boolean mScreenIsRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTimerText = (TextView)findViewById(R.id.home_screen_time_text);
        new UpdateTimerAsyncTask().execute();



        //THIS IS TEST DATA
        //ADD MODEL AND LEADER HERE
        //MIGHT ONLY BE ABLE TO AFTER YOU SEPERATE THEM


        Button cabinetButton = (Button)findViewById(R.id.home_screen_cabinet_button);
        final Intent cabinetIntent = new Intent(this, CabinetActivity.class);
        cabinetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(cabinetIntent);
            }
        });

    }

    private class UpdateTimerAsyncTask extends AsyncTask<Void, Void, Void>{

        private boolean mIsRunning = true;
        private Calendar mCalendar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mCalendar = Calendar.getInstance();
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(mIsRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress();

                mCalendar.add(Calendar.SECOND, 1);
                //mCalendar.set(mCalendar.YEAR, mCalendar.MONTH, mCalendar.DAY_OF_MONTH, mCalendar.HOUR_OF_DAY, mCalendar.MINUTE, mCalendar.SECOND + 1);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            mTimerText.setText(mCalendar.getTime().toString());//(mCalendar.HOUR_OF_DAY + ":" + mCalendar.MINUTE + ":" + mCalendar.SECOND + "\n" +
                    //mCalendar.DAY_OF_MONTH + " " + mCalendar.getDisplayName(mCalendar.MONTH, Calendar.LONG, Locale.getDefault()) + ", " + mCalendar.YEAR);

            mIsRunning = mScreenIsRunning;

            super.onProgressUpdate(values);

        }

        String GetMonthText(int i){
            return getResources().getStringArray(R.array.months)[i];
        }
    }

}
