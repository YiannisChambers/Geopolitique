package geopolitique.id11699156.com.geopolitique;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Government {
    Leader mLeader;
    Cabinet mCabinet;

    double mPopularity;
    double mInternationalPopularity;

    double mScandalCount;

    LinkedList<Issue> mIssues;

    public Government(){
        this(new Leader());
    }

    public Government(Leader leader) {
        mLeader = leader;
        mCabinet = new Cabinet();
        mPopularity = 51.00f;
        mInternationalPopularity = 51.00f;
        mIssues = new LinkedList<Issue>();
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

    }

    public boolean checkIfMinister(String department){
        if(department == Constants.FOREIGN_AFFAIRS){
            return mCabinet.getForeignAffairsMinister() == null;
        }
        else if(department == Constants.TREASURY){
            return mCabinet.getTreasurer() == null;
        }
        else if(department == Constants.DEFENCE)
        {
           return mCabinet.getDefenceMinister() == null;
        }
        else if(department == Constants.EDUCATION){
            return mCabinet.getEducationMinister() == null;
        }
        else if(department == Constants.HEALTH){
            return mCabinet.getHealthMinister() == null;
        }
        return false;
    }

    public void addPolicy(Policy policy){
       String department = policy.getMinistry();
        if(department == Constants.FOREIGN_AFFAIRS){
            mCabinet.getForeignAffairsMinister().addPolicy(policy);
        }
        else if(department == Constants.TREASURY){
            mCabinet.getTreasurer().addPolicy(policy);
        }
        else if(department == Constants.DEFENCE)
        {
            mCabinet.getDefenceMinister().addPolicy(policy);
        }
        else if(department == Constants.EDUCATION){
            mCabinet.getEducationMinister().addPolicy(policy);
        }
        else if(department == Constants.HEALTH){
            mCabinet.getHealthMinister().addPolicy(policy);
        }
    }

    public void addIssue(Issue issue){
        mIssues.add(issue);
    }

    public LinkedList<Issue> getIssues() {
        return mIssues;
    }

    public Issue getIssue(int position){
        return mIssues.get(position);
    }

    public void resolveIssue(int position, int optionPosition){
        mIssues.get(position).selectOption(optionPosition);
    }
}