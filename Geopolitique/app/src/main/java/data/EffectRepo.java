/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import model.Effect;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class EffectRepo {
    /**
     * Creates and adds a new Effect object to the Database.
     *
     * @param effect The Effect object to add to the database.
     * @return
     */
    public static Effect createNewEffect(Effect effect){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
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
