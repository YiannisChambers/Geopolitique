package data;

import io.realm.Realm;
import model.Leader;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class LeaderRepo {

    public static void createNewLeader(Leader leader){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealm(leader);

        realm.commitTransaction();

    }



}
