package com.ramadan_apps.mvvm_demo.data.people;

import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by mahmoud on 19/02/18.
 */

public class PeopleLocalDataSource implements PeopleRepoContract.Local<People> {
    @Override
    public Flowable<List<People>> getCachedPeoples() {
        return null;
    }

    @Override
    public Completable savePeoples(List<People> peoples) {
        return null;
    }
}
