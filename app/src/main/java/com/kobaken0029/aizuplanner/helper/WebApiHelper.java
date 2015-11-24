package com.kobaken0029.aizuplanner.helper;

import com.kobaken0029.aizuplanner.model.ResponseObject;

import rx.Observer;

public interface WebApiHelper {
    void request(Observer<ResponseObject> observer);
    void requestByStartDay(String startDay, Observer<ResponseObject> observer);
    void requestByTitle(String title, Observer<ResponseObject> observer);
    void requestByDescription(String description, Observer<ResponseObject> observer);
}
