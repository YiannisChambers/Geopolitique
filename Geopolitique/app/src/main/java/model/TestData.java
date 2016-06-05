package model;

import java.util.LinkedList;
import java.util.Random;

import data.CountryRepo;
import data.EconomyRepo;
import data.EffectRepo;
import data.ExistingCountryRepo;
import data.IssueRepo;
import data.LeaderRepo;
import data.MinisterRepo;
import data.OptionRepo;
import data.PolicyRepo;
import geopolitique.id11699156.com.geopolitique.R;
import io.realm.Realm;
import util.Constants;
import io.realm.RealmList;

/**
 * Class to set up all the dummy data that will already be in the database
 * when the program is launched for the first time.
 *
 * WARNING: HORRIBLE CODE AHEAD.
 * There will be many public static methods, and hardcoded strings
 * all in aid of facilitating easy database population.
 */
public class TestData {

    public static LinkedList<Policy> policies;
    public static LinkedList<Minister> ministers;
    public static LinkedList<Issue> issues;
    public static LinkedList<ExistingCountry> existingCountries;
    public static LinkedList<LinkedList<Option>> optionsList;

    /**
     * Create all test data for the first time
     */
    public static void setUpTestData(){
        TestData.setUpMinisters();
        TestData.setUpPolicy();
        TestData.setUpIssues();
        TestData.setUpExistingCountries();

        MinisterRepo.createNewMinisters(TestData.getMinisters());
        PolicyRepo.createNewPolicies(TestData.getPolicies());
        IssueRepo.createNewIssues(TestData.getIssues());
    }

    /**
     * Ensure that the Issues are initialised if the database has already been created.
     */
    public static void setUpTestDataWithExistingDatabase(){
        setUpIssues();
    }

