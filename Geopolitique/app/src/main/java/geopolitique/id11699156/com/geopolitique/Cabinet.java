package geopolitique.id11699156.com.geopolitique;

import java.util.LinkedList;

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

    public void setDefenceMinister(Minister mDefenceMinister) {
        this.mDefenceMinister = mDefenceMinister;
    }

    public void setEducationMinister(Minister mEducationMinister) {
        this.mEducationMinister = mEducationMinister;
    }

    public void setForeignAffairsMinister(Minister mForeignAffairsMinister) {
        this.mForeignAffairsMinister = mForeignAffairsMinister;
    }

    public void setHealthMinister(Minister mHealthMinister) {
        this.mHealthMinister = mHealthMinister;
    }

    public void setTreasurer(Minister mTreasurer) {
        this.mTreasurer = mTreasurer;
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
}
