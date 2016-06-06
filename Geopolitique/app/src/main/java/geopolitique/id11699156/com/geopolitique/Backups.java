/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package geopolitique.id11699156.com.geopolitique;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Economy;

/**
 * Class to store the backups for use in the Polls screen
 *
 * WARNING: HORRIBLE CODE AHEAD.
 * Static variables only facilitate easy data access;
 * ideally, this would be stored in the database
 */
public class Backups {

    private static LinkedList<Economy> economyBackups = new LinkedList<>();

    private static ArrayList pollBackups = new ArrayList();

    public static void addEconomyBackup(Economy economy){
        economyBackups.add(economy);
    }

    /**
     * Add a poll backup for use in the PollScreenFragments LineChart
     * @param popularity The popularity value to add
     */
    public static void addPollBackup(double popularity){
        pollBackups.add(popularity);
    }

    public static ArrayList getPollBackups(){
        return pollBackups;
    }

}
