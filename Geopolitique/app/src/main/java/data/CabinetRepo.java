package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Cabinet;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class CabinetRepo {
    public static Cabinet createNewCabinet(Cabinet cabinet){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        cabinet.setID(getNextKey());
        Cabinet createdCabinet = realm.copyToRealm(cabinet);
        realm.commitTransaction();

        return createdCabinet;
    }

    public static LinkedList<Cabinet> getAllCabinets(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Cabinet> results = realm.where(Cabinet.class).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static void updateCabinet(Cabinet cabinet){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cabinet);
        realm.commitTransaction();
    }

    public static void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Cabinet> results = realm.where(Cabinet.class).findAll();
        results.deleteAllFromRealm();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Cabinet.class).findAll().size() + 1;
    }
}
