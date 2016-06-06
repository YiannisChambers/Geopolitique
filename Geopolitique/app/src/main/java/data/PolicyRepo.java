/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import java.util.LinkedList;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Policy;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class PolicyRepo {
    /**
     * Creates and adds a series of new Policy objects to the Database.
     *
     * @param policies The Policy objects to add to the database.
     * @return
     */
    public static void createNewPolicies(LinkedList<Policy> policies){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i= 0; i < policies.size(); i++){
            //Set new object to have the next ID in the database.
            policies.get(i).setID(getNextKey());
            realm.copyToRealmOrUpdate(policies.get(i));
        }

        realm.commitTransaction();
    }


    public static Policy getPolicyByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Policy issue = realm.where(Policy.class).equalTo("mID", ID).findFirst();
        return issue;
    }


    public static LinkedList<Policy> getUnadoptedPolicies(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Policy> results = realm.where(Policy.class).findAll();

        LinkedList<Policy> policies =  RealmHelper.getLinkedListFromRealmResults(results);
        LinkedList<Policy> playerPolicies = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getTotalPolicies();
        LinkedList<Policy> unadoptedPolicies = new LinkedList<>();

        for(int i = 0; i < policies.size();i++){
            if(!playerPolicies.contains(policies.get(i)))
            {
                unadoptedPolicies.add(policies.get(i));
            }
        }
        return unadoptedPolicies;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Policy.class).findAll().size() + 1;
    }
}
