package geopolitique.id11699156.com.geopolitique;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by yiannischambers on 19/05/2016.
 */
public class Model {

    Country country;

    public Model(Leader leader){
        country = new Country(leader, "Australia", "Monarchy", 23000000, 1.4f);
    }

    public Country getCountry() {
        return country;
    }

    //---oOo---//

    public class Economy{

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

        double mAverageIncome;

        public Economy()
        {
            mIncomeTaxRate = 40;
            mCompanyTaxRate = 30;
            mUnemploymentRate = 10;
            mAverageIncome = 50000;
        }

        public void calculateEconomy(Country country){

            double totalPersonalIncome = calculateTotalPersonalIncome(country.getPopulation());
            mExports =  totalPersonalIncome * (country.getGovernment().getInternationalPopularity() / 100);
            double totalIncome = totalPersonalIncome + mExports;

            double taxes = calculateTaxes(totalIncome);    //calculateIncomeTax(country.getPopulation()));
            mConsumption = (totalIncome - taxes) + ((totalIncome - taxes) * (mCompanyTaxRate/100));
            //mExports =  totalIncome * (country.getGovernment().getInternationalPopularity() / 100);

            mGDP = totalIncome + taxes + mConsumption;

            double governmentSpending = country.getGovernment().getGovernmentSpending();
            mDeficitSurplusFigure = taxes - governmentSpending;
            mDebt += mDeficitSurplusFigure;
        }

        private double calculateTotalPersonalIncome(int countryPopulation){
            int population = countryPopulation;

            int workingPopulation = (int) (population - (population * (mUnemploymentRate / 100)));

            return workingPopulation * mAverageIncome;
            //Assume that average income is 50K
        }


        private double calculateTaxes(double totalIncome)
        {
            return totalIncome * ( (mIncomeTaxRate + mCompanyTaxRate) / 100);
        }

        public double getDebt() {
            return mDebt;
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

        public void changeUnemploymentRate(double value){
            if(mUnemploymentRate - value < 0){
                mUnemploymentRate = 0;
            }
            else if(mUnemploymentRate + value > 100){
                mUnemploymentRate = 100;
            }
            else{
                mUnemploymentRate += value;
            }
        }

        public void changeAverageIncome(double value){
            if(mAverageIncome - value < 0){
                mAverageIncome = 0;
            }
            else{
                mAverageIncome += value;
            }
        }

        public void changeIncomeTaxRate(double value){
            if(mIncomeTaxRate - value < 0){
                mIncomeTaxRate = 0;
            }
            else if(mIncomeTaxRate + value > 100){
                mIncomeTaxRate = 100;
            }
            else{
                mIncomeTaxRate += value;
            }
        }

        public void changeCompanyTaxRate(double value){
            if(mCompanyTaxRate - value < 0){
                mCompanyTaxRate  = 0;
            }
            else if(mCompanyTaxRate  + value > 100){
                mCompanyTaxRate  = 100;
            }
            else{
                mCompanyTaxRate  += value;
            }
        }

        /*private double calculateIncomeTax(int countryPopulation) {
            double incomeTax = 0;

            int population = countryPopulation;

            int workingPopulation = (int) (population - (population * (mUnemploymentRate / 100)));

            int[] peoplePerTaxBracket = new int[mIncomeTaxBrackets.length];

            int peopleLeft = workingPopulation;
            double rateOfDivision = 0.3;
            for (int i = 0; i < peoplePerTaxBracket.length; i++) {

                if (i != peoplePerTaxBracket.length - 1) {
                    int people = (int) (peopleLeft * rateOfDivision);
                    rateOfDivision += 0.1f;
                    peoplePerTaxBracket[i] = people;
                    peopleLeft -= people;
                } else {
                    peoplePerTaxBracket[i] = peopleLeft;
                }
            }

            //Let's assume that 500 million is the maximum that someone can earn in the nation.

            double[] incomePerTaxBracket = new double[mIncomeTaxBrackets.length];

            double largestPossibleIncome = 500000000f;
            double income = 10000f;
            for (int i = 0; i < incomePerTaxBracket.length; i++) {
                if (i == 0) {
                    incomePerTaxBracket[i] = 0;
                }
                else{
                    incomePerTaxBracket[i] = income;
                    income *= 2;
                }
            }

            for(int i = 0; i < mIncomeTaxBrackets.length; i++){
                double averageIncome = 0;
                if(i != mIncomeTaxBrackets.length - 1){
                    averageIncome = (incomePerTaxBracket[i] + incomePerTaxBracket[i + 1])/2;

                }
                else{
                    averageIncome = (incomePerTaxBracket[i] + largestPossibleIncome)/2;
                }

                double totalIncome = averageIncome * peoplePerTaxBracket[i];
                double taxes = totalIncome * mIncomeTaxBrackets[i];
                incomeTax += taxes;
            }

            return incomeTax;
        }
        */

    }


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

