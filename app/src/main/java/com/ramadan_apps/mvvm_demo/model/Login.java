package com.ramadan_apps.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mahmoud Ramadan on 2/10/18.
 */

public class Login implements Serializable {
    @SerializedName("username") public String userName;
}
