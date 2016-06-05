package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Government;
import model.Leader;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class GovernmentRepo {
    public static Government createNewGovernment(Government government){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        government.setID(getNextKey());
        Government createdGovernemnt = realm.copyToRealm(government);
        realm.commitTransaction();

        return createdGovernemnt;
    }

    public static LinkedList<Government> getAllGovernments(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Government> results = realm.where(Government.class).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static void updateGovernment(Government government){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(government);
        realm.commitTransaction();
    }

    public static void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Government> results = realm.where(Government.class).findAll();
        results.deleteAllFromRealm();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Government.class).findAll().size() + 1;
    }

}
