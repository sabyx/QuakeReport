package com.example.android.quakereport;

public class Earthquake {
    private String location;
    private double magnitude;
    private long date;
    private String url;

    public Earthquake(String location, double magnitude, long date, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getDate() {
        return date;
    }
}
