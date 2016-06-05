package geopolitique.id11699156.com.geopolitique;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import data.ExistingCountryRepo;
import data.IssueRepo;
import data.MinisterRepo;
import data.PlayerRepo;
import data.PolicyRepo;
import model.Country;
import model.ExistingCountry;
import model.Leader;
import model.Model;
import model.Player;
import util.SetupHelper;

public class CreateExistingNationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    LinkedList<ExistingCountry> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_existing_nation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countries = ExistingCountryRepo.getAllExistingCountries();
        setUpSpinner();
    }

    private void setUpSpinner(){
        String names[] = new String[countries.size()];
        for(int i = 0; i < countries.size(); i++){
            names[i] = (countries.get(i).getCountry().getCountryName());
        }
        Spinner spinner = (Spinner)findViewById(R.id.create_existing_nation_activity_choose_existing_spinner);

        spinner.setAdapter(SetupHelper.setUpSpinnerAdapter(this, names));
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Comment me what i am doing here!!!! verdammnochmal
     *
     * @param view
     */
    public void onCreateNationClick(View view) {
        Spinner spinner = (Spinner)findViewById(R.id.create_existing_nation_activity_choose_existing_spinner);
        Country country = countries.get(spinner.getSelectedItemPosition()).getCountry();   //new Country("Australia", 23000000, 1.4f, R.drawable.australia_flag);
        Player player = new Player(country);
        PlayerRepo.createNewPlayer(player);


        //Model.setUpTestData();

        Intent startIntent = new Intent(this, HomeScreenActivity.class);
        startActivity(startIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImageView imageView = (ImageView)findViewById(R.id.create_existing_nation_activity_choose_existing_image);
        TextView textView =  (TextView)findViewById(R.id.create_existing_nation_activity_choose_existing_text);

        textView.setText(countries.get(position).getCountry().getCountryName());
        imageView.setImageResource(countries.get(position).getCountry().getPictureID());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
