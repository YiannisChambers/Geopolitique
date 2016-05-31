package data;

import io.realm.Realm;
import model.Country;

/**
 * Created by yiannischambers on 31/05/2016.
 */
public class CountryRepo {

    public static  Country createNewCountry(Country country) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        country.setID(getNextKey());
        Country createdCountry = realm.copyToRealm(country);
        //createdCountry.setLeader(country.getLeader());

        realm.commitTransaction();

        return createdCountry;
    }

    public static void updateCountry(Country country){

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(country);

        realm.commitTransaction();
    }

    public static Country getCountryByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Country issue = realm.where(Country.class).equalTo("mID", ID).findFirst();
        return issue;
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Country.class).findAll().size() + 1;
    }

}
