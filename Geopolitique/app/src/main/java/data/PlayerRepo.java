/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package data;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Player;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class PlayerRepo {
    /**
     * Creates and adds a new Player object to the Database.
     *
     * @param player The Player object to add to the database.
     * @return
     */
    public static Player createNewPlayer(Player player){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Player createdPlayer = realm.copyToRealm(player);
        realm.commitTransaction();

        return createdPlayer;

    }

    public static boolean checkIfPlayerExists(){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Player> results = realm.where(Player.class).findAll();

        return results.size() != 0;

    }

    public static Player getCurrentPlayer(){
        Realm realm = Realm.getDefaultInstance();

        Player player = realm.where(Player.class).findFirst();
        return player;
    }
}
