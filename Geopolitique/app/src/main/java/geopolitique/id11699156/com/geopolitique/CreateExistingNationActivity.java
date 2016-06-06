/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import data.ExistingCountryRepo;
import data.PlayerRepo;
import model.Country;
import model.ExistingCountry;
import model.Player;
import util.SetupHelper;

/**
 * CreateExistingNation activity to enable a player to select a starting
 * nation to begin their game with.
 */
public class CreateExistingNationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LinkedList<ExistingCountry> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_existing_nation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get all Existing Countries from the Database
        countries = ExistingCountryRepo.getAllExistingCountries();
        setUpSpinner();
    }

    /**
     * Sets up the Spinner with the nation names in them
     */
    private void setUpSpinner(){
        String names[] = new String[countries.size()];
        //For each existing country...
        for(int i = 0; i < countries.size(); i++){
            //...store the name in the array
            names[i] = (countries.get(i).getCountry().getCountryName());
        }


        Spinner spinner = (Spinner)findViewById(R.id.create_existing_nation_activity_choose_existing_spinner);

        //Send the array to helper to initialise the spinner
        spinner.setAdapter(SetupHelper.setUpSpinnerAdapter(this, names));

        //Make the first option selected
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * On button click, start the game using the selected Existing Country
     * @param view
     */
    public void onCreateNationClick(View view) {
        Spinner spinner = (Spinner)findViewById(R.id.create_existing_nation_activity_choose_existing_spinner);

        //Get Existing Country from Spinner
        Country country = countries.get(spinner.getSelectedItemPosition()).getCountry();

        //Create a new player with the Country and save it to the database
        Player player = new Player(country);
        PlayerRepo.createNewPlayer(player);

        //Start the Home Screen
        Intent startIntent = new Intent(this, HomeScreenActivity.class);
        startActivity(startIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImageView imageView = (ImageView)findViewById(R.id.create_existing_nation_activity_choose_existing_image);
        TextView textView =  (TextView)findViewById(R.id.create_existing_nation_activity_choose_existing_text);

        //Change the country name and the flag.
        textView.setText(countries.get(position).getCountry().getCountryName());
        imageView.setImageResource(countries.get(position).getCountry().getPictureID());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
