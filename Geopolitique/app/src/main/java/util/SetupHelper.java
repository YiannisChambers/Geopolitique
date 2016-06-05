package util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import geopolitique.id11699156.com.geopolitique.CabinetActivity;
import geopolitique.id11699156.com.geopolitique.IssuesActivity;
import geopolitique.id11699156.com.geopolitique.PoliciesScreen;
import geopolitique.id11699156.com.geopolitique.PollsScreen;
import geopolitique.id11699156.com.geopolitique.R;
import geopolitique.id11699156.com.geopolitique.StatisticsActivity;

/**
 * Created by yiannischambers on 16/05/2016.
 */
public class SetupHelper {

    public static ArrayAdapter<CharSequence> setUpSpinnerAdapter(Context context,  int arrayId){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                arrayId, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        return adapter;
    }

    public static ArrayAdapter<String> setUpSpinnerAdapter(Context context, String[] array){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return spinnerArrayAdapter;
    }

    public static void setUpToolBar(AHBottomNavigation bottomNavigation, int currentItem){

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Cabinet", R.drawable.ic_group_white_24dp);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Policies", R.drawable.ic_description_white_24dp);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Home", R.drawable.ic_account_balance_white_24dp);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Issues", R.drawable.ic_sms_failed_white_24dp);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Polls", R.drawable.ic_poll_white_24dp);
        //AHBottomNavigationItem item6 = new AHBottomNavigationItem("Statistics", R.drawable.ic_trending_up_white_24dp);

        // Add items
        bottomNavigation.addItem(item1); //Cabinet
        bottomNavigation.addItem(item2); //Policies
        bottomNavigation.addItem(item3); //Issues
        bottomNavigation.addItem(item4); //Home
        bottomNavigation.addItem(item5); //Polls
        //bottomNavigation.addItem(item6); //Statistics

        bottomNavigation.setCurrentItem(currentItem);

        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#f63d2b"));
        bottomNavigation.setAccentColor(Color.parseColor("#90A4Ae"));
        bottomNavigation.setBackgroundColor(Color.BLACK);

        bottomNavigation.setForceTitlesDisplay(true);

    }
}
