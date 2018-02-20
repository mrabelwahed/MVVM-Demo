package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class Picture extends RealmObject implements Serializable {

    @SerializedName("large") public String large;

    @SerializedName("medium") public String medium;

    @SerializedName("thumbnail") public String thumbnail;
}
