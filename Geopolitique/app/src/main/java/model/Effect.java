/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Effect class that stores an effect on a country property
 * with the name of the property-to-affect and the property value
 */
public class Effect extends RealmObject {

    @PrimaryKey
    private long mID;

    private String mProperty;

    private int mEffect;

    /**
     * Default constructor
     */
    public Effect() { }

    /**
     * Full constructor for Effect with property and effect value
     * @param countryProperty
     * @param effect
     */
    public Effect(String countryProperty, int effect) {
        mProperty = countryProperty;
        mEffect = effect;
    }

    /**
     * Get the Effect's ID.
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Get the Effect's property - that is, the country property that the effect affects
     * @return
     */
    public String getProperty() {
        return mProperty;
    }

    /**
     * Get the effect value
     * @return
     */
    public int getEffect() {
        return mEffect;
    }

    /**
     * Set the effect's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}
