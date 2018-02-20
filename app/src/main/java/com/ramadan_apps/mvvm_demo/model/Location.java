package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class Location extends RealmObject{
    @SerializedName("street") public String street;

    @SerializedName("city") public String city;

    @SerializedName("state") public String state;

    @SerializedName("zip") public String zip;
}
