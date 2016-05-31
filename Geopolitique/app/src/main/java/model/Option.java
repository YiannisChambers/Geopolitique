package model;

import java.util.LinkedList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Option extends RealmObject{
    @PrimaryKey
    long mID;

    String mDescription;

    RealmList<Effect> mEffects;

    public Option(){}

    public Option(String description, RealmList<Effect> effects){
        mDescription = description;
        mEffects = effects;
    }


    public long getID() {
        return mID;
    }

    public void setID(long mID) {
        this.mID = mID;
    }

    public String getDescription() {
        return mDescription;
    }

    public RealmList<Effect> getEffects() {
        return mEffects;
    }
}
