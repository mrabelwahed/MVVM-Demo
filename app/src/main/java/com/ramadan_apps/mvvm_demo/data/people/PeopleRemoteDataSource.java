package com.ramadan_apps.mvvm_demo.data.people;

import android.content.Context;
import android.util.Log;

import com.ramadan_apps.mvvm_demo.MyApp;
import com.ramadan_apps.mvvm_demo.data.PeopleFactory;
import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mahmoud on 19/02/18.
 */

public class PeopleRemoteDataSource implements PeopleRepoContract.Remote<People> {
    private Context context;
    private PeopleLocalDataSource peopleLocalDataSource;
    private static String TAG = PeopleRemoteDataSource.class.getSimpleName();
    public PeopleRemoteDataSource(Context context) {
        this.context = context;
        peopleLocalDataSource = new PeopleLocalDataSource();
    }

    @Override
    public Flowable<List<People>> getRemotePeoples() {
       return MyApp.create(context).getPeopleService().
                fetchPeople(PeopleFactory.RANDOM_USER_URL)
               .flatMap(response -> Flowable.just(response.getPeopleList()))
                .doOnNext(it ->{
                    Log.d(TAG,"Dispatching"+it.size()+"From People APi");
                    peopleLocalDataSource.savePeoples(it);
                })
               .subscribeOn(Schedulers.io());
    }
}
