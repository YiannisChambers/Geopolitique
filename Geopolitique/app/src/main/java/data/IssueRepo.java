/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */


package data;

import java.util.LinkedList;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Issue;

/**
 * Repository Class for Issue Realm Object
 */
public class IssueRepo {
    /**
     * Creates and adds a new Issue object to the Database.
     *
     * @param issue The Issue object to add to the database.
     * @return
     */
    public static Issue createNewIssue(Issue issue){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Set new object to have the next ID in the database.
        issue.setID(getNextKey());
        Issue createdIssue = realm.copyToRealm(issue);

        realm.commitTransaction();


        return createdIssue;
    }

    /**
     * Create mulitple issues from a LinkedList
     * @param issues
     */
    public static void createNewIssues(LinkedList<Issue> issues){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //For each...
        for(int i = 0; i < issues.size(); i++){
            //...set the next key...
            issues.get(i).setID(getNextKey());
            //...copy to the database.
            realm.copyToRealm(issues.get(i));
        }
        realm.commitTransaction();
    }

    /**
     * Return all issues that have not been resolved by the Government
     * @return
     */
    public static LinkedList<Issue> getAllUnresolvedIssues(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Issue> results = realm.where(Issue.class).equalTo("mIsResolved", false).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    /**
     * Return a specific Issue by ID
     * @param ID
     * @return
     */
    public static Issue getIssueByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Issue issue = realm.where(Issue.class).equalTo("mID", ID).findFirst();
        return issue;
    }

    /**
     * Gets the next valid Primary Key value
     * @return
     */
    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Issue.class).findAll().size() + 1;
    }
}
