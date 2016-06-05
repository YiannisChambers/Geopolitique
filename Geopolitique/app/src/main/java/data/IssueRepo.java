package data;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Issue;

/**
 * Created by yiannischambers on 1/06/2016.
 */
public class IssueRepo {

    static int number = 0;

    public static Issue createNewIssue(Issue issue){
        Realm realm = Realm.getDefaultInstance();

        //if(!realm.isInTransaction())
        realm.beginTransaction();

        issue.setID(number);//getNextKey());
        Issue createdIssue = realm.copyToRealm(issue);

        realm.commitTransaction();
        number+=1;

        return createdIssue;
    }

    public static void duplicateIssue(Issue issue){
        Realm realm = Realm.getDefaultInstance();

        createNewIssue(issue);

        /*
        ////if(!realm.isInTransaction())
        realm.beginTransaction();
        Issue createdIssue = getIssueByID(issue.getID());

        if(createdIssue != null) {
            createdIssue.setID(number);//getNextKey());
            Issue duplicateIssue = realm.copyToRealm(createdIssue);
        }
        else
        {
            //issue.setID(number);
            //Issue duplicateIssue = realm.copyToRealm(issue);
        }
        */


        //realm.commitTransaction();
    }

    public static void createNewIssues(LinkedList<Issue> issues){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(int i = 0; i < issues.size(); i++){
            issues.get(i).setID(number);//getNextKey());
            realm.copyToRealm(issues.get(i));
            number+=1;
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
