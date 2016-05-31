package model;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Option {
    String mDescription;
    LinkedList<Effect> mEffects;

    /**
     *
     * @param description
     * @param effects
     */
    public Option(String description, LinkedList<Effect> effects){
        mDescription = description;
        mEffects = effects;
    }

    public String getDescription() {
        return mDescription;
    }

    public LinkedList<Effect> getEffects() {
        return mEffects;
    }
}
