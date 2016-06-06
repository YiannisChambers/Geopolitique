/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import model.Cabinet;

/**
 * Repository Class for Cabinet Realm Object
 */
public class CabinetRepo {

    /**
     * Creates and adds a new Cabinet object to the Database.
     *
     * @param cabinet The Cabinet object to add to the database.
     * @return
     */
    public static Cabinet createNewCabinet(Cabinet cabinet) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        cabinet.setID(getNextKey());
        //Create the Object
        Cabinet createdCabinet = realm.copyToRealm(cabinet);
        realm.commitTransaction();

        return createdCabinet;
    }

    /**
     * Gets the next valid Primary Key value for the Cabinet table
     * @return
     */
    public static int getNextKey() {
        Realm realm = Realm.getDefaultInstance();
        //Return the amount of Cabinet objects in the Database, plus one
        return realm.where(Cabinet.class).findAll().size() + 1;
    }
}
