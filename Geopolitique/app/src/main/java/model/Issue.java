package model;

import java.util.LinkedList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Issue extends RealmObject{
    @PrimaryKey
    long mID;

    String mName;
    String mDescription;
    RealmList<Option> mOptions;

    //TODO:
    //Add results - RealmList<String> results - that appears as a Dialog when an option is selected.

    Option mSelectedOption;

    boolean mIsResolved;

    boolean mIsFinished;

    int mStaleFactor;

    public Issue(){};


    public Issue(String name, String description, RealmList<Option> options){
        mName = name;
        mDescription = description;
        mOptions = options;
        mIsFinished = false;
        mStaleFactor = 0;
        isResolved();
    }

    public long getID() {
        return mID;
    }

    public RealmList<Option> getOptions() {
        return mOptions;
    }

    public Option getSelectedOption() {
        return mSelectedOption;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public void selectOption(int position){
        mSelectedOption = mOptions.get(position);
        mIsResolved = true;
    }

    public boolean isResolved(){
        mIsResolved = !(mSelectedOption == null);
        return mIsResolved;
    }

    public boolean isFinished(){
        return mIsFinished;
    }

    public void finishIssue(){
        mIsFinished = true;
    }


    public int getStaleFactor(){
        return mStaleFactor;
    }

    public void incrementStaleFactor(){
        mStaleFactor += 1;
    }

    public void setID(long mID) {
        this.mID = mID;
    }
}

