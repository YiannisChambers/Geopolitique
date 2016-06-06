/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Leader class that stores a nation's leader
 */
public class Leader extends RealmObject {
    @PrimaryKey
    private long mID;

    private String mFirstName;
    private String mLastName;
    private String mTitle;


    /**
     * Default constructor with default test values.
     */
    public Leader(){
        mFirstName = "John";
        mLastName = "Smith";
        mTitle = "Prime Minister";
    }

    /**
     * Fully constructor that takes the first and last names
     * of a leader, and their official title
     * @param first
     * @param last
     * @param title
     */
    public Leader(String first, String last, String title) {
        mFirstName = first;
        mLastName = last;
        mTitle = title;
    }

    /**
     * Get Leader's ID
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Get a Leader's Full Name
     * @return
     */
    public String getFullName() {
        return mFirstName + " " + mLastName;
    }

    /**
     * Get a Leader's First Name
     * @return
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Get a Leader's Last Name
     * @return
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Get a Leader's official Title
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the Leader's Full Name with their title
     * @return
     */
    public String getFullNameWithTitle() {
        return mTitle + " " + mFirstName + " " + mLastName;
    }

    /**
     * Get the Leader's shortened Name with their title
     * @return
     */
    public String getShortNameWithTitle() {
        return mTitle + " " + mLastName;
    }

    /**
     * Set the Leader's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}
