package geopolitique.id11699156.com.geopolitique;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Option {
    String mDescription;
    LinkedList<Effect> mEffects;

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