        private void increasePopulationInDay(){
            mPopulation += (mPopulation * ((mGrowthRate/100) / 365));
        }

        public void updateDaily(){
            increasePopulationInDay();
            updatePolicyEffects();
        }

        void updatePolicyEffects(){
            LinkedList<Policy> policies = mGovernment.getCabinet().getTotalPolicies();

            for(int i = 0; i < policies.size(); i++){
                if(policies.get(i).getTimeRemaining() != 0){
                    LinkedList<Effect> effects = policies.get(i).getEffect();
                    for(int j = 0; j < effects.size(); j++){
                        enactEffect(effects.get(i), policies.get(i));
                    }
                }
            }

        }

        void enactEffect(Effect effect, Policy policy){
            String property = effect.mProperty;
            int effectValue = effect.getEffect() / policy.getTimeToComplete();
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
        }

        public void updateMonthly(){
            mEconomy.calculateEconomy(this);
            updatePopularity();
            updateInternationalPopularity();
        }

        private void updatePopularity(){
            double value = 0;

            //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
            //This would update at the time; don't put here.

            double deficitValue = (mEconomy.mDebt / 2) / 1000000000;
            double incomeValue = ((mEconomy.getAverageIncome()) - 50000) / 10000;

            value = deficitValue + incomeValue;
            mGovernment.changePopularity(value);
        }

