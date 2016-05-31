package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Government;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class GovernmentRepo {
    public static void createNewGovernment(Government government){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(government);
        realm.commitTransaction();
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
}
