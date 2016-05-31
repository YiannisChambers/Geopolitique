package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Economy;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class EconomyRepo {
    public static void createNewEconomy(Economy economy){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(economy);
        realm.commitTransaction();
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
}
