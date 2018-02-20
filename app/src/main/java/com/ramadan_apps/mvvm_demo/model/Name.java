package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class Name extends RealmObject implements Serializable {

    @SerializedName("title") public String title;

    @SerializedName("first") public String firts;

    @SerializedName("last") public String last;
}
