package model;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Economy {

    double mGDP;
    double mUnemploymentRate;

    double mConsumption;
    //double mInvestment;
    double mExports;
    double mIncome;

    double mDeficitSurplusFigure;
    double mDebt;
    //double[] mIncomeTaxBrackets;  //This holds the cents paid on the dollar.

    double mIncomeTaxRate;
    double mCompanyTaxRate;

    double mTaxIncome;

    double mAverageIncome;

    public Economy() {
        mIncomeTaxRate = 40;
        mCompanyTaxRate = 30;
        mUnemploymentRate = 10;
        mAverageIncome = 50000;
    }

    public void calculateEconomy(Country country) {

        double totalPersonalIncome = calculateTotalPersonalIncome(country.getPopulation());
        mExports = totalPersonalIncome * (country.getGovernment().getInternationalPopularity() / 100);
        double totalIncome = totalPersonalIncome + mExports;

        double taxes = calculateTaxes(totalIncome);
        mTaxIncome = taxes;
        mConsumption = (totalIncome - taxes) + ((totalIncome - taxes) * (mCompanyTaxRate / 100));
        mIncome = totalIncome;

        //GDP Calculation
        mGDP = mIncome + mTaxIncome + mConsumption;

        double governmentSpending = country.getGovernment().getGovernmentSpending();
        mDeficitSurplusFigure = taxes - governmentSpending;
        mDebt += mDeficitSurplusFigure;
    }

    private double calculateTotalPersonalIncome(int countryPopulation) {
        int population = countryPopulation;

        int workingPopulation = (int) (population - (population * (mUnemploymentRate / 100)));

        //Assume that average income is 50K
        return workingPopulation * mAverageIncome;
    }


    private double calculateTaxes(double totalIncome) {
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

    public void changeUnemploymentRate(double value) {
        if (mUnemploymentRate - value < 0) {
            mUnemploymentRate = 0;
        } else if (mUnemploymentRate + value > 100) {
            mUnemploymentRate = 100;
        } else {
            mUnemploymentRate += value;
        }
    }

    public void changeAverageIncome(double value) {
        if (mAverageIncome - value < 0) {
            mAverageIncome = 0;
        } else {
            mAverageIncome += value;
        }
    }

    public void changeIncomeTaxRate(double value) {
        if (mIncomeTaxRate - value < 0) {
            mIncomeTaxRate = 0;
        } else if (mIncomeTaxRate + value > 100) {
            mIncomeTaxRate = 100;
        } else {
            mIncomeTaxRate += value;
        }
    }

    public void changeCompanyTaxRate(double value) {
        if (mCompanyTaxRate - value < 0) {
            mCompanyTaxRate = 0;
        } else if (mCompanyTaxRate + value > 100) {
            mCompanyTaxRate = 100;
        } else {
            mCompanyTaxRate += value;
        }
    }
}
