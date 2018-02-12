package com.ramadan_apps.mvvm_demo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ramadan_apps.mvvm_demo.R;
import com.ramadan_apps.mvvm_demo.databinding.ItemPeopleBinding;
import com.ramadan_apps.mvvm_demo.model.People;
import com.ramadan_apps.mvvm_demo.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> peopleList;

    public PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @Override public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,
                        parent, false);
        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override public int getItemCount() {
        return peopleList.size();
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding mItemPeopleBinding;

        public PeopleAdapterViewHolder(ItemPeopleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        void bindPeople(People people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(
                        new ItemPeopleViewModel(people, itemView.getContext()));
            } else {
                mItemPeopleBinding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}