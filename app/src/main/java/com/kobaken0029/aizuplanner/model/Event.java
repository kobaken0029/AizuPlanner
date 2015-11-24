package com.kobaken0029.aizuplanner.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Event implements Serializable {
    private String title;
    private String place;
    private String description;
    private String link;
    private String contact;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("bosyu_start")
    private String bosyuStart;
    @SerializedName("bosyu_end")
    private String bosyuEnd;
    @SerializedName("bosyu_description")
    private String bosyuDescription;
    @SerializedName("x")
    private String ltg;
    @SerializedName("y")
    private String lat;
}
