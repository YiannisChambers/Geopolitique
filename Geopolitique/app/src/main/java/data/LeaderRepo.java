package data;

import io.realm.Realm;
import model.Leader;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class LeaderRepo {

    public static  Leader createNewLeader(Leader leader) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        leader.setID(getNextKey());
        Leader createdLeader = realm.copyToRealm(leader);

        realm.commitTransaction();

        return createdLeader;
    }


    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Leader.class).findAll().size() + 1;
    }


}
