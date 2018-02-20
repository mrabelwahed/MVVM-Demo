package com.ramadan_apps.mvvm_demo.data.people;

import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;

/**
 * Created by mahmoud on 19/02/18.
 */

public class PeopleLocalDataSource implements PeopleRepoContract.Local<People> {

    private static final String TAG = PeopleLocalDataSource.class.getSimpleName();
    @Override
    public Flowable<List<People>> getCachedPeoples() {
        Realm realm =Realm.getDefaultInstance();
        return  Flowable.just(
                realm.copyFromRealm(realm.where(People.class).findAllAsync()))
                .filter(people -> !people.isEmpty())
                .doOnComplete(realm::close);
    }

    @Override
    public void savePeoples(List<People> peopleList) {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.insertOrUpdate(peopleList));
    }
}
