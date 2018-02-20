package com.ramadan_apps.mvvm_demo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.ramadan_apps.mvvm_demo.R;
import com.ramadan_apps.mvvm_demo.data.people.PeopleLocalDataSource;
import com.ramadan_apps.mvvm_demo.data.people.PeopleRemoteDataSource;
import com.ramadan_apps.mvvm_demo.data.people.PeopleRepository;
import com.ramadan_apps.mvvm_demo.model.People;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class MainViewModel  extends AndroidViewModel{

    public MutableLiveData<Boolean> progressbarObservable = new MutableLiveData<>();
    public MutableLiveData<Boolean> labelObservable = new MutableLiveData<>();
    public MutableLiveData<Boolean> recyclerViewObservable = new MutableLiveData<>();
    public MutableLiveData<List<People>> reposObservable = new MutableLiveData<>();

    public ObservableField<String> messageLabel;

    private static final String TAG = MainViewModel.class.getSimpleName();

    public MutableLiveData<Boolean> getRecyclerViewObservable() {
        return recyclerViewObservable;
    }

    private List<People> peopleList;
    private Context context;
    public PeopleRepository peopleRepository;

    private CompositeDisposable compositeDisposable  = new CompositeDisposable();

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.peopleList = new ArrayList<>();
        this.context = application.getApplicationContext();
         messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
         peopleRepository =PeopleRepository.getInstance(new PeopleRemoteDataSource(context),
                 new PeopleLocalDataSource());
    }



    public List<People> getPeopleList(){
        return peopleList;
    }


    public void onFabBtnClicked(View view){
        initializeViews();
        fetchPeopleList();
    }


    private void initializeViews(){
        progressbarObservable.setValue(true);
        labelObservable.setValue(false);
        recyclerViewObservable.setValue(false);
    }

    public LiveData<Boolean> getProgressbarObservable() {
        return progressbarObservable;
    }

    public LiveData<List<People>> getReposObservable() {
        return reposObservable;
    }

    public LiveData<Boolean> getLabelObservable() {
        return labelObservable;
    }

    private void fetchPeopleList(){

        Disposable disposable = peopleRepository.getPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(peoples -> {
                    progressbarObservable.setValue(false);
                    labelObservable.setValue(false);
                    reposObservable.setValue(peoples);
                }, throwable -> {
                    Log.e(TAG,throwable.getMessage());
                    messageLabel.set(context.getString(R.string.error_loading_people));
                    progressbarObservable.setValue(false);
                    labelObservable.setValue(true);
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


    @Override
    protected void onCleared() {
        super.onCleared();
        reset();
    }
}
