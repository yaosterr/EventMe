package com.example.eventme;

import java.io.Serializable;

public class Event implements Serializable {
    String event;
    String location;
    String dateAndTime;
    String organization;
    public Event(String event, String location, String dateAndTime, String organization){
        this.event = event;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.organization = organization;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
