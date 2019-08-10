package com.laioffer.matrix;
/**
 * 写成private然后加getter&setter，而不是直接public去写的目的：
 * 1.封装性
 * 2.让用户只能以指定方法和bar改变数据，保证数据格式的正确性，setter里会有规定，比如年龄不可能是10000，会throw exception / alert
 * */
public class TrafficEvent {
    private String id;
    private String event_type;
    private int event_comment_number;
    private long event_timestamp;
    private double event_longitude;
    private double event_latitude;
    private String event_reporter_id;
    private String event_level;
    private int event_like_number;
    private String event_description;
    private String imgUri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public int getEvent_comment_number() {
        return event_comment_number;
    }

    public void setEvent_comment_number(int event_comment_number) {
        this.event_comment_number = event_comment_number;
    }

    public long getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(long event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public double getEvent_longitude() {
        return event_longitude;
    }

    public void setEvent_longitude(double event_longitude) {
        this.event_longitude = event_longitude;
    }

    public double getEvent_latitude() {
        return event_latitude;
    }

    public void setEvent_latitude(double event_latitude) {
        this.event_latitude = event_latitude;
    }

    public String getEvent_reporter_id() {
        return event_reporter_id;
    }

    public void setEvent_reporter_id(String event_reporter_id) {
        this.event_reporter_id = event_reporter_id;
    }

    public String getEvent_level() {
        return event_level;
    }

    public void setEvent_level(String event_level) {
        this.event_level = event_level;
    }

    public int getEvent_like_number() {
        return event_like_number;
    }

    public void setEvent_like_number(int event_like_number) {
        this.event_like_number = event_like_number;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

}

