package geopolitique.id11699156.com.geopolitique;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by yiannischambers on 19/05/2016.
 */
public class Model {

    static Country country;
    public static LinkedList<Policy> policies;
    public static LinkedList<Minister> ministers;


    public static void setUp(Leader leader){
        country = new Country(leader, "Australia", "Monarchy", 23000000, 1.4f);
        policies = new LinkedList<>();
        ministers = new LinkedList<>();
        setUpMinisters();
        setUpPolicy();
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
        country.getGovernment().getCabinet().setEducationMinister(minister3);
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
        Policy p2 = new Policy("Defence spending", "Money put towards the nation's defence forces", effects, 2, 2500000000f, Constants.DEFENCE);
        country.getGovernment().getCabinet().getDefenceMinister().addPolicy(p2);
    }
    public static LinkedList<Policy> getPolicies() {
        return policies;
    }

}
