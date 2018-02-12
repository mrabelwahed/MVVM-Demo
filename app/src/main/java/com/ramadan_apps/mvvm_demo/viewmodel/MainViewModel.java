package com.ramadan_apps.mvvm_demo.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.ramadan_apps.mvvm_demo.MyApp;
import com.ramadan_apps.mvvm_demo.R;
import com.ramadan_apps.mvvm_demo.data.PeopleFactory;
import com.ramadan_apps.mvvm_demo.data.PeopleResponse;
import com.ramadan_apps.mvvm_demo.data.PeopleService;
import com.ramadan_apps.mvvm_demo.model.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class MainViewModel  extends Observable{

    public ObservableInt peopleProgressbar;
    public ObservableInt peopleRecycler;
    public ObservableInt peopleLabel;
    public ObservableField<String> messageLabel;

    private List<People> peopleList;
    private Context context;

    private CompositeDisposable compositeDisposable  = new CompositeDisposable();;

    public MainViewModel(@NonNull Context context){
      this.context = context;
      peopleRecycler = new ObservableInt(View.GONE);
      peopleLabel= new ObservableInt(View.VISIBLE);
      peopleProgressbar = new ObservableInt(View.GONE);
      messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
      this.peopleList = new ArrayList<>();

    }


    public List<People> getPeopleList(){
        return peopleList;
    }

    public void  changePeopleDataSet(List<People>dataset){
        peopleList.addAll(dataset);
        setChanged();
        notifyObservers();
    }

    public void onFabBtnClicked(View view){
        initializeViews();
        fetchPeopleList();
    }


    private void initializeViews(){
        peopleProgressbar.set(View.VISIBLE);
        peopleLabel.set(View.GONE);
        peopleRecycler.set(View.GONE);
    }


    private void fetchPeopleList(){
        MyApp peopleApplication = MyApp.create(context);
        PeopleService peopleService = peopleApplication.getPeopleService();

        Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PeopleResponse>() {
                    @Override public void accept(PeopleResponse peopleResponse) throws Exception {
                        changePeopleDataSet(peopleResponse.getPeopleList());
                        peopleProgressbar.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        peopleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_people));
                        peopleProgressbar.set(View.GONE);
                        peopleLabel.set(View.VISIBLE);
                        peopleRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void reset(){
      unsubscribeFromDisposable();
      compositeDisposable = null;
      context =null;

    }

    private void unsubscribeFromDisposable(){
        if (compositeDisposable !=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
