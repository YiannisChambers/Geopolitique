package model;

import java.util.LinkedList;

import data.CountryRepo;
import data.RealmHelper;
import geopolitique.id11699156.com.geopolitique.Backups;
import util.Constants;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Country extends RealmObject {

    @PrimaryKey
    long mID;

    Leader mLeader;
    Government mGovernment;
    String mCountryName;
    String mCountryType;

    Economy mEconomy;

    int mPopulation;
    double mGrowthRate;

    public Country(){}

    public Country(Leader leader, String countryName, String countryType, int population, double growthRate, Economy economy) {
        mLeader = leader;
        mGovernment = new Government(leader);
        mCountryName = countryName;
        mCountryType = countryType;
        mPopulation = population;
        mGrowthRate = growthRate;
        mEconomy = economy;
        mEconomy.calculateEconomy(this);
    }

    public Country(String countryName, String countryType, int population, double growthRate) {
        this(new Leader(), countryName, countryType, population, growthRate, new Economy());
    }

    public Country(String countryName, String countryType, int population, double growthRate, Economy economy) {
        this(new Leader(), countryName, countryType, population, growthRate, economy);
    }


    public long getID() {
        return mID;
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

    private void increasePopulationInDay() {
        mPopulation += (mPopulation * ((mGrowthRate / 100) / 365));
    }

    public void updateDaily() {
        increasePopulationInDay();
        updateIssueEffects();
    }


    void updateIssueEffects() {
        RealmList<Issue> issues = mGovernment.getIssues();

        for (int i = 0; i < issues.size(); i++) {
            if (issues.get(i).isResolved()) {
                if (!issues.get(i).isFinished()) {
                    RealmList<Effect> effects = issues.get(i).getSelectedOption().getEffects();
                    for (int j = 0; j < effects.size(); j++) {
                        enactEffect(effects.get(j));
                    }
                    issues.get(i).finishIssue();
                }
            } else {
                updateProperty(Constants.POPULARITY, -0.05f * issues.get(i).getStaleFactor());
                issues.get(i).incrementStaleFactor();
            }
        }

    }


    void updatePolicyEffects() {
        LinkedList<Policy> policies = mGovernment.getCabinet().getTotalPolicies();

        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).getTimeRemaining() == 0) {
                RealmList<Effect> effects = policies.get(i).getEffects();
                for (int j = 0; j < effects.size(); j++) {
                    enactEffect(effects.get(i), policies.get(i));
                }
            } else {
                policies.get(i).decrementTimeRemaining();
            }
        }

    }

    void enactEffect(Effect effect, Policy policy) {
        String property = effect.mProperty;
        double effectValue = effect.getEffect() / policy.getTimeToComplete();
        updateProperty(property, effectValue);
    }

    void enactEffect(Effect effect) {
        String property = effect.mProperty;
        double effectValue = effect.getEffect();
        updateProperty(property, effectValue);
    }

    private void updateProperty(String property, double effectValue) {
        if (property == Constants.AVERAGE_INCOME) {
            mEconomy.changeAverageIncome(effectValue);
        } else if (property.equals(Constants.COMPANY_TAX_RATE)) {
            mEconomy.changeCompanyTaxRate(effectValue);
        } else if (property.equals( Constants.INCOME_TAX_RATE)) {
            mEconomy.changeIncomeTaxRate(effectValue);
        } else if (property.equals(Constants.POPULARITY)) {
            mGovernment.changePopularity(effectValue);
        } else if (property.equals( Constants.INTERNATIONAL_POPULARITY)) {
            mGovernment.changeInternationalPopularity(effectValue);
        } else if (property.equals(Constants.UNEMPLOYMENT)) {
            mEconomy.changeUnemploymentRate(effectValue);
        }
    }

    public void updateWeekly() {
        updatePopularity();
        updatePolicyEffects();
    }

    public void updateMonthly() {
        mEconomy.calculateEconomy(this);
        Backups.addEconomyBackup(mEconomy);
        updatePopularity();
        updateInternationalPopularity();
    }

    private void updatePopularity() {
        double value = 0;

        //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
        //This would update at the time; don't put here.

        double deficitValue = (mEconomy.mDebt / 100) / 100000000000f;
        double incomeValue = ((mEconomy.getAverageIncome()) - 50000) / 10000;

        value = deficitValue + incomeValue;
        mGovernment.changePopularity(value);

        Backups.addPollBackup(mGovernment.getPopularity());
    }

    private void updateInternationalPopularity() {
        double value = 0;

        //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
        //This would update at the time; don't put here.

        double deficitValue = (mEconomy.mDebt / 2) / 100000000000f;

        value = deficitValue;
        mGovernment.changeInternationalPopularity(value);
    }

    public Leader getLeader() {
        return mLeader;
    }

    public void setLeader(Leader leader){
        mLeader = leader;
        mGovernment = new Government(leader);
    }

    public void setID(long mID) {
        this.mID = mID;
    }
}
