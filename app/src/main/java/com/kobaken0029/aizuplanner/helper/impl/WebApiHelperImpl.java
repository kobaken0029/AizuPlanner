package com.kobaken0029.aizuplanner.helper.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.kobaken0029.aizuplanner.helper.WebApiHelper;
import com.kobaken0029.aizuplanner.internet.AizuEventApi;
import com.kobaken0029.aizuplanner.model.ResponseObject;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WebApiHelperImpl implements WebApiHelper {
    private RestAdapter mAdapter;

    public WebApiHelperImpl() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        mAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.data4citizen.jp")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("===NetWork==="))
                .build();
    }

    @Override
    public void request(Observer<ResponseObject> observer) {
        mAdapter.create(AizuEventApi.class).get()
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void requestByStartDay(String startDay, Observer<ResponseObject> observer) {

    }

    @Override
    public void requestByTitle(String title, Observer<ResponseObject> observer) {
        mAdapter.create(AizuEventApi.class).getByTitle(title)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void requestByDescription(String description, Observer<ResponseObject> observer) {

    }
}
