/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import java.util.Calendar;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Player class that stores a player's Country and
 * the time of the Country.
 */
public class Player extends RealmObject {
    @PrimaryKey
    private long mID;

    private long mTime;

    private Country mCountry;

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
