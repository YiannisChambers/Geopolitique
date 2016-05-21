package geopolitique.id11699156.com.geopolitique;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Minister {
    String mFirstName;
    String mLastName;
    int mKnowledge;
    int mExperience;
    int mWorkload;
    int mCurrentWorkload;
    LinkedList<Policy> mPolicies;


    public Minister() {
        mFirstName = "John";
        mLastName = "Smith";
        mKnowledge = new Random().nextInt(10) + 1;
        mExperience = new Random().nextInt(10) + 1;
        mWorkload = mKnowledge * mExperience;
        mCurrentWorkload = 0;
        mPolicies = new LinkedList<Policy>();
    }

    public Minister(String first, String last, int knowledge, int experience) {
        mFirstName = first;
        mLastName = last;
        mKnowledge = knowledge;
        mExperience = experience;
        mWorkload = mKnowledge * mExperience;
        mCurrentWorkload = 0;
        mPolicies = new LinkedList<Policy>();
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

    public LinkedList<Policy> getPolicies() {
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

    public boolean createdScandal(){
        int i = new Random().nextInt(100);
        if(i > mExperience){
            return true;
        }
        return false;
    }
}
