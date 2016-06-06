/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Economy class for a Country that simulates
 * an economy's GDP, Tax Income and other stats.
 */
public class Economy extends RealmObject{

    @PrimaryKey
    private long mID;

    private double mGDP;
    private double mUnemploymentRate;

    private double mConsumption;
    private double mExports;
    private double mIncome;

    private double mDeficitSurplusFigure;
    private double mDebt;

    private double mIncomeTaxRate;
    private double mCompanyTaxRate;

    private double mTaxIncome;

    private double mAverageIncome;

    /**
     * Default constructor with default values
     */
    public Economy() {
        mIncomeTaxRate = 40;
        mCompanyTaxRate = 30;
        mUnemploymentRate = 10;
        mAverageIncome = 50000;
    }

    /**
     * Full constructor for an Economy
     * @param incomeTaxRate
     * @param companyTaxRate
     * @param unemploymentRate
     * @param averageIncome
     */
    public Economy(int incomeTaxRate, int companyTaxRate, int unemploymentRate, int averageIncome){
        mIncomeTaxRate = incomeTaxRate;
        mCompanyTaxRate = companyTaxRate;
        mUnemploymentRate = unemploymentRate;
        mAverageIncome = averageIncome;
    }

    /**
     * Get the Economy's ID
     * @return
     */
    public long getID() {
        return mID;
    }

    /**
     * Set the Economy's ID
     * @param mID
     */
    public void setID(long mID) {
        this.mID = mID;
    }

    /**
     * Calculate and update the values of the Economy for a month
     * for a particular Country.
     * @param country
     */
    public void calculateEconomy(Country country) {
        //Calculate the total personal income that an average person would make in the Country
        double totalPersonalIncome = calculateTotalPersonalIncome(country.getPopulation());

        //Calculate the average sales that a country would make internationally - less international popularity means less sales
        mExports = totalPersonalIncome * (country.getGovernment().getInternationalPopularity() / 100);

        //Total Income = Income + Exports
        double totalIncome = totalPersonalIncome + mExports;
        mIncome = totalIncome;

        //Calculate the taxes from the Country's personal income
        double taxes = calculateTaxes(totalIncome);
        mTaxIncome = taxes;

        //Consumption = the left over from everyone's income that people spend
        mConsumption = (totalIncome - taxes) + ((totalIncome - taxes) * (mCompanyTaxRate / 100));

        //GDP = Total Income + Total Tax Take + Consumption
        mGDP = mIncome + mTaxIncome + mConsumption;

        //Get the total government spending for a Country - the total cost of policies
        double governmentSpending = country.getGovernment().getGovernmentSpending();
        //Calculate the surplus or deficit
        mDeficitSurplusFigure = taxes - governmentSpending;
        //Increase or decrease the Country's debt
        mDebt += mDeficitSurplusFigure;
    }

    /**
     * Calculates the total income that every person in the country makes
     * @param countryPopulation
     * @return
     */
    private double calculateTotalPersonalIncome(int countryPopulation) {
        //Get the total population
        int population = countryPopulation;

        //Get the total working population - designated by the total population minus the unemployment rate
        int workingPopulation = (int) (population - (population * (mUnemploymentRate / 100)));

        //Assume that everyone's income is the Average Income, and calculate the total income
        return workingPopulation * mAverageIncome;
    }

    /**
     * Calculates the taxes that the country's people pay
     * @param totalIncome
     * @return
     */
    private double calculateTaxes(double totalIncome) {
        //Get the percentage of the income paid in taxes using the combined personal and company tax rates
        return totalIncome * ((mIncomeTaxRate + mCompanyTaxRate) / 100);
    }

    public double getDebt() {
        return mDebt;
    }

    public double getDeficitSurplusFigure() {
        return mDeficitSurplusFigure;
    }

    public double getGDP() {
        return mGDP;
    }

    public double getUnemploymentRate() {
        return mUnemploymentRate;
    }

    public double getAverageIncome() {
        return mAverageIncome;
    }

    public double getTaxIncome() {
        return mTaxIncome;
    }

    public double getConsumption() {
        return mConsumption;
    }

    public double getIncome() {
        return mIncome;
    }


    /**
     * Change the unemployment rate by a certain value
     * @param value
     */
    public void changeUnemploymentRate(double value) {
        //If the value makes the rate go below 0, clamp the rate at 0...
        if (mUnemploymentRate - value < 0) {
            mUnemploymentRate = 0;
        } else if (mUnemploymentRate + value > 100) {
            //...and if the value makes the rate go above 100%, clamp the rate at 100
            mUnemploymentRate = 100;
        } else {
            mUnemploymentRate += value;
        }
    }

    /**
     * Change the country's average income by a certain value
     * @param value
     */
    public void changeAverageIncome(double value) {
        //If the value makes income go below $0.00, clamp the rate at 0.
        if (mAverageIncome - value < 0) {
            mAverageIncome = 0;
        } else {
            mAverageIncome += value;
        }
    }

    /**
     * Change the country's average income tax rate by a certain value
     * @param value
     */
    public void changeIncomeTaxRate(double value) {
        //If the value makes the rate go below 0, clamp the rate at 0...
        if (mIncomeTaxRate - value < 0) {
            mIncomeTaxRate = 0;
        } else if (mIncomeTaxRate + value > 100) {
            mIncomeTaxRate = 100;
        } else {
            mIncomeTaxRate += value;
        }
    }
    /**
     * Change the country's average company tax rate by a certain value
     * @param value
     */
    public void changeCompanyTaxRate(double value) {
        //If the value makes the rate go below 0, clamp the rate at 0...
        if (mCompanyTaxRate - value < 0) {
            mCompanyTaxRate = 0;
        } else if (mCompanyTaxRate + value > 100) {
            //...and if the value makes the rate go above 100%, clamp the rate at 100
            mCompanyTaxRate = 100;
        } else {
            mCompanyTaxRate += value;
        }
    }
}
