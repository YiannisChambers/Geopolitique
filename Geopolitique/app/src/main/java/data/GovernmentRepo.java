/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import java.util.LinkedList;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Government;

/**
 * Repository Class for Government Realm Object
 */
public class GovernmentRepo {
    /**
     * Creates and adds a new Government object to the Database.
     *
     * @param government The Government object to add to the database.
     * @return
     */
    public static Government createNewGovernment(Government government){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        government.setID(getNextKey());
        Government createdGovernemnt = realm.copyToRealm(government);
        realm.commitTransaction();

        return createdGovernemnt;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Government.class).findAll().size() + 1;
    }

}
