/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import java.util.Arrays;
import java.util.LinkedList;
import util.Constants;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Cabinet class to store the Ministers of a player's Government.
 */
public class Cabinet extends RealmObject{
    @PrimaryKey
    long mID;

    Minister mForeignAffairsMinister;
    Minister mTreasurer;
    Minister mEducationMinister;
    Minister mDefenceMinister;
    Minister mHealthMinister;

    /**
     * Required empty constructor
     */
    public Cabinet() {}

    /**
     * Returns the ID of the object;
     * @return
     */
    public long getID() {
        return mID;
    }



    public Minister getDefenceMinister() {
        return mDefenceMinister;
    }

    public Minister getEducationMinister() {
        return mEducationMinister;
    }

    public Minister getForeignAffairsMinister() {
        return mForeignAffairsMinister;
    }

    public Minister getHealthMinister() {
        return mHealthMinister;
    }

    public Minister getTreasurer() {
        return mTreasurer;
    }

    /**
     * Set the Government's Defence minister
     * @param defenceMinister
     */
    public void setDefenceMinister(Minister defenceMinister) {
        if(mDefenceMinister != null) {
            if (mDefenceMinister.getPolicies().size() > 0) {
                for (int i = 0; i < mDefenceMinister.getPolicies().size(); i++) {
                    defenceMinister.addPolicy(mDefenceMinister.getPolicies().get(i));
                }
            }
        }
        this.mDefenceMinister = defenceMinister;
    }

    /**
     * Set the Government's Education Minister
     * @param educationMinister
     */
    public void setEducationMinister(Minister educationMinister) {
        if(mEducationMinister != null){
            if(mEducationMinister.getPolicies().size() > 0){
                for(int i = 0; i < mEducationMinister.getPolicies().size(); i++){
                    educationMinister.addPolicy(mEducationMinister.getPolicies().get(i));
                }
            }
        }

        this.mEducationMinister = educationMinister;
    }

    /**
     * Set the Government's Foreign Affairs Minister
     * @param foreignAffairsMinister
     */
    public void setForeignAffairsMinister(Minister foreignAffairsMinister) {
        if(mForeignAffairsMinister != null) {
            if (mForeignAffairsMinister.getPolicies().size() > 0) {
                for (int i = 0; i < mForeignAffairsMinister.getPolicies().size(); i++) {
                    foreignAffairsMinister.addPolicy(mForeignAffairsMinister.getPolicies().get(i));
                }
            }
        }
        this.mForeignAffairsMinister = foreignAffairsMinister;
    }

    /**
     * Set the Government's Health Minister
     * @param healthMinister
     */
    public void setHealthMinister(Minister healthMinister) {
        if(mHealthMinister != null) {
            if (mHealthMinister.getPolicies().size() > 0) {
                for (int i = 0; i < mHealthMinister.getPolicies().size(); i++) {
                    healthMinister.addPolicy(mHealthMinister.getPolicies().get(i));
                }
            }
        }
        this.mHealthMinister = healthMinister;
    }

    /**
     * Set the Government's Treasurer
     * @param treasurer
     */
    public void setTreasurer(Minister treasurer) {
        if(mTreasurer != null) {
            if (mTreasurer.getPolicies().size() > 0) {
                for (int i = 0; i < mTreasurer.getPolicies().size(); i++) {
                    mTreasurer.addPolicy(mTreasurer.getPolicies().get(i));
                }
            }
        }
        this.mTreasurer = treasurer;
    }

    /**
     * Get the complete workload for all Ministers
     * @return
     */
    public int getTotalWorkload() {
        int totalWorkload = 0;

        //For each Minister, if they've been assigned, get their workload and add it to the total
        totalWorkload += mForeignAffairsMinister != null ? mForeignAffairsMinister.getWorkload() : 0;
        totalWorkload += mEducationMinister != null ? mEducationMinister.getWorkload() : 0;
        totalWorkload += mDefenceMinister != null ? mDefenceMinister.getWorkload() : 0;
        totalWorkload += mHealthMinister != null ? mHealthMinister.getWorkload() : 0;
        totalWorkload += mTreasurer != null ? mTreasurer.getWorkload() : 0;

        return totalWorkload;
    }

