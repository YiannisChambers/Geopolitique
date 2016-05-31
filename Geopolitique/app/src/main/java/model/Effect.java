package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Effect extends RealmObject {

    @PrimaryKey
    long mID;

    String mProperty;

    int mEffect;

    public Effect() {
    }

    public Effect(String countryProperty, int effect) {
        mProperty = countryProperty;
        mEffect = effect;
    }

    public long getID() {
        return mID;
    }

    public String getProperty() {
        return mProperty;
    }

    public int getEffect() {
        return mEffect;
    }

    public void setID(long mID) {
        this.mID = mID;
    }
}
