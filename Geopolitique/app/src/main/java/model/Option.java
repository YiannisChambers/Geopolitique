/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Option class that stores the Option for a
 * specific Issue, with its description
 * and effects.
 */
public class Option extends RealmObject {
    @PrimaryKey
    private long mID;

    private String mDescription;

    private RealmList<Effect> mEffects;

    /**
     * Default Constructor
     */
    public Option() {
    }

    /**
     * Full Constructor setting the description of the option
     * and its effects.
     */

    public Option(String description, RealmList<Effect> effects) {
        mDescription = description;
        mEffects = effects;
    }


    /**
     * Get the Option's ID
     *
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Set the Option's ID
     *
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }

    /**
     * Get the Option's Description.
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Get the Option's effects
     * @return
     */
    public RealmList<Effect> getEffects() {
        return mEffects;
    }
}
