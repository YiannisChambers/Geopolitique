/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Existing Country class to hold an existing country
 * that the player can begin their game with.
 */
public class ExistingCountry extends RealmObject {
    @PrimaryKey
    private long mID;

    private Country mCountry;


    /**
     * Default constructor
     */
    public ExistingCountry(){}

    /**
     * Full constructor for Existing Country that takes a country
     * to intialise with.
     * @param country
     */
    public ExistingCountry(Country country){
        mCountry = country;
    }

    /**
     * Get an Existing Country's ID
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Get an Existing Country's Country
     * @return
     */
    public Country getCountry() {
        return mCountry;
    }

    /**
     * Set the Existing Country's country
     * @param country
     */
    public void setCountry(Country country) {
        this.mCountry = country;
    }

    /**
     * Set the Existing Country's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}

