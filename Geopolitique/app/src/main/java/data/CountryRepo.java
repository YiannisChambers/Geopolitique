/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import model.Country;

/**
 * Created by yiannischambers on 31/05/2016.
 */
public class CountryRepo {

    /**
     * Creates and adds a new Country object to the Database.
     *
     * @param country The Country object to add to the database.
     * @return
     */
    public static  Country createNewCountry(Country country) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        country.setID(getNextKey());
        //Create the object
        Country createdCountry = realm.copyToRealm(country);

        realm.commitTransaction();

        return createdCountry;
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Country.class).findAll().size() + 1;
    }

}
