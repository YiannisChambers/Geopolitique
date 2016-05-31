package data;

import io.realm.Realm;
import model.Effect;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class EffectRepo {

    public static Effect createNewEffect(Effect effect){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        effect.setID(getNextKey());
        Effect e = realm.copyToRealm(effect);
        realm.commitTransaction();
        return e;
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Effect.class).findAll().size() + 1;
    }
}
