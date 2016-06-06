/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Issue class that represents an issue which the
 * Government must make a snap decision on.
 */
public class Issue extends RealmObject {
    @PrimaryKey
    private long mID;

    private String mName;
    private String mDescription;
    private RealmList<Option> mOptions;

    //TODO:
    //Add results - RealmList<String> results - that appears as a Dialog when an option is selected.

    private Option mSelectedOption;

    private boolean mIsResolved;

    private boolean mIsFinished;

    private int mStaleFactor;

    /**
     * Default constructor
     */
    public Issue() {
    }

    ;

    /**
     * Constructor that initialises the Issue's
     * name, description and options to select
     *
     * @param name
     * @param description
     * @param options
     */
    public Issue(String name, String description, RealmList<Option> options) {
        mName = name;
        mDescription = description;
        mOptions = options;
        mIsFinished = false;
        mStaleFactor = 0;
        isResolved();
    }

    /**
     * Get an issue's ID
     *
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Get an issue's options to select
     *
     * @return
     */
    public RealmList<Option> getOptions() {
        return mOptions;
    }

    /**
     * Get the option that the Government has selected
     *
     * @return
     */
    public Option getSelectedOption() {
        return mSelectedOption;
    }

    /**
     * Get the description of the issue
     *
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Get the title of an issue
     *
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Select an option at the specified position
     * as the Government's decision.
     *
     * @param position
     */
    public void selectOption(int position) {
        mSelectedOption = mOptions.get(position);
        mIsResolved = true;
    }

    /**
     * Check if the Government has resolved the issue
     * by selecting an option
     *
     * @return
     */
    public boolean isResolved() {
        mIsResolved = !(mSelectedOption == null);
        return mIsResolved;
    }

    /**
     * Check if the resolved Issue has had its effects calculated
     *
     * @return
     */
    public boolean isFinished() {
        return mIsFinished;
    }

    /**
     * Signify that the issue has had its effects calculated
     */
    public void finishIssue() {
        mIsFinished = true;
    }

    /**
     * Get the stale factor - the amount of time
     * the Government has not decided on the issue
     * @return
     */
    public int getStaleFactor() {
        return mStaleFactor;
    }

    /**
     * Increment the stale factor
     */
    public void incrementStaleFactor() {
        mStaleFactor += 1;
    }

    /**
     * Set the ID of the Issue
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}

