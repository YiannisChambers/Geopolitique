package model;

import java.util.LinkedList;

import data.EffectRepo;
import data.OptionRepo;
import util.Constants;
import io.realm.RealmList;

/**
 * Created by yiannischambers on 19/05/2016.
 */
public class Model {

    static Country country;
    public static LinkedList<Policy> policies;
    public static LinkedList<Minister> ministers;
    public static LinkedList<Issue> issues;

    public static void setUp(Leader leader){
        country = new Country("Australia", "Monarchy", 23000000, 1.4f);
        country.setLeader(leader);

        policies = new LinkedList<>();
        ministers = new LinkedList<>();
        issues = new LinkedList<>();
        setUpMinisters();
        setUpPolicy();
        setUpIssues();
    }

    public static Country getCountry() {
        return country;
    }


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

        //country.getGovernment().getCabinet().setDefenceMinister(minister);
        //country.getGovernment().getCabinet().setHealthMinister(minister2);
        //country.getGovernment().getCabinet().setEducationMinister(minister3);
        //country.getGovernment().getCabinet().setForeignAffairsMinister(minister4);
        //country.getGovernment().getCabinet().setTreasurer(minister5);

    }

    public static LinkedList<Minister> getMinisters() {
        return ministers;
    }


    public static void setUpPolicy() {

        policies = new LinkedList<>();
        RealmList<Effect> effects = new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, -5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, 1500)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, -5)));

        policies.add(new Policy("Negative Gearing", "Allows citizens to deduct rental losses from taxes", effects, 10, 1500000000f, Constants.TREASURY));

        effects= new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, 2)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, -500)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 2)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INTERNATIONAL_POPULARITY, 10)));

        Policy p = new Policy("Foreign Aid", "Financial grants sent abroad", effects, 5, 5000000000f, Constants.FOREIGN_AFFAIRS);
        //country.getGovernment().getCabinet().getForeignAffairsMinister().addPolicy(p);

        effects= new RealmList<Effect>();
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INCOME_TAX_RATE, 5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.AVERAGE_INCOME, -1000)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 5)));
        effects.add(EffectRepo.createNewEffect(new Effect(Constants.INTERNATIONAL_POPULARITY, -1)));

        Policy p2 = new Policy("Defence Spending", "Money put towards the nation's defence forces", effects, 2, 2500000000f, Constants.DEFENCE);
        //country.getGovernment().getCabinet().getDefenceMinister().addPolicy(p2);

        policies.add(p);
        policies.add(p2);
    }

    public static LinkedList<Policy> getPolicies() {
        return policies;
    }


    public static void setUpIssues(){
        issues = new LinkedList<>();
        RealmList<Option> options = new RealmList<>();

        RealmList<Effect> negativeEffect = new RealmList<Effect>();
        negativeEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, -5)));

        RealmList<Effect> positiveEffect = new RealmList<Effect>();
        positiveEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 5)));

        RealmList<Effect> neutralEffect = new RealmList<Effect>();
        negativeEffect.add(EffectRepo.createNewEffect(new Effect(Constants.POPULARITY, 0)));

        options.add(OptionRepo.createNewOption(new Option("Defend your minister completely as a victim of unfounded and biased media abuse.", negativeEffect)));
        options.add(OptionRepo.createNewOption(new Option("Distance yourself from the comments. Brush off the issue.", neutralEffect)));
        options.add(OptionRepo.createNewOption(new Option("Demand the resignation of your minister immediately.", positiveEffect)));


        Issue issue = new Issue("Scandal!", "An affair involving a junior minister and his secretary has been outed by the tabloids. The press seeks your comment.", options);

        issues.add(issue);
    }

    public static LinkedList<Issue> getIssues() {
        return issues;
    }

}
