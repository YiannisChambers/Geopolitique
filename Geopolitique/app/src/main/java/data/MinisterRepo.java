/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */


package data;

import java.util.LinkedList;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Minister;

/**
 * Repository Class for Minister Realm Object
 */
public class MinisterRepo {
    /**
     * Creates and adds a new Minister object to the Database.
     *
     * @param minister The Minister object to add to the database.
     * @return
     */
    public static Minister createNewMinister(Minister minister){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        minister.setID(getNextKey());
        Minister createdMinister = realm.copyToRealm(minister);
        realm.commitTransaction();

        return createdMinister;
    }

    /**
     * Create multiple Ministers from a Linked List
     * @param ministers
     */
    public static void createNewMinisters(LinkedList<Minister> ministers){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i = 0; i < ministers.size(); i++){
            ministers.get(i).setID(getNextKey());
            realm.copyToRealm(ministers.get(i));
        }
        realm.commitTransaction();
    }

    /**
     * Return the ministers in the database who are not a member of the Player's Cabinet
     * @return
     */
    public static LinkedList<Minister> getAllMinistersNotInCabinet(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Minister> results = realm.where(Minister.class).findAll();
        LinkedList<Minister> ministers =  RealmHelper.getLinkedListFromRealmResults(results);
        LinkedList<Minister> playerMinisters = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getMinisters();
        LinkedList<Minister> nonCabinetMinisters = new LinkedList<>();

        //If the minister is not contained in the player's Cabinet, add to the list to return
        for(int i = 0; i < ministers.size(); i++){
            if(!playerMinisters.contains(ministers.get(i))){
                nonCabinetMinisters.add(ministers.get(i));
            }
        }

        return nonCabinetMinisters;
    }

    /**
     * Get specific minister by ID
     * @param ID
     * @return
     */
    public static Minister getMinisterByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Minister minister = realm.where(Minister.class).equalTo("mID", ID).findFirst();
        return minister;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Minister.class).findAll().size() + 1;
    }
}
