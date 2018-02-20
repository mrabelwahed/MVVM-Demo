package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class Login extends RealmObject implements Serializable {
    @SerializedName("username") public String userName;
}
