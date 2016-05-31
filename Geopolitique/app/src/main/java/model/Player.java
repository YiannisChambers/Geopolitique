package model;

import data.CountryRepo;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class Player extends RealmObject {
    @PrimaryKey
    long mID;

    Country mCountry;

    public Player(){}

    public Player(Country country){
        mCountry = country;
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
}
