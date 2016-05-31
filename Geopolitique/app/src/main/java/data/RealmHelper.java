package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class RealmHelper {

    public static <T extends RealmModel> LinkedList<T> getLinkedListFromRealmResults(RealmResults<T> results){
        LinkedList<T> list = new LinkedList<T>();

        for(int i = 0; i < results.size(); i++){
            list.add(results.get(i));
        }

        return list;
    }

    public static void beginTransaction(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
    }

    public static void endTransaction(){
        Realm realm = Realm.getDefaultInstance();
        realm.commitTransaction();
    }
}
