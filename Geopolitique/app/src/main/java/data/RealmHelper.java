/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import java.util.LinkedList;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * A utility class to perform database and Realm related usefulness
 */
public class RealmHelper {

    private static Realm realmInstance;
    public static <T extends RealmModel> LinkedList<T> getLinkedListFromRealmResults(RealmResults<T> results){
        LinkedList<T> list = new LinkedList<T>();

        for(int i = 0; i < results.size(); i++){
            list.add(results.get(i));
        }

        return list;
    }

    /**
     * Commence a transaction in Realm
     */
    public static void beginTransaction(){
        Realm realm = Realm.getDefaultInstance();

        //If there's not already a transaction happening, begin a new one
        if(!realm.isInTransaction())
            realm.beginTransaction();
    }

    /**
     * End a transaction in Realm
     */
    public static void endTransaction(){
        Realm realm = Realm.getDefaultInstance();
        realm.commitTransaction();
    }


    /**
     * Return a Realm instance.
     * @return
     */
    public static Realm realm(){
        if(realmInstance == null){
            realmInstance= Realm.getDefaultInstance();
        }
        return realmInstance;
    }
}
