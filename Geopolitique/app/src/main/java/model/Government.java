package model;

import data.GovernmentRepo;
import util.Constants;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Government extends RealmObject {
    @PrimaryKey
    long mID;

    Leader mLeader;

    Cabinet mCabinet;

    double mPopularity;
    double mInternationalPopularity;

    RealmList<Issue> mIssues;

    public Government(){
        this(new Leader());
    }

    public Government(Leader leader) {
        mLeader = leader;
        mCabinet = new Cabinet();
        mPopularity = 51.00f;
        mInternationalPopularity = 51.00f;
        mIssues = new RealmList<Issue>();
    }

    public long getID() {
        return mID;
    }

    public Cabinet getCabinet() {
        return mCabinet;
    }

    public Leader getLeader() {
        return mLeader;
    }

    public double getGovernmentSpending(){
        return mCabinet.getTotalPolicyCosts();
    }

    public double getPopularity()
    {
        return mPopularity;
    }

    public double getInternationalPopularity() {
        return mInternationalPopularity;
    }

    public void changePopularity(double value){
        if(mPopularity - value < 0){
            mPopularity = 0;
        }
        else if(mPopularity + value > 100){
            mPopularity = 100;
        }
        else{
            mPopularity += value;
        }
        //update();

    }

    public void changeInternationalPopularity(double value){
        if(mInternationalPopularity - value < 0){
            mInternationalPopularity = 0;
        }
        else if(mInternationalPopularity + value > 100){
            mInternationalPopularity = 100;
        }
        else{
            mInternationalPopularity += value;
        }
        //update();

    }

    public boolean checkIfMinister(String department){
        if(department.equals(Constants.FOREIGN_AFFAIRS)){
            return mCabinet.getForeignAffairsMinister() == null;
        }
        else if(department.equals(Constants.TREASURY)){
            Minister treasurer = mCabinet.getTreasurer();
            return mCabinet.getTreasurer() == null;
        }
        else if(department.equals(Constants.DEFENCE))
        {
           return mCabinet.getDefenceMinister() == null;
        }
        else if(department.equals(Constants.EDUCATION)){
            return mCabinet.getEducationMinister() == null;
        }
        else if(department.equals(Constants.HEALTH)){
            return mCabinet.getHealthMinister() == null;
        }
        return false;
    }

    public void addPolicy(Policy policy){
       String department = policy.getMinistry();
        if(department.equals(Constants.FOREIGN_AFFAIRS)){
            mCabinet.getForeignAffairsMinister().addPolicy(policy);
        }
        else if(department.equals(Constants.TREASURY)){
            mCabinet.getTreasurer().addPolicy(policy);
        }
        else if(department.equals(Constants.DEFENCE))
        {
            mCabinet.getDefenceMinister().addPolicy(policy);
        }
        else if(department.equals(Constants.EDUCATION)){
            mCabinet.getEducationMinister().addPolicy(policy);
        }
        else if(department.equals(Constants.HEALTH)){
            mCabinet.getHealthMinister().addPolicy(policy);
        }
    }

    public void addIssue(Issue issue){
        mIssues.add(issue);
        //update();
    }

    public RealmList<Issue> getIssues() {
        return mIssues;
    }

    public Issue getIssue(int position){
        return mIssues.get(position);
    }

    public void resolveIssue(int position, int optionPosition){
        mIssues.get(position).selectOption(optionPosition);
    }

    public void update(){
        //GovernmentRepo.updateGovernment(this);
    }
}