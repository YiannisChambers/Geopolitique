/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package model;

import util.Constants;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Government class that holds a government's
 * Leader, Cabinet and popularity figures.
 */
public class Government extends RealmObject {
    @PrimaryKey
    private long mID;

    private Leader mLeader;

    private Cabinet mCabinet;

    private double mPopularity;
    private double mInternationalPopularity;

    private RealmList<Issue> mIssues;

    /**
     * Default constructor
     */
    public Government() {
    }

    /**
     * Full constructor that takes the Government's
     * leader and cabinet
     *
     * @param leader
     * @param cabinet
     */
    public Government(Leader leader, Cabinet cabinet) {
        mCabinet = cabinet;
        mPopularity = 51.00f;
        mInternationalPopularity = 51.00f;
        mIssues = new RealmList<Issue>();
        mLeader = leader;
    }

    /**
     * Get the Government's ID
     *
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Get the Government's Cabinet
     *
     * @return
     */
    public Cabinet getCabinet() {
        return mCabinet;
    }

    /**
     * Get the Government's Leader
     *
     * @return
     */
    public Leader getLeader() {
        return mLeader;
    }

    /**
     * Get the Government's total spending (the policy costs).
     *
     * @return
     */
    public double getGovernmentSpending() {
        return mCabinet.getTotalPolicyCosts();
    }

    /**
     * Get the Government's popularity
     *
     * @return
     */
    public double getPopularity() {
        return mPopularity;
    }

    /**
     * Get the Government's international popularity
     *
     * @return
     */
    public double getInternationalPopularity() {
        return mInternationalPopularity;
    }

    /**
     * Change the Governement's popularity by a certain value
     *
     * @param value
     */
    public void changePopularity(double value) {
        //If the value makes the popularity go below 0, clamp it at 0...
        if (mPopularity - value < 0) {
            mPopularity = 0;
        } else if (mPopularity + value > 100) {
            //..and if the value makes the popularity go above 100, clamp it at 100...
            mPopularity = 100;
        } else {
            mPopularity += value;
        }

    }

    /**
     * Change the Governement's international popularity by a certain value
     *
     * @param value
     */
    public void changeInternationalPopularity(double value) {
        //If the value makes the popularity go below 0, clamp it at 0...
        if (mInternationalPopularity - value < 0) {
            mInternationalPopularity = 0;
        } else if (mInternationalPopularity + value > 100) {
            //..and if the value makes the popularity go above 100, clamp it at 100...
            mInternationalPopularity = 100;
        } else {
            mInternationalPopularity += value;
        }
    }

    /**
     * Check if the Government currently has a Minister in a specific portfolio
     *
     * @param department
     * @return
     */
    public boolean checkIfMinister(String department) {
        //Check if the current minister for a department is not assigned (i.e. equals NULL)

        if (department.equals(Constants.FOREIGN_AFFAIRS)) {
            return mCabinet.getForeignAffairsMinister() == null;
        } else if (department.equals(Constants.TREASURY)) {
            Minister treasurer = mCabinet.getTreasurer();
            return mCabinet.getTreasurer() == null;
        } else if (department.equals(Constants.DEFENCE)) {
            return mCabinet.getDefenceMinister() == null;
        } else if (department.equals(Constants.EDUCATION)) {
            return mCabinet.getEducationMinister() == null;
        } else if (department.equals(Constants.HEALTH)) {
            return mCabinet.getHealthMinister() == null;
        }
        return false;
    }

    /**
     * Add a policy to a Government
     *
     * @param policy
     */
    public void addPolicy(Policy policy) {
        //Get the corrent minister for the policy (based on the Ministry of the Policy)
        String department = policy.getMinistry();
        if (department.equals(Constants.FOREIGN_AFFAIRS)) {
            mCabinet.getForeignAffairsMinister().addPolicy(policy);
        } else if (department.equals(Constants.TREASURY)) {
            mCabinet.getTreasurer().addPolicy(policy);
        } else if (department.equals(Constants.DEFENCE)) {
            mCabinet.getDefenceMinister().addPolicy(policy);
        } else if (department.equals(Constants.EDUCATION)) {
            mCabinet.getEducationMinister().addPolicy(policy);
        } else if (department.equals(Constants.HEALTH)) {
            mCabinet.getHealthMinister().addPolicy(policy);
        }
    }

    /**
     * Remove an existing policy from a Government
     *
     * @param policy
     */
    public void removePolicy(Policy policy) {
        //Get the corrent minister for the policy (based on the Ministry of the Policy)
        String department = policy.getMinistry();
        if (department.equals(Constants.FOREIGN_AFFAIRS)) {
            mCabinet.getForeignAffairsMinister().removePolicy(policy);
        } else if (department.equals(Constants.TREASURY)) {
            mCabinet.getTreasurer().removePolicy(policy);
        } else if (department.equals(Constants.DEFENCE)) {
            mCabinet.getDefenceMinister().removePolicy(policy);
        } else if (department.equals(Constants.EDUCATION)) {
            mCabinet.getEducationMinister().removePolicy(policy);
        } else if (department.equals(Constants.HEALTH)) {
            mCabinet.getHealthMinister().removePolicy(policy);
        }
    }


    /**
     * Add an issue to the Government
     * @param issue
     */
    public void addIssue(Issue issue) {
        mIssues.add(issue);
    }

    /**
     * Get the issues that the Government has
     * @return
     */
    public RealmList<Issue> getIssues() {
        return mIssues;
    }

    /**
     * Set a government's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}