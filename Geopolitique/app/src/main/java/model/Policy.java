/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Policy extends RealmObject{
    @PrimaryKey
    private long mID;

    private String mName;

    private String mDescription;

    private RealmList<Effect> mEffects;

    private int mSize;

    private double mCost;

    private int mTimeToComplete;

    private int mTimeRemaining;

    private String mMinistry;

    public Policy(){}

    public Policy(String name, String description, RealmList<Effect> effects, int size, double cost, String ministryCode) {
        mName = name;
        mDescription = description;
        mEffects = effects;
        mSize = size;
        mCost = cost;
        mMinistry = ministryCode;
    }

    public void setTimeToComplete(int timeToComplete){
        mTimeToComplete = timeToComplete;
        mTimeRemaining = mTimeToComplete;
    }

    public long getID() {
        return mID;
    }

    public RealmList<Effect> getEffects() {
        return mEffects;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public int getSize() {
        return mSize;
    }

    public double getCost() {
        return mCost;
    }

    public int getTimeRemaining() {
        return mTimeRemaining;
    }

    public int getTimeToComplete() {
        return mTimeToComplete;
    }

    public void decrementTimeRemaining(){
        mTimeRemaining -= 1;
    }

    /*public void changeCost(double value){
        int ratio = (int)mCost / mTimeToComplete;

        if(mCost - value < 0){
            mCost = 0;
        }
        else{
            mCost += value;
        }
        int newRatio = (int)mCost / mTimeToComplete;

        int expandBy = ratio - newRatio;
        mTimeToComplete *= expandBy;
        setTimeToComplete(mTimeToComplete);
    }
    */

    public String getMinistry() {
        return mMinistry;
    }

    public void setID(long mID) {
        this.mID = mID;
    }
}
