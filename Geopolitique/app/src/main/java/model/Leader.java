package model;

/**
 * Created by yiannischambers on 20/05/2016.
 */
public class Leader {
    String mFirstName;
    String mLastName;
    String mTitle;

    public Leader(){
        mFirstName = "John";
        mLastName = "Smith";
        mTitle = "Prime Minister";
    }

    public Leader(String first, String last, String title) {
        mFirstName = first;
        mLastName = last;
        mTitle = title;
    }

    public String getFullName() {
        return mFirstName + " " + mLastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFullNameWithTitle() {
        return mTitle + " " + mFirstName + " " + mLastName;
    }

    public String getShortNameWithTitle() {
        return mTitle + " " + mLastName;
    }
}
