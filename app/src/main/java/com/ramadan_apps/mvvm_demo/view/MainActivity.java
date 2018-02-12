package com.ramadan_apps.mvvm_demo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ramadan_apps.mvvm_demo.R;
import com.ramadan_apps.mvvm_demo.databinding.ActivityMainBinding;
import com.ramadan_apps.mvvm_demo.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{
   private  ActivityMainBinding binding;
    private  MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(binding.toolbar);
        setupRecyclerview(binding.recyclerview);
        setupObserver(mainViewModel);

    }

    private void initDataBinding(){
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         mainViewModel = new MainViewModel(getApplicationContext());
        binding.setMainViewModel(mainViewModel);
    }

    private void setupRecyclerview(RecyclerView recyclerView){
     PeopleAdapter adapter= new PeopleAdapter();
     recyclerView.setAdapter(adapter);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof MainViewModel){
            PeopleAdapter adapter = (PeopleAdapter) binding.recyclerview.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            adapter.setPeopleList(mainViewModel.getPeopleList());
        }

    }

    private void setupObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }
}
