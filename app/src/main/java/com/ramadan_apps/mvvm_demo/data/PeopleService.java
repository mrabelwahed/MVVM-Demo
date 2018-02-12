package com.ramadan_apps.mvvm_demo.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Mahmoud Ramadan on 2/11/18.
 */

public interface PeopleService {

    @GET
    Observable<PeopleResponse> fetchPeople(@Url String url);

}
