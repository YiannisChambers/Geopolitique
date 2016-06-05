package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Issue;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class IssueRepo {
    public static void createNewIssue(Issue issue){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        issue.setID(getNextKey());
        realm.copyToRealm(issue);
        realm.commitTransaction();
    }

    public static void createNewIssues(LinkedList<Issue> issues){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i = 0; i < issues.size(); i++){
            issues.get(i).setID(getNextKey());
            realm.copyToRealm(issues.get(i));
        }
        realm.commitTransaction();
    }

    public static LinkedList<Issue> getAllIssues(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Issue> results = realm.where(Issue.class).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static LinkedList<Issue> getAllUnresolvedIssues(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Issue> results = realm.where(Issue.class).equalTo("mIsResolved", false).findAll();
        return RealmHelper.getLinkedListFromRealmResults(results);
    }

    public static Issue getIssueByID(long ID){
        Realm realm = Realm.getDefaultInstance();
        Issue issue = realm.where(Issue.class).equalTo("mID", ID).findFirst();
        return issue;
    }

    public static void updateMinister(Issue issue){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(issue);
        realm.commitTransaction();
    }

    public static void deleteAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Issue> results = realm.where(Issue.class).findAll();
        results.deleteAllFromRealm();
    }

    public static int getNextKey()
    {   Realm realm = Realm.getDefaultInstance();
        return realm.where(Issue.class).findAll().size() + 1;
    }
}