    /**
     * Get the complete current workload for all Ministers
     * @return
     */
    public int getTotalCurrentWorkload() {
        int totalCurrentWorkload = 0;

        //For each Minister, if they've been assigned, get their current workload and add it to the total
        totalCurrentWorkload += mForeignAffairsMinister != null ? mForeignAffairsMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mEducationMinister != null ? mEducationMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mDefenceMinister != null ? mDefenceMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mHealthMinister != null ? mHealthMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mTreasurer != null ? mTreasurer.getCurrentWorkload() : 0;

        return totalCurrentWorkload;
    }

    /**
     * Get the total costs of all the policies that the Governemnt currently has
     * @return
     */
    public double getTotalPolicyCosts(){
        double totalPolicyCosts = 0;

        totalPolicyCosts += mForeignAffairsMinister != null ? mForeignAffairsMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mEducationMinister != null ? mEducationMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mDefenceMinister != null ? mDefenceMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mHealthMinister != null ? mHealthMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mTreasurer != null ? mTreasurer.getTotalPolicyCosts() : 0;

        return totalPolicyCosts;
    }

    /**
     * Get all the policies that the Government has adopted from each Minister.
     * @return
     */
    public LinkedList<Policy> getTotalPolicies(){
        LinkedList<Policy> policies = new LinkedList<>();

        //For each Minister, if they've been set, add their attached Policies to the list
        if(mForeignAffairsMinister != null)
        {
            policies.addAll(mForeignAffairsMinister.getPolicies());
        }
        if(mEducationMinister != null)
        {
            policies.addAll(mEducationMinister.getPolicies());
        }
        if(mHealthMinister != null)
        {
            policies.addAll(mHealthMinister.getPolicies());
        }
        if(mTreasurer != null)
        {
            policies.addAll(mTreasurer.getPolicies());
        }
        if(mDefenceMinister != null)
        {
            policies.addAll(mDefenceMinister.getPolicies());
        }
        return policies;
    }

    /**
     * Get all the ministers as an LinkedList
     * @return
     */
    public LinkedList<Minister> getMinisters(){
        Minister[] ministers = {mTreasurer, mDefenceMinister, mForeignAffairsMinister, mHealthMinister, mEducationMinister};
        return new LinkedList<Minister>(Arrays.asList(ministers));
    }

    /**
     * Get a Minister at a certain position in the getMinisters() Linked List.
     * This is for setting purposes in the Cabinet screen, to differentiate
     * which Minister has been selected in the List dapter
     * @param position
     * @return
     */
    public Minister getMinister(int position){
        return getMinisters().get(position);
    }

    /**
     * Get the title for a specific Minister at a position
     * in the aforementioned Minister's list.
     * @param position
     * @return
     */
    public static String getTitle(int position){
        switch(position){
            case 0: return Constants.TREASURY;
            case 1: return Constants.DEFENCE;
            case 2: return Constants.FOREIGN_AFFAIRS;
            case 3: return Constants.HEALTH;
            case 4: return Constants.EDUCATION;
            default: return "";
        }
    }

    /**
     * Set a Minister, specified by its position
     * in the aforementioned Ministers' list
     * @param position
     * @return
     */
    public void setMinister(int position, Minister minister){
        switch(position){
            case 0: {setTreasurer(minister); break;}
            case 1: {setDefenceMinister(minister); break;}
            case 2: {setForeignAffairsMinister(minister); break;}
            case 3: {setHealthMinister(minister); break;}
            case 4: {setEducationMinister(minister); break;}
            default: break;
        }
    }

    /**
     * Set the Cabinet's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }
}
