package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Option;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class OptionRepo {
    public static Option createNewOption(Option option){
        Realm realm = Realm.getDefaultInstance();

        //if(!realm.isInTransaction())
        realm.beginTransaction();
        option.setID(getNextKey());
        Option createdIssue = realm.copyToRealm(option);

        realm.commitTransaction();

        return createdIssue;
    }

    public static void createNewOptions(LinkedList<Option> issues){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i = 0; i < issues.size(); i++){
            issues.get(i).setID(getNextKey());
            realm.copyToRealm(issues.get(i));
        }
        realm.commitTransaction();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Option.class).findAll().size() + 1;
    }


}
