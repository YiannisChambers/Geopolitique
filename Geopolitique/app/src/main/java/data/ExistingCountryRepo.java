/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.ExistingCountry;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class ExistingCountryRepo {

    /**
     * Creates and adds a new Existing Country object to the Database.
     *
     * @param existingCountry The Existing Country object to add to the database.
     * @return
     */
    public static ExistingCountry createExistingCountry(ExistingCountry existingCountry) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        existingCountry.setID(getNextKey());
        ExistingCountry createdExistingCountry = realm.copyToRealm(existingCountry);

        realm.commitTransaction();

        return createdExistingCountry;
    }

    public static LinkedList<ExistingCountry> getAllExistingCountries() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ExistingCountry> existingCountries = realm.where(ExistingCountry.class).findAll();

        return RealmHelper.getLinkedListFromRealmResults(existingCountries);
    }

    public static int getNextKey() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ExistingCountry.class).findAll().size() + 1;
    }
}
