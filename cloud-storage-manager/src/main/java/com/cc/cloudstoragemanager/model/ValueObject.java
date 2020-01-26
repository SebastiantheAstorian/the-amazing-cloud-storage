package com.cc.cloudstoragemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * the objects that can be stored in the cloud storage system
 */
@Value
@Builder(builderClassName = "Builder", toBuilder = true)
public class ValueObject {
    //field names are not in camel case for the sake of conformity with the input CSV file

    private int trip_id;

    private int year;
    private int month;
    private int week;



    private int day;
    private int hour;

    private String usertype;
    private String gender;

    private String starttime;
    private String stoptime;
    private double tripduration;

    private double temperature;
    private String events;

    private int     from_station_id;
    private String  from_station_name;
    private double  latitude_start;
    private double  longitude_start;
    private double  dpcapacity_start; //this is a decimal in the input file (although conceptually it should be an integer...?)

    private int to_station_id;
    private String to_station_name;
    private double  latitude_end;
    private double  longitude_end;
    private double  dpcapacity_end;


    @ConstructorProperties({"trip_id", "year", "month", "week", "day", "hour", "usertype", "gender", "starttime", "stoptime", "tripduration", "temperature", "events", "from_station_id", "from_station_name", "latitude_start", "longitude_start", "dpcapacity_start", "to_station_id", "to_station_name", "latitude_end", "longitude_end", "dpcapacity_end"})
    public ValueObject(int trip_id, int year, int month, int week, int day, int hour, String usertype, String gender, String starttime, String stoptime, double tripduration, double temperature, String events, int from_station_id, String from_station_name, double latitude_start, double longitude_start, double dpcapacity_start, int to_station_id, String to_station_name, double latitude_end, double longitude_end, double dpcapacity_end) {
        this.trip_id = trip_id;
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.hour = hour;
        this.usertype = usertype;
        this.gender = gender;
        this.starttime = starttime;
        this.stoptime = stoptime;
        this.tripduration = tripduration;
        this.temperature = temperature;
        this.events = events;
        this.from_station_id = from_station_id;
        this.from_station_name = from_station_name;
        this.latitude_start = latitude_start;
        this.longitude_start = longitude_start;
        this.dpcapacity_start = dpcapacity_start;
        this.to_station_id = to_station_id;
        this.to_station_name = to_station_name;
        this.latitude_end = latitude_end;
        this.longitude_end = longitude_end;
        this.dpcapacity_end = dpcapacity_end;
    }

    /**
     * Explicit getter for the field that is used as the key
     * @return ID of the trip
     */
    @JsonIgnore
    public int getKey(){return trip_id;};
}
