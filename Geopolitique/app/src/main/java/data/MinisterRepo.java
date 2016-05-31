package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Minister;
import model.Player;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class MinisterRepo {
    public static Minister createNewMinister(Minister minister){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        minister.setID(getNextKey());
        Minister createdMinister = realm.copyToRealm(minister);
        realm.commitTransaction();

        return createdMinister;
    }

    public static void createNewMinisters(LinkedList<Minister> ministers){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i = 0; i < ministers.size(); i++){
            ministers.get(i).setID(getNextKey());
            realm.copyToRealm(ministers.get(i));
        }
        realm.commitTransaction();
    }

    public static LinkedList<Minister> getAllMinisters(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Minister> results = realm.where(Minister.class).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static LinkedList<Minister> getAllMinistersNotInCabinet(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Minister> results = realm.where(Minister.class).findAll();
        LinkedList<Minister> ministers =  RealmHelper.getLinkedListFromRealmResults(results);
        LinkedList<Minister> playerMinisters = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getMinisters();
        LinkedList<Minister> nonCabinetMinisters = new LinkedList<>();

        for(int i = 0; i < ministers.size(); i++){
            if(!playerMinisters.contains(ministers.get(i))){
                nonCabinetMinisters.add(ministers.get(i));
            }
        }

        return nonCabinetMinisters;
    }

    public static Minister getMinisterByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Minister minister = realm.where(Minister.class).equalTo("mID", ID).findFirst();
        return minister;
    }

    public static void updateMinister(Minister minister){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(minister);
        realm.commitTransaction();
    }

    public static void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Minister> results = realm.where(Minister.class).findAll();
        results.deleteAllFromRealm();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Minister.class).findAll().size() + 1;
    }
}
