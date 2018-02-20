package com.ramadan_apps.mvvm_demo;

import android.app.Application;
import android.content.Context;

import com.ramadan_apps.mvvm_demo.data.PeopleFactory;
import com.ramadan_apps.mvvm_demo.data.PeopleService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Mahmoud Ramadan on 2/11/18.
 */

public class MyApp extends Application {
    private PeopleService peopleService;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    private static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    public static MyApp create(Context context) {
        return MyApp.get(context);
    }

    public PeopleService getPeopleService() {
        if (peopleService == null) {
            peopleService = PeopleFactory.create();
        }

        return peopleService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
