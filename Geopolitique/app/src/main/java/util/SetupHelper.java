package util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

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
}
