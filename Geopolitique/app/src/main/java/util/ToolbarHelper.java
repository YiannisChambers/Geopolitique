package util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.github.mikephil.charting.charts.Chart;

import geopolitique.id11699156.com.geopolitique.CabinetActivity;
import geopolitique.id11699156.com.geopolitique.HomeScreenActivity;
import geopolitique.id11699156.com.geopolitique.IssuesActivity;
import geopolitique.id11699156.com.geopolitique.PoliciesScreen;
import geopolitique.id11699156.com.geopolitique.PollsScreen;
import geopolitique.id11699156.com.geopolitique.R;

/**
 * Created by yiannischambers on 6/06/2016.
 */
public class ToolbarHelper {

    private static int issueNotes;
    private static int statisticNotes;

    public static AHBottomNavigation mToolbar;

    public static void setUpToolbar(AHBottomNavigation navigation, Context context, int position) {
        mToolbar = navigation;

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Cabinet", R.drawable.ic_group_white_24dp);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Policies", R.drawable.ic_description_white_24dp);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Home", R.drawable.ic_account_balance_white_24dp);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Issues", R.drawable.ic_sms_failed_white_24dp);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Polls", R.drawable.ic_poll_white_24dp);

        // Add items
        mToolbar.addItem(item1); //Cabinet
        mToolbar.addItem(item2); //Policies
        mToolbar.addItem(item3); //Issues
        mToolbar.addItem(item4); //Home
        mToolbar.addItem(item5); //Polls

        mToolbar.setCurrentItem(position);

        // Customize notification (title, background, typeface)
        mToolbar.setNotificationBackgroundColor(Color.parseColor("#f63d2b"));
        mToolbar.setAccentColor(Color.parseColor("#90A4Ae"));
        mToolbar.setBackgroundColor(Color.BLACK);

        mToolbar.setForceTitlesDisplay(true);

        if(issueNotes > 0) {
            mToolbar.setNotification("" + issueNotes, 3);
        }
        else
        {
            mToolbar.setNotification("", 3);
        }

        if(statisticNotes > 0) {
            mToolbar.setNotification("" + statisticNotes, 4);
        }
        else
        {
            mToolbar.setNotification("", 4);
        }
        setListenersForScreen(context, position);
    }

    public static AHBottomNavigation getToolbar() {
        return mToolbar;
    }

    private static Intent getIntentForButton(int position, Context context) {
        switch(position)
        {
            case 0: {
                final Intent cabinetIntent = new Intent(context, CabinetActivity.class);
                cabinetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return cabinetIntent;
            }
            case 1: {
                final Intent policiesIntent = new Intent(context, PoliciesScreen.class);
                policiesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return policiesIntent;
            }

            case 2: {
                final Intent homeScreenIntent = new Intent(context, HomeScreenActivity.class);
                homeScreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return homeScreenIntent;
            }

            case 3: {
                issueNotes = 0;
                mToolbar.setNotification("", 3);
                final Intent issuesIntent = new Intent(context, IssuesActivity.class);
                issuesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return issuesIntent;
            }

            case 4: {
                statisticNotes = 0;
                mToolbar.setNotification("", 4);
                final Intent pollsIntent = new Intent(context, PollsScreen.class);
                pollsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return pollsIntent;
            }

            default: {
                return null;
            }
        }
    }

    public static void setListenersForScreen(Context c, int p) {
        final Context context = c;
        final int buttonPressed = p;
        mToolbar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (buttonPressed != position) {
                    Intent intent = getIntentForButton(position, context);
                    context.startActivity(intent);
                }
            }
        });
    }

    public static void incrementIssueNumber(){
        issueNotes += 1;
        mToolbar.setNotification(issueNotes + "", 3);
        mToolbar.refresh();
    }

    public static void incrementStatisticsNumber(){
        statisticNotes += 1;
        mToolbar.setNotification(statisticNotes + "", 4);
        mToolbar.refresh();
    }
}