package data;

import io.realm.Realm;
import model.Option;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class OptionRepo {
    public static Option createNewOption(Option option){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        option.setID(getNextKey());
        Option o = realm.copyToRealm(option);
        realm.commitTransaction();
        return o;
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Option.class).findAll().size() + 1;
    }
}
