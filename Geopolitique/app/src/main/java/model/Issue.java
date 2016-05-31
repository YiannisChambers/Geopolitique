package model;

import java.util.LinkedList;

/**
 * Created by yiannischambers on 29/05/2016.
 */
public class Issue {
    String mName;
    String mDescription;
    LinkedList<Option> mOptions;
    Option mSelectedOption;
    boolean mIsResolved;
    boolean mIsFinished;
    int mStaleFactor;

    public Issue(String name, String description, LinkedList<Option> options){
        mName = name;
        mDescription = description;
        mOptions = options;
        mIsFinished = false;
        mStaleFactor = 0;
    }

    public LinkedList<Option> getOptions() {
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
}
