package com.ramadan_apps.mvvm_demo.data;

import com.google.gson.annotations.SerializedName;
import com.ramadan_apps.mvvm_demo.model.People;

import java.util.List;

/**
 * Created by Mahmoud Ramadan on 2/11/18.
 */

public class PeopleResponse {

    @SerializedName("results") private List<People> peopleList;

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> mPeopleList) {
        this.peopleList = mPeopleList;
    }
}
