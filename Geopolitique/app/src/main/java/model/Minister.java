/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import java.util.Random;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Minister extends RealmObject{
    @PrimaryKey
    private long mID;

    private String mFirstName;
    private String mLastName;
    private int mKnowledge;
    private int mExperience;
    private int mWorkload;
    private int mCurrentWorkload;

    private RealmList<Policy> mPolicies;

    public Minister() {
        mFirstName = "John";
        mLastName = "Smith";
        mKnowledge = new Random().nextInt(10) + 1;
        mExperience = new Random().nextInt(10) + 1;
        mWorkload = mKnowledge * mExperience;
        mCurrentWorkload = 0;
        mPolicies = new RealmList<Policy>();
    }

    public Minister(String first, String last, int knowledge, int experience) {
        mFirstName = first;
        mLastName = last;
        mKnowledge = knowledge;
        mExperience = experience;
        mWorkload = mKnowledge * mExperience;
        mCurrentWorkload = 0;
        mPolicies = new RealmList<Policy>();
    }


    public long getID() {
        return mID;
    }

    public int getExperience() {
        return mExperience;

    }

    public int getKnowledge() {
        return mKnowledge;
    }

    public int getWorkload() {
        return mWorkload;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public int getCurrentWorkload() {
        return mCurrentWorkload;
    }

    public RealmList<Policy> getPolicies() {
        return mPolicies;
    }

    public void addPolicy(Policy policy) {

        if(mCurrentWorkload + policy.getSize() > mWorkload)
        {
            policy.setTimeToComplete((policy.getSize()) * 2);
        }
        else {
            policy.setTimeToComplete(policy.getSize());
        }
        mPolicies.add(policy);
        recalculateCurrentWorkload();
    }

    public void removePolicy(Policy policy) {
        mPolicies.remove(policy);
        recalculateCurrentWorkload();
    }

    private void recalculateCurrentWorkload() {
        mCurrentWorkload = 0;
        for (int i = 0; i < mPolicies.size(); i++) {
            mCurrentWorkload += mPolicies.get(i).getSize();
        }
    }

    public double getTotalPolicyCosts(){
        double cost = 0;
        for(int i = 0; i < mPolicies.size(); i++)
        {
            cost += mPolicies.get(i).getCost();
        }
        return cost;
    }



    public void setID(long mID) {
        this.mID = mID;
    }

}
