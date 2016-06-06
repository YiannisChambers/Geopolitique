/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import java.util.LinkedList;

import data.CabinetRepo;
import data.GovernmentRepo;
import data.RealmHelper;
import geopolitique.id11699156.com.geopolitique.Backups;
import util.Constants;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Country class that stores the Government,
 * and the Economy for the Player's country.
 */
public class Country extends RealmObject {
    @PrimaryKey
    private long mID;

    private Leader mLeader;
    private Government mGovernment;
    private String mCountryName;

    private Economy mEconomy;

    private int mPopulation;

    private double mGrowthRate;

    private int mPictureID;

    public Country(){}

    /**
     * The full constructor for the Country class,
     * taking a Leader, Economy, and
     * all other country data.
     * @param leader
     * @param countryName
     * @param population
     * @param growthRate
     * @param economy
     * @param pictureID
     */
    public Country(Leader leader, String countryName,  int population, double growthRate, Economy economy, int pictureID) {
        mLeader = leader;
        mGovernment = GovernmentRepo.createNewGovernment(new Government(leader, CabinetRepo.createNewCabinet(new Cabinet())));
        mCountryName = countryName;
        mPopulation = population;
        mGrowthRate = growthRate;
        mEconomy = economy;
        RealmHelper.beginTransaction();
        mEconomy.calculateEconomy(this);
        RealmHelper.endTransaction();
        mPictureID = pictureID;
    }

    /**
     * Constructor for creating Countries without a Leader nor an Economy
     * @param countryName
     * @param population
     * @param growthRate
     * @param pictureID
     */
    public Country(String countryName, int population, double growthRate, int pictureID) {
        this(new Leader(), countryName, population, growthRate, new Economy(), pictureID);
    }

    /**
     * Constructor for creating Countries without a Leader
     * @param countryName
     * @param population
     * @param growthRate
     * @param economy
     * @param pictureID
     */
    public Country(String countryName, int population, double growthRate, Economy economy, int pictureID) {
        this(new Leader(), countryName,  population, growthRate, economy, pictureID);
    }


    /**
     * Gets the ID of the Country
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Gets the Country's name.
     * @return
     */
    public String getCountryName() {
        return mCountryName;
    }

    /**
     * Get the Country's current population
     * @return
     */
    public int getPopulation() {
        return mPopulation;
    }

    /**
     * Get the Country's Government
     * @return
     */
    public Government getGovernment() {
        return mGovernment;
    }


    /**
     * Get the Country's Economy.
     * @return
     */
    public Economy getEconomy() {
        return mEconomy;
    }

    /**
     * Increase the country's population by its growth rate on a daily basis.
     */
    private void increasePopulationInDay() {
        //Increase the population by its total population growth in a year, divided by 365
        mPopulation += (mPopulation * ((mGrowthRate / 100) / 365));
    }

    /**
     * Calculates and updates the Country's data for a day
     */
    public void updateDaily() {
        //Increase population
        increasePopulationInDay();

        //Calculate effects for all issues that the Government has resolved.
        updateIssueEffects();

        //Update all policy effects
        updatePolicyEffects();
    }

    /**
     * Calculates the effects for each Issue that the Government has resolved
     */
    void updateIssueEffects() {
        //Get all the issues of the Government
        RealmList<Issue> issues = mGovernment.getIssues();

        //For each one...
        for (int i = 0; i < issues.size(); i++) {
            //If the player has resolved them - selected an option...
            if (issues.get(i).isResolved()) {
                //If the issue hasn't already had their effects calculated...
                if (!issues.get(i).isFinished()) {
                    //For each effect...
                    RealmList<Effect> effects = issues.get(i).getSelectedOption().getEffects();
                    for (int j = 0; j < effects.size(); j++) {
                        //Calculate the effect on the Country
                        enactEffect(effects.get(j));
                    }
                    //Signify that the Issue has had its effects calculated
                    issues.get(i).finishIssue();
                }
            } else {
                //Increase the stale factor for an issue - the Governemnt loses popularity for not acting.
                updateProperty(Constants.POPULARITY, -0.05f * issues.get(i).getStaleFactor());
                issues.get(i).incrementStaleFactor();
            }
        }
    }

