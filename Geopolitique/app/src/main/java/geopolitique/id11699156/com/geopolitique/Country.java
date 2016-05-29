package geopolitique.id11699156.com.geopolitique;

import android.view.MotionEvent;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Country {
    Government mGovernment;
    String mCountryName;
    String mCountryType;

    Economy mEconomy;

    int mPopulation;
    double mGrowthRate;

    public Country(Leader leader, String countryName, String countryType, int population, double growthRate) {
        mGovernment = new Government(leader);
        mCountryName = countryName;
        mCountryType = countryType;
        mPopulation = population;
        mGrowthRate = growthRate;
        mEconomy = new Economy();
        mEconomy.calculateEconomy(this);
    }

    public String getCountryName() {
        return mCountryName;
    }

    public String getCountryType() {
        return mCountryType;
    }

    public int getPopulation() {
        return mPopulation;
    }

    public Government getGovernment() {
        return mGovernment;
    }


    public Economy getEconomy() {
        return mEconomy;
    }

    private void increasePopulationInDay(){
        mPopulation += (mPopulation * ((mGrowthRate/100) / 365));
    }

    public void updateDaily(){
        increasePopulationInDay();
        updateIssueEffects();
    }




    void updateIssueEffects(){
        LinkedList<Issue> issues = mGovernment.getIssues();

        for(int i = 0; i < issues.size(); i++){
            if(issues.get(i).isResolved()){
                if(!issues.get(i).isFinished()) {
                    LinkedList<Effect> effects = issues.get(i).getSelectedOption().getEffects();
                    for (int j = 0; j < effects.size(); j++) {
                        enactEffect(effects.get(i));
                    }
                }
            }
            else
            {
                updateProperty(Constants.POPULARITY, -0.05f * issues.get(i).getStaleFactor());
                issues.get(i).incrementStaleFactor();
            }
        }

    }


    void updatePolicyEffects(){
        LinkedList<Policy> policies = mGovernment.getCabinet().getTotalPolicies();

        for(int i = 0; i < policies.size(); i++){
            if(policies.get(i).getTimeRemaining() == 0){
                LinkedList<Effect> effects = policies.get(i).getEffect();
                for(int j = 0; j < effects.size(); j++){
                    enactEffect(effects.get(i), policies.get(i));
                }
            }
            else
            {
                policies.get(i).decrementTimeRemaining();
            }
        }

    }

    void enactEffect(Effect effect, Policy policy){
        String property = effect.mProperty;
        double effectValue = effect.getEffect() / policy.getTimeToComplete();
        updateProperty(property, effectValue);
    }

    void enactEffect(Effect effect){
        String property = effect.mProperty;
        double effectValue = effect.getEffect();
        updateProperty(property, effectValue);
    }

    private void updateProperty(String property, double effectValue){
        if(property == Constants.AVERAGE_INCOME)
        {
            mEconomy.changeAverageIncome(effectValue);
        }
        else if(property == Constants.COMPANY_TAX_RATE)
        {
            mEconomy.changeCompanyTaxRate(effectValue);
        }
        else if(property == Constants.INCOME_TAX_RATE)
        {
            mEconomy.changeIncomeTaxRate(effectValue);
        }
        else if(property == Constants.POPULARITY){
            mGovernment.changePopularity(effectValue);
        }
        else if(property == Constants.INTERNATIONAL_POPULARITY)
        {
            mGovernment.changeInternationalPopularity(effectValue);
        }
        else if(property == Constants.UNEMPLOYMENT)
        {
            mEconomy.changeUnemploymentRate(effectValue);
        }
    }

    public void updateWeekly(){

        updatePopularity();
        updatePolicyEffects();
    }

    public void updateMonthly(){
        mEconomy.calculateEconomy(this);
        Backups.addEconomyBackup(mEconomy);
        updatePopularity();
        updateInternationalPopularity();

    }

    private void updatePopularity(){
        double value = 0;

        //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
        //This would update at the time; don't put here.

        double deficitValue = (mEconomy.mDebt / 100) / 100000000000f;
        double incomeValue = ((mEconomy.getAverageIncome()) - 50000) / 10000;

        value = deficitValue + incomeValue;
        mGovernment.changePopularity(value);

        Backups.addPollBackup(mGovernment.getPopularity());
    }

    private void updateInternationalPopularity(){
        double value = 0;

        //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
        //This would update at the time; don't put here.

        double deficitValue = (mEconomy.mDebt / 2) / 100000000000f;

        value = deficitValue;
        mGovernment.changeInternationalPopularity(value);
    }
}
