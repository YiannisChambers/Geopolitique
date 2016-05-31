package model;

import java.util.Arrays;
import java.util.LinkedList;

import geopolitique.id11699156.com.geopolitique.Constants;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Cabinet {
    Minister mForeignAffairsMinister;
    Minister mTreasurer;
    Minister mEducationMinister;
    Minister mDefenceMinister;
    Minister mHealthMinister;

    public Cabinet() {
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

    public int getTotalWorkload() {
        int totalWorkload = 0;

        totalWorkload += mForeignAffairsMinister != null ? mForeignAffairsMinister.getWorkload() : 0;
        totalWorkload += mEducationMinister != null ? mEducationMinister.getWorkload() : 0;
        totalWorkload += mDefenceMinister != null ? mDefenceMinister.getWorkload() : 0;
        totalWorkload += mHealthMinister != null ? mHealthMinister.getWorkload() : 0;
        totalWorkload += mTreasurer != null ? mTreasurer.getWorkload() : 0;

        return totalWorkload;
    }

    public int getTotalCurrentWorkload() {
        int totalCurrentWorkload = 0;

        totalCurrentWorkload += mForeignAffairsMinister != null ? mForeignAffairsMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mEducationMinister != null ? mEducationMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mDefenceMinister != null ? mDefenceMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mHealthMinister != null ? mHealthMinister.getCurrentWorkload() : 0;
        totalCurrentWorkload += mTreasurer != null ? mTreasurer.getCurrentWorkload() : 0;

        return totalCurrentWorkload;
    }

    public double getTotalPolicyCosts(){
        double totalPolicyCosts = 0;

        totalPolicyCosts += mForeignAffairsMinister != null ? mForeignAffairsMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mEducationMinister != null ? mEducationMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mDefenceMinister != null ? mDefenceMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mHealthMinister != null ? mHealthMinister.getTotalPolicyCosts() : 0;
        totalPolicyCosts += mTreasurer != null ? mTreasurer.getTotalPolicyCosts() : 0;

        return totalPolicyCosts;
    }

    public LinkedList<Policy> getTotalPolicies(){
        LinkedList<Policy> policies = new LinkedList<>();// totalPolicyCosts = 0;

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

    public LinkedList<Minister> getMinisters(){
        Minister[] ministers = {mTreasurer, mDefenceMinister, mForeignAffairsMinister, mHealthMinister, mEducationMinister};
        return new LinkedList<Minister>(Arrays.asList(ministers));
    }

    public Minister getMinister(int position){
        return getMinisters().get(position);
    }

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

}
