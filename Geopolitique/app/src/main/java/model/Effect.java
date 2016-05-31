package model;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Effect {

    String mProperty;
    int mEffect;

    public Effect(String countryProperty, int effect) {
        mProperty = countryProperty;
        mEffect = effect;
    }

    public String getProperty() {
        return mProperty;
    }

    public int getEffect() {
        return mEffect;
    }
}