    /**
     * Create a list of Minsters to populate the database with.
     */
    public static void setUpMinisters() {
        ministers = new LinkedList<>();
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

    public static LinkedList<Minister> getMinisters() {
        return ministers;
    }

    /**
     * Create a series of policies to populate the database with
     */
    public static void setUpPolicy() {

        //Create new linked list to store all policies
        policies = new LinkedList<>();

        //Create a list of effects and populate it
        RealmList<Effect> effects = new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, -5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, 1500)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, -5)));
        //Create a new Policy with those effects
        policies.add(new Policy("Negative Gearing", "Allows citizens to deduct rental losses from taxes", effects, 10, 1500000000f, Constants.TREASURY));

        //Rinse and repeat the same process to make another policy.
        effects= new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, 2)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, -500)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 2)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INTERNATIONAL_POPULARITY, 10)));

        Policy p = new Policy("Foreign Aid", "Financial grants sent abroad", effects, 5, 5000000000f, Constants.FOREIGN_AFFAIRS);
        p.setTimeToComplete(5);

        //And again.
        effects= new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, 5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, -1000)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INTERNATIONAL_POPULARITY, -1)));

        Policy p2 = new Policy("Defence Spending", "Money put towards the nation's defence forces", effects, 2, 2500000000f, Constants.DEFENCE);
        p2.setTimeToComplete(2);

        //Add completed Policies to policy list.
        policies.add(p);
        policies.add(p2);
    }

    public static LinkedList<Policy> getPolicies() {
        return policies;
    }


    public static void setUpIssues(){
        issues = new LinkedList<>();
        optionsList = new LinkedList<>();

        issues.add(createIssue("Scandal!", "An affair involving a junior minister and his secretary has been outed by the tabloids. The press seeks your comment.",
                "Defend your minister completely as a victim of unfounded and biased media abuse",
                "Distance yourself from the comments. Brush off the issue.",
                "Demand the resignation of your minister immediately."));

        issues.add(createIssue("Terrorist Attack!", "Late last night, four radicals from the far-right Sons of Anarchy stormed a movie theatre in the nation's south east, and took the patrons hostage. A tense hostage situation has developed, as the police forces desperately attempt to defuse the situation. \n The terrorists demand to speak with you over the phone, or else they will begin executing hostages. What will you do?",
                "Organise to speak with the terrorists. Attempt to negotiate the release of the hostages.",
                "Refuse to speak with the terrorists. Address the nation to decry the actions.",
                "Refuse to speak with the terrorists outright. Order the police to storm the theater."));

        issues.add(createIssue("Budget Day.", "The time has come for the government to release their latest update to the nation's economic direction. What policies will you make?",
                "Cut corporate tax by 5%; incentivise job creation through tax breaks to big business.",
                "Maintain current policy platform; cut around the edges of reform",
                "Increase social spending by cutting the defence budget. Raise taxes for big business."));

    }

    private static Issue createIssue(String title, String description, String positiveOption, String neutralOption, String negativeOption){

        RealmList<Effect> negativeEffect = new RealmList<Effect>();
        negativeEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, -5)));

        RealmList<Effect> positiveEffect = new RealmList<Effect>();
        positiveEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 5)));

        RealmList<Effect> neutralEffect = new RealmList<Effect>();
        negativeEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 0)));
        RealmList<Option> options = new RealmList<>();


        Option option1 = new Option(positiveOption, positiveEffect);
        Option option2 = new Option(neutralOption, neutralEffect);
        Option option3 = new Option(negativeOption, negativeEffect);

        LinkedList<Option> tempOptions = new LinkedList<>();
        tempOptions.add(option1);
        tempOptions.add(option2);
        tempOptions.add(option3);
        optionsList.add(tempOptions);

        options.add(OptionRepo.createNewOption(new Option(positiveOption, positiveEffect)));
        options.add(OptionRepo.createNewOption(new Option(neutralOption, neutralEffect)));
        options.add(OptionRepo.createNewOption(new Option(negativeOption, negativeEffect)));

        Issue issue = new Issue(title, description, options);

        return issue;
    }

    public static LinkedList<Issue> getIssues() {
        return issues;
    }

    public static void addRandomIssue(){
        if(new Random().nextBoolean()) {
            Realm.getDefaultInstance();
            Random r = new Random();
            int position = r.nextInt(issues.size());
            Issue original = issues.get(position);
            LinkedList<Option> options = optionsList.get(position);
            Issue issue = createIssue(original.getName(), original.getDescription(), options.get(0).getDescription(),
                    options.get(1).getDescription(), options.get(2).getDescription());

            IssueRepo.createNewIssue(issue);
        }
    }

    /**
     * Create Existing Countries to populate the database with
     * so the user can choose the country to start with
     */
    private static void setUpExistingCountries(){
        existingCountries = new LinkedList<>();

        //Create countries (adding entities to the database as it happens)
        Country australia = CountryRepo.createNewCountry(new Country(LeaderRepo.createNewLeader(new Leader("Malcolm", "Turnbull", "Prime Minister")), "Australia", 23000000, 1.4f, EconomyRepo.createNewEconomy(new Economy(40, 30, 5, 50000)), R.drawable.australia_flag));
        Country britain =  CountryRepo.createNewCountry(new Country(LeaderRepo.createNewLeader(new Leader("David", "Cameron", "Prime Minister")), "Britain", 64100000, 1.7f, EconomyRepo.createNewEconomy(new Economy(20, 45, 5, 50000)), R.drawable.britain_flag));
        Country russia =  CountryRepo.createNewCountry(new Country(LeaderRepo.createNewLeader(new Leader("Vladimir", "Putin", "President")), "Russia", 163000000, 1.2f, EconomyRepo.createNewEconomy(new Economy(30, 50, 4, 50000)), R.drawable.russia_flag));
        Country unitedStates =  CountryRepo.createNewCountry(new Country(LeaderRepo.createNewLeader(new Leader("Barack", "Obama", "President")),"United States", 300000000, 1.4f, EconomyRepo.createNewEconomy(new Economy(20, 30, 5, 50000)), R.drawable.us_flag));

        //Add to list
        existingCountries.add(ExistingCountryRepo.createExistingCountry(new ExistingCountry(australia)));
        existingCountries.add(ExistingCountryRepo.createExistingCountry(new ExistingCountry(britain)));
        existingCountries.add(ExistingCountryRepo.createExistingCountry(new ExistingCountry(russia)));
        existingCountries.add(ExistingCountryRepo.createExistingCountry(new ExistingCountry(unitedStates)));
    }
}
