/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import model.Economy;

public class EconomyRepo {
    /**
     * Creates and adds a new Economy object to the Database.
     *
     * @param economy The Economy object to add to the database.
     * @return
     */
    public static Economy createNewEconomy(Economy economy){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        economy.setID(getNextKey());
        Economy createdEconomy = realm.copyToRealm(economy);
        realm.commitTransaction();

        return createdEconomy;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Economy.class).findAll().size() + 1;
    }
}
