/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import java.util.LinkedList;
import io.realm.Realm;
import model.Option;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class OptionRepo {
    /**
     * Creates and adds a new Option object to the Database.
     *
     * @param option The Option object to add to the database.
     * @return
     */
    public static Option createNewOption(Option option){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        option.setID(getNextKey());
        Option createdIssue = realm.copyToRealm(option);

        realm.commitTransaction();

        return createdIssue;
    }


    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Option.class).findAll().size() + 1;
    }


}
