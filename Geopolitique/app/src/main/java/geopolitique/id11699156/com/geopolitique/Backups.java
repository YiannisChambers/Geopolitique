package geopolitique.id11699156.com.geopolitique;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Economy;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Backups {

    private static LinkedList<Economy> economyBackups = new LinkedList<>();

    private static ArrayList pollBackups = new ArrayList();

    public static void addEconomyBackup(Economy economy){
        economyBackups.add(economy);
    }

    public static LinkedList<Economy> getEconomyBackups(){
        return economyBackups;
    }

    public static void addPollBackup(double popularity){
        pollBackups.add(popularity);
    }

    public static ArrayList getPollBackups(){
        return pollBackups;
    }

}
