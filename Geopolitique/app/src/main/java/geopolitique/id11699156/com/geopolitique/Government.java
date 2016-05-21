package geopolitique.id11699156.com.geopolitique;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Government {
    Leader mLeader;
    Cabinet mCabinet;

    double mPopularity;
    double mInternationalPopularity;

    double mScandalCount;

    public Government(){
        mLeader = new Leader();
        mCabinet = new Cabinet();
        mPopularity = 51.00f;
        mInternationalPopularity = 51.00f;
    }

    public Government(Leader leader) {
        mLeader = leader;
        mCabinet = new Cabinet();
        mPopularity = 51.00f;
        mInternationalPopularity = 51.00f;
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
}