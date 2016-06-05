package data;

import java.util.Arrays;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Country;
import model.ExistingCountry;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class ExistingCountryRepo {

    public static LinkedList<ExistingCountry> getAllExistingCountries()
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ExistingCountry> existingCountries = realm.where(ExistingCountry.class).findAll();

        return RealmHelper.getLinkedListFromRealmResults(existingCountries);
    }

    public static ExistingCountry createExistingCountry(ExistingCountry existingCountry){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        existingCountry.setID(getNextKey());
        ExistingCountry createdExistingCountry = realm.copyToRealm(existingCountry);

        realm.commitTransaction();

        return createdExistingCountry;
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(ExistingCountry.class).findAll().size() + 1;
    }
}
