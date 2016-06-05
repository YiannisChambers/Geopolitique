package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Economy;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class EconomyRepo {
    public static Economy createNewEconomy(Economy economy){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        economy.setID(getNextKey());
        Economy createdEconomy = realm.copyToRealm(economy);
        realm.commitTransaction();

        return createdEconomy;
    }

    public static LinkedList<Economy> getAllEconomies(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Economy> results = realm.where(Economy.class).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static void updateEconomy(Economy economy){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(economy);
        realm.commitTransaction();
    }

    public static void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Economy> results = realm.where(Economy.class).findAll();
        results.deleteAllFromRealm();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Economy.class).findAll().size() + 1;
    }
}
