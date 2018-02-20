package com.ramadan_apps.mvvm_demo.data.people;

import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by mahmoud on 19/02/18.
 */

public class PeopleRemoteDataSource implements PeopleRepoContract.Remote<People> {
    @Override
    public Flowable<List<People>> getRemotePeoples() {



        return null;
    }
}
