package geopolitique.id11699156.com.geopolitique;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Policy {
    String mName;
    String mDescription;
    LinkedList<Effect> mEffects;
    int mSize;
    double mCost;
    int mTimeToComplete;
    int mTimeRemaining;
    String mMinistry;
    double mPopularity;

    public Policy(String name, String description, LinkedList<Effect> effects, int size, double cost, String ministryCode) {
        mName = name;
        mDescription = description;
        mEffects = new LinkedList<Effect>(effects);
        mSize = size;
        mCost = cost;
        mMinistry = ministryCode;
    }

    public void setTimeToComplete(int timeToComplete){
        mTimeToComplete = timeToComplete;
        mTimeRemaining = mTimeToComplete;
    }

    public LinkedList<Effect> getEffect() {
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

    public void changeCost(double value){
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

    public String getMinistry() {
        return mMinistry;
    }
}
