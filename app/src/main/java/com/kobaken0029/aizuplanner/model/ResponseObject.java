package com.kobaken0029.aizuplanner.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseObject {
    private String result;
    private Integer limit;
    private Integer offset;
    private Integer count;
    private boolean next;
    private String lastUpdate;
    private List<Event> data;
}