    /**
     * Updates the effects of each policy that the Government has enacted.
     */
    void updatePolicyEffects() {
        //Get all the policies that the Government has adopted
        LinkedList<Policy> policies = mGovernment.getCabinet().getTotalPolicies();

        //For each one...
        for (int i = 0; i < policies.size(); i++) {
            //If they've been fully enacted and are now government policy...
            if (policies.get(i).getTimeRemaining() == 0) {
                //For each one of the effects that the policy might have...
                RealmList<Effect> effects = policies.get(i).getEffects();
                for (int j = 0; j < effects.size(); j++) {
                    //Calculate the effect on the Country.
                    enactEffect(effects.get(j), policies.get(i));
                }
            } else {
                //Otherwise, decrement the time remaining until the policy is implemented
                policies.get(i).decrementTimeRemaining();
            }
        }

    }

    /**
     * Enact an effect from a Policy on the country
     * @param effect
     * @param policy
     */
    void enactEffect(Effect effect, Policy policy) {
        //Get the property to be affected
        String property = effect.getProperty();
        //Calculate the effect value
        double effectValue = effect.getEffect() / 2;//policy.getTimeToComplete();
        //Calculate the effect on the country
        updateProperty(property, effectValue);
    }

    /**
     * Enact an effect from an Issue
     * @param effect
     */
    void enactEffect(Effect effect) {
        //Get the property to be affected
        String property = effect.getProperty();
        //Calculate the effect value
        double effectValue = effect.getEffect();
        //Calculate the effect on the Country
        updateProperty(property, effectValue);
    }

    /**
     * Calculates and updates the impact of an effect on a property of the Country
     * @param property
     * @param effectValue
     */
    private void updateProperty(String property, double effectValue) {
        //Check which property the Effect impacts, and change the value
        //of that property by the amount of the effect
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

    /**
     * Calculates and updates the Country's data for a week
     */
    public void updateWeekly() {
        //Update the Government's popularity
        updatePopularity();
    }

    /**
     * Calculates and updates the Country's data for a month
     */
    public void updateMonthly() {
        //Calculate economic figures for a month
        mEconomy.calculateEconomy(this);
        //Add these new figures to the Economy backup for diagram purposes
        Backups.addEconomyBackup(mEconomy);

        //Update the Country's popularity
        updatePopularity();
        updateInternationalPopularity();
    }

    /**
     * Calculates the Country's Government's popularity
     */
    private void updatePopularity() {
        //Total value for popularity to change
        double value = 0;

        //For each 10 billion dollars that the Country is in debt, minus a percentage point.
        double deficitValue = (mEconomy.getDebt() / 100) / 100000000000f;
        //For each 10 thousand dollars that the average income is below $50,000, minus a percentage point
        double incomeValue = ((mEconomy.getAverageIncome()) - 50000) / 10000;

        //Calculate total value
        value = deficitValue + incomeValue;

        //Change the popularity of the Government
        mGovernment.changePopularity(value);

        //Add a poll backup
        Backups.addPollBackup(mGovernment.getPopularity());
    }

    private void updateInternationalPopularity() {
        //Total value for popularity to change
        double value = 0;

        //For each 10 billion dollars that the Country is in debt, minus a percentage point.
        double deficitValue = (mEconomy.getDebt() / 100) / 100000000000f;

        value = deficitValue;
        //Change the Government's international popularity
        mGovernment.changeInternationalPopularity(value);
    }

    /**
     * Get the leader of the country
     * @return
     */
    public Leader getLeader() {
        return mLeader;
    }

    /**
     * Set the leader of the Country, and form its Government
     * @param leader
     */
    public void setLeader(Leader leader){
        mLeader = leader;
        mGovernment = new Government(leader, new Cabinet());
    }

    /**
     * Set the Country's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }

    /**
     * Get the picture ID for a country's flag
     * @return
     */
    public int getPictureID() {
        return mPictureID;
    }
}
