package com.cc.cloudstoragemanager.model;

import lombok.Value;

import java.time.Instant;

/**
 * the objects that can be stored in the cloud storage system
 */
@Value
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
    private int to_station_name;
    private double  latitude_end;
    private double  longitude_end;
    private double  dpcapacity_end;

    /**
     * Explicit getter for the field that is used as the key
     * @return ID of the trip
     */
    public int getKey(){return trip_id;};
}
