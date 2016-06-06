/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package util;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import geopolitique.id11699156.com.geopolitique.R;

/**
 * Helper class to enable the easy set up of Spinners and
 * the Toolbar used through all Activities
 */
public class SetupHelper {

    public static ArrayAdapter<CharSequence> setUpSpinnerAdapter(Context context, int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                arrayId, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        return adapter;
    }

    public static ArrayAdapter<String> setUpSpinnerAdapter(Context context, String[] array) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return spinnerArrayAdapter;
    }

}
