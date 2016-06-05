package model;

import java.util.Calendar;

import data.CountryRepo;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class Player extends RealmObject {
    @PrimaryKey
    long mID;

    long mTime;

    Country mCountry;

    public Player(){}

    public Player(Country country){
        mCountry = country;
        mTime = Calendar.getInstance().getTimeInMillis();
    }

    public long getID() {
        return mID;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country mCountry) {
        this.mCountry = mCountry;
    }

    public long getTime(){
        return mTime;
    }

    public void setTime(long time){
        mTime = time;
    }
}
