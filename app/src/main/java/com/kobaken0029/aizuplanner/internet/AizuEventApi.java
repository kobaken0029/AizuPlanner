package com.kobaken0029.aizuplanner.internet;

import com.kobaken0029.aizuplanner.model.ResponseObject;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface AizuEventApi {
    @GET("/app/users/openDataOutput/json/get/o_aizu_event")
    Observable<ResponseObject> get();

    @GET("/app/users/openDataOutput/json/get/o_aizu_event")
    Observable<ResponseObject> getByStartDay(@Query("start_day") String startDay);

    @GET("/app/users/openDataOutput/json/get/o_aizu_event")
    Observable<ResponseObject> getByTitle(@Query("title") String title);

    @GET("/app/users/openDataOutput/json/get/o_aizu_event")
    Observable<ResponseObject> getByDescription(@Query("description") String description);
}