        private void updateInternationalPopularity(){
            double value = 0;

            //SCANDALS: Each Minister has (if randomOutOf100 > minister.Experience) chance of causing a scandal
            //This would update at the time; don't put here.

            double deficitValue = (mEconomy.mDebt / 2) / 100000000;

            value = deficitValue;
            mGovernment.changeInternationalPopularity(value);
        }
    }

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
    }

    public class Leader {
        String mFirstName;
        String mLastName;
        String mTitle;

        public Leader(){
            mFirstName = "John";
            mLastName = "Smith";
            mTitle = "Prime Minister";
        }

        public Leader(String first, String last, String title) {
            mFirstName = first;
            mLastName = last;
            mTitle = title;
        }

        public String getFullName() {
            return mFirstName + " " + mLastName;
        }

        public String getFirstName() {
            return mFirstName;
        }

        public String getLastName() {
            return mLastName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getFullNameWithTitle() {
            return mTitle + " " + mFirstName + " " + mLastName;
        }

        public String getShortNameWithTitle() {
            return mTitle + " " + mLastName;
        }
    }

    public class Cabinet {
        Minister mForeignAffairsMinister;
        Minister mTreasurer;
        Minister mEducationMinister;
        Minister mDefenceMinister;
        Minister mHealthMinister;

        public Cabinet() {
        }

        public Minister getDefenceMinister() {
            return mDefenceMinister;
        }

        public Minister getEducationMinister() {
            return mEducationMinister;
        }

        public Minister getForeignAffairsMinister() {
            return mForeignAffairsMinister;
        }

        public Minister getHealthMinister() {
            return mHealthMinister;
        }

        public Minister getTreasurer() {
            return mTreasurer;
        }

        public void setDefenceMinister(Minister mDefenceMinister) {
            this.mDefenceMinister = mDefenceMinister;
        }

        public void setEducationMinister(Minister mEducationMinister) {
            this.mEducationMinister = mEducationMinister;
        }

        public void setForeignAffairsMinister(Minister mForeignAffairsMinister) {
            this.mForeignAffairsMinister = mForeignAffairsMinister;
        }

        public void setHealthMinister(Minister mHealthMinister) {
            this.mHealthMinister = mHealthMinister;
        }

        public void setTreasurer(Minister mTreasurer) {
            this.mTreasurer = mTreasurer;
        }

        public int getTotalWorkload() {
            int totalWorkload = 0;

            totalWorkload += mForeignAffairsMinister == null ? mForeignAffairsMinister.getWorkload() : 0;
            totalWorkload += mEducationMinister == null ? mEducationMinister.getWorkload() : 0;
            totalWorkload += mDefenceMinister == null ? mDefenceMinister.getWorkload() : 0;
            totalWorkload += mHealthMinister == null ? mHealthMinister.getWorkload() : 0;
            totalWorkload += mTreasurer == null ? mTreasurer.getWorkload() : 0;

            return totalWorkload;
        }

        public int getTotalCurrentWorkload() {
            int totalCurrentWorkload = 0;

            totalCurrentWorkload += mForeignAffairsMinister == null ? mForeignAffairsMinister.getCurrentWorkload() : 0;
            totalCurrentWorkload += mEducationMinister == null ? mEducationMinister.getCurrentWorkload() : 0;
            totalCurrentWorkload += mDefenceMinister == null ? mDefenceMinister.getCurrentWorkload() : 0;
            totalCurrentWorkload += mHealthMinister == null ? mHealthMinister.getCurrentWorkload() : 0;
            totalCurrentWorkload += mTreasurer == null ? mTreasurer.getCurrentWorkload() : 0;

            return totalCurrentWorkload;
        }

        public double getTotalPolicyCosts(){
            double totalPolicyCosts = 0;

            totalPolicyCosts += mForeignAffairsMinister == null ? mForeignAffairsMinister.getTotalPolicyCosts() : 0;
            totalPolicyCosts += mEducationMinister == null ? mEducationMinister.getTotalPolicyCosts() : 0;
            totalPolicyCosts += mDefenceMinister == null ? mDefenceMinister.getTotalPolicyCosts() : 0;
            totalPolicyCosts += mHealthMinister == null ? mHealthMinister.getTotalPolicyCosts() : 0;
            totalPolicyCosts += mTreasurer == null ? mTreasurer.getTotalPolicyCosts() : 0;

            return totalPolicyCosts;
        }

        public LinkedList<Policy> getTotalPolicies(){
            LinkedList<Policy> policies = new LinkedList<>();// totalPolicyCosts = 0;

            if(mForeignAffairsMinister != null)
            {
                policies.addAll(mForeignAffairsMinister.getPolicies());
            }
            if(mEducationMinister != null)
            {
                policies.addAll(mEducationMinister.getPolicies());
            }
            if(mHealthMinister != null)
            {
                policies.addAll(mHealthMinister.getPolicies());
            }
            if(mTreasurer != null)
            {
                policies.addAll(mTreasurer.getPolicies());
            }
            if(mDefenceMinister != null)
            {
                policies.addAll(mDefenceMinister.getPolicies());
            }
            return policies;
        }


    }

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
                policy.setTimeToComplete((policy.getSize() / mExperience) * 2);
            }
            else {
                policy.setTimeToComplete(policy.getSize() / mExperience);
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

    public class Effect {

        String mProperty;
        int mEffect;

        public Effect(String countryProperty, int effect) {
            mProperty = countryProperty;
            mEffect = effect;
        }

        public String getProperty() {
            return mProperty;
        }

        public int getEffect() {
            return mEffect;
        }
    }

    public class ExistingPolicies{

        public LinkedList<Policy> policies;

        public ExistingPolicies(){
            LinkedList<Effect> effects = new LinkedList<Effect>();
            effects.add(new Effect(Constants.INCOME_TAX_RATE, -5));
            effects.add(new Effect(Constants.AVERAGE_INCOME, 1500));
            effects.add(new Effect(Constants.POPULARITY, -5));
            policies.add(new Policy("Negative Gearing", "Allows citizens to deduct rental losses from taxes", effects, 10, 1500000000f, Constants.TREASURY));

            effects.clear();
            effects.add(new Effect(Constants.INCOME_TAX_RATE, 2));
            effects.add(new Effect(Constants.AVERAGE_INCOME, -500));
            effects.add(new Effect(Constants.POPULARITY, 2));
            effects.add(new Effect(Constants.INTERNATIONAL_POPULARITY, 10));
            policies.add(new Policy("Foreign Aid", "Financial grants sent abroad", effects, 5, 5000000000f, Constants.FOREIGN_AFFAIRS));

            effects.clear();
            effects.add(new Effect(Constants.INCOME_TAX_RATE, 5));
            effects.add(new Effect(Constants.AVERAGE_INCOME, -1000));
            effects.add(new Effect(Constants.POPULARITY, 5));
            effects.add(new Effect(Constants.INTERNATIONAL_POPULARITY, -1));
            policies.add(new Policy("Defence spending", "Money put towards the nation's defence forces", effects, 2, 2500000000f, Constants.DEFENCE));


        }

        public LinkedList<Policy> getPolicies() {
            return policies;
        }
    }

    public class ExistingMinisters{
        public LinkedList<Minister> ministers;

        public ExistingMinisters(){
            ministers.add(new Minister("Jane", "Carruthers", 5, 10));
            ministers.add(new Minister("Derek", "McCormack", 1, 3));
            ministers.add(new Minister("Steven", "St James", 6, 7));
            ministers.add(new Minister("Angela", "Mountbatten", 3, 8));
            ministers.add(new Minister("Frederick", "Guilder", 1, 1));
            ministers.add(new Minister("John", "Douglas", 5, 5));
            ministers.add(new Minister("Fred", "Turpentine", 10, 10));
            ministers.add(new Minister("Jonathon", "Chambers", 5, 5));
            ministers.add(new Minister("Josephina", "de Santa Maria", 3, 4));
            ministers.add(new Minister("Julia", "Urquhart", 10, 10));
            ministers.add(new Minister("Sophie", "de Arbanville", 6, 7));
            ministers.add(new Minister("Genevieve", "d'Arc", 7, 6));
        }

        public LinkedList<Minister> getMinisters() {
            return ministers;
        }
    }


}
