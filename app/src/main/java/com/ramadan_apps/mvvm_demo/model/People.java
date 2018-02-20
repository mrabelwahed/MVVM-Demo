package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */
public class People extends RealmObject implements Serializable {
    @SerializedName("gender") public String gender;

    @SerializedName("name") public Name name;

    @SerializedName("location") public com.ramadan_apps.mvvm_demo.model.Location location;

    @SerializedName("email") public String mail;

    @SerializedName("login") public Login userName;

    @SerializedName("phone") public String phone;

    @SerializedName("cell") public String cell;

    @SerializedName("picture") public com.ramadan_apps.mvvm_demo.model.Picture picture;

    public String fullName;

    public boolean hasEmail() {
        return mail != null && !mail.isEmpty();
    }
}
