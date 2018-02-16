package com.ramadan_apps.mvvm_demo.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ramadan_apps.mvvm_demo.R;
import com.ramadan_apps.mvvm_demo.databinding.ActivityMainBinding;
import com.ramadan_apps.mvvm_demo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(binding.toolbar);


        setupRecyclerview(this,mainViewModel);

        observeMainViewModel();


    }

    private void observeMainViewModel() {
        mainViewModel.getProgressbarObservable().observe(this, isloading -> {
            binding.progressbar.setVisibility(isloading ? View.VISIBLE : View.GONE);
        });

        mainViewModel.getLabelObservable().observe(this, isVisible -> {
            binding.label.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        });

        mainViewModel.getRecyclerViewObservable().observe(this, isVisible ->{
            binding.recyclerview.setVisibility(isVisible?View.VISIBLE:View.GONE);
        });

        mainViewModel.getReposObservable().observe(this, repos -> {
            if (repos != null) {
                binding.recyclerview.setVisibility(View.VISIBLE);
                binding.label.setVisibility(View.GONE);
                binding.progressbar.setVisibility(View.GONE);
            }
        });


    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMainViewModel(mainViewModel);
    }

    private void setupRecyclerview(LifecycleOwner owner, MainViewModel mainViewModel) {
        PeopleAdapter adapter = new PeopleAdapter(mainViewModel, owner);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }
}
