/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import model.Leader;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class LeaderRepo {
    /**
     * Creates and adds a new Leader object to the Database.
     *
     * @param leader The Leader object to add to the database.
     * @return
     */
    public static  Leader createNewLeader(Leader leader) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        leader.setID(getNextKey());
        Leader createdLeader = realm.copyToRealm(leader);

        realm.commitTransaction();

        return createdLeader;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Leader.class).findAll().size() + 1;
    }


}
