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
 * Created by yiannischambers on 1/06/2016.
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


    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Government.class).findAll().size() + 1;
    }

}
