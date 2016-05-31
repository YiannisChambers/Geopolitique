package model;

import java.util.LinkedList;

import geopolitique.id11699156.com.geopolitique.Constants;

/**
 * Created by yiannischambers on 19/05/2016.
 */
public class Model {

    static Country country;
    public static LinkedList<Policy> policies;
    public static LinkedList<Minister> ministers;
    public static LinkedList<Issue> issues;


    public static void setUp(Leader leader){
        country = new Country(leader, "Australia", "Monarchy", 23000000, 1.4f);
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
        Minister minister = new Minister("Malcolm Bligh", "Turnbull", 4, 5);
        Minister minister2 = new Minister("William Richard", "Shorten", 3, 4);
        Minister minister3 = new Minister("Kevin Michael", "Rudd", 6, 5);
        Minister minister4 = new Minister("Edward Gough", "Whitlam", 9, 9);
        Minister minister5 = new Minister("Robert James Lee", "Hawke", 7, 7);
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

        country.getGovernment().getCabinet().setDefenceMinister(minister);
        country.getGovernment().getCabinet().setHealthMinister(minister2);
        //country.getGovernment().getCabinet().setEducationMinister(minister3);
        country.getGovernment().getCabinet().setForeignAffairsMinister(minister4);
        //country.getGovernment().getCabinet().setTreasurer(minister5);

    }

    public static LinkedList<Minister> getMinisters() {
        return ministers;
    }


    public static void setUpPolicy() {
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

        Policy p = new Policy("Foreign Aid", "Financial grants sent abroad", effects, 5, 5000000000f, Constants.FOREIGN_AFFAIRS);
        country.getGovernment().getCabinet().getForeignAffairsMinister().addPolicy(p);

        effects.clear();
        effects.add(new Effect(Constants.INCOME_TAX_RATE, 5));
        effects.add(new Effect(Constants.AVERAGE_INCOME, -1000));
        effects.add(new Effect(Constants.POPULARITY, 5));
        effects.add(new Effect(Constants.INTERNATIONAL_POPULARITY, -1));
        Policy p2 = new Policy("Defence Spending", "Money put towards the nation's defence forces", effects, 2, 2500000000f, Constants.DEFENCE);
        country.getGovernment().getCabinet().getDefenceMinister().addPolicy(p2);

        policies.add(p);
        policies.add(p2);
    }

    public static LinkedList<Policy> getPolicies() {
        return policies;
    }

    public static LinkedList<Policy> getUnadoptedPolicies() {
        LinkedList<Policy> unAdoptedPolicies = new LinkedList<>();
        for(int i= 0; i < policies.size(); i++){
            if(!Model.getCountry().getGovernment().getCabinet().getTotalPolicies().contains(policies.get(i))){
                unAdoptedPolicies.add(policies.get(i));
            }
        }
        return unAdoptedPolicies;
    }



    public static void setUpIssues(){
        LinkedList<Option> options = new LinkedList<>();


        LinkedList<Effect> negativeEffect = new LinkedList<Effect>();
        negativeEffect.add(new Effect(Constants.POPULARITY, -5));

        LinkedList<Effect> positiveEffect = new LinkedList<Effect>();
        positiveEffect.add(new Effect(Constants.POPULARITY, 5));

        LinkedList<Effect> neutralEffect = new LinkedList<Effect>();
        negativeEffect.add(new Effect(Constants.POPULARITY, 0));

        options.add(new Option("Defend your minister completely as a victim of unfounded and biased media abuse.", negativeEffect));
        options.add(new Option("Distance yourself from the comments. Brush off the issue.", neutralEffect));
        options.add(new Option("Demand the resignation of your minister immediately.", positiveEffect));
        Issue issue = new Issue("Scandal!", "An affair involving a junior minister and his secretary has been outed by the tabloids. The press seeks your comment.", options);

        issues.add(issue);
    }

    public static LinkedList<Issue> getIssues() {
        LinkedList<Issue> unresolvedIssues = new LinkedList<>();
        for(int i = 0; i < issues.size(); i++){
            if(!(issues.get(i).isResolved())) {
                unresolvedIssues.add(issues.get(i));
            }
        }
        return unresolvedIssues;
    }
}
