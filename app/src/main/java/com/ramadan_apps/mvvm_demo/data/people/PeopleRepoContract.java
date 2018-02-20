package com.ramadan_apps.mvvm_demo.data.people;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by mahmoud on 19/02/18.
 */

public interface PeopleRepoContract {

     interface Remote<T>{
        Flowable<List<T>> getRemotePeoples();
    }

     interface Local<T>{
      Flowable<List<T>> getCachedPeoples();
      Completable savePeoples(List<T> peoples);

    }

     interface Repository<T>{
        Flowable<List<T>> getPeople();
    }

}
