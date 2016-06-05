package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class ExistingCountry extends RealmObject {
    @PrimaryKey
    long mID;

    Country mCountry;

    public ExistingCountry(){
    }

    public ExistingCountry(Country country){
        mCountry = country;
    }

    public long getID() {
        return mID;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        this.mCountry = country;
    }

    public void setID(long mID) {
        this.mID = mID;
    }
}

