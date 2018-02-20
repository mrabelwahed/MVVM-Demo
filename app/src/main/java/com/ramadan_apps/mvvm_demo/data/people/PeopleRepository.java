package com.ramadan_apps.mvvm_demo.data.people;

import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by mahmoud on 19/02/18.
 */

public  class PeopleRepository implements PeopleRepoContract.Repository<People> {

    public static  PeopleRepository INSTANCE = null;
    public PeopleRepoContract.Remote remoteDataSource;
    public PeopleRepoContract.Local localDateSource;

    private PeopleRepository( PeopleRepoContract.Remote remoteDataSource, PeopleRepoContract.Local localDateSource){
        this.remoteDataSource = remoteDataSource;
        this.localDateSource = localDateSource;
    }


    public static PeopleRepository getInstance(PeopleRepoContract.Remote remoteDataSource, PeopleRepoContract.Local localDateSource){
        if (INSTANCE == null)
            INSTANCE = new PeopleRepository(remoteDataSource,localDateSource);
        return INSTANCE;
    }


    @Override
    public Flowable<List<People>> getPeople() {

          Flowable <List<People>>cachedPeople =localDateSource.getCachedPeoples();
          Flowable<List<People>> remotePeople = remoteDataSource.getRemotePeoples();

        return Flowable.
                concat(cachedPeople,remotePeople)
                .filter(peoples -> !peoples.isEmpty())
                 .firstOrError()
                 .toFlowable();
    }
}
