package com.ramadan_apps.mvvm_demo.data.people;

import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by mahmoud on 19/02/18.
 */

public  class PeopleRepository implements PeopleRepoContract.Repository<People> {

    public static  PeopleRepository INSTANCE = null;
    public PeopleRemoteDataSource remoteDataSource;
    public PeopleLocalDataSource localDateSource;

    private PeopleRepository( PeopleRemoteDataSource remoteDataSource, PeopleLocalDataSource localDateSource){
        this.remoteDataSource = remoteDataSource;
        this.localDateSource = localDateSource;
    }


    public static PeopleRepository getInstance(PeopleRemoteDataSource remoteDataSource, PeopleLocalDataSource localDateSource){
        if (INSTANCE == null)
            INSTANCE = new PeopleRepository(remoteDataSource,localDateSource);
        return INSTANCE;
    }


    @Override
    public Flowable<List<People>> getPeople() {
        return Flowable.concat(localDateSource.getCachedPeoples(),remoteDataSource.getRemotePeoples());
    }
}
