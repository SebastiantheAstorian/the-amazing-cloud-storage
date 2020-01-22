package cc.ass5.storagenodes.cloudstoragenode.model;


import lombok.Getter;

import java.io.Serializable;


public class ValueObject implements Serializable {

    @Getter private String trip_id;
    @Getter private String year;
    @Getter private String month;
    @Getter private String week;
    @Getter private String day;
    @Getter private String hour;
    @Getter private String usertype;
    @Getter private String gender;
    @Getter private String starttime;
    @Getter private String stoptime;
    @Getter private String tripduration;
    @Getter private String temperature;
    @Getter private String events;
    @Getter private String from_station_id;
    @Getter private String from_station_name;
    @Getter private String latitude_start;
    @Getter private String longitude_start;
    @Getter private String dpcapacity_start;
    @Getter private String to_station_id;
    @Getter private String to_station_name;
    @Getter private String latitude_end;
    @Getter private String longitude_end;
    @Getter private String dpcapacity_end;

    public ValueObject(String trip_id, String year, String month, String week, String day, String hour, String usertype, String gender, String starttime, String stoptime, String tripduration, String temperature, String events, String from_station_id, String from_station_name, String latitude_start, String longitude_start, String dpcapacity_start, String to_station_id, String to_station_name, String latitude_end, String longitude_end, String dpcapacity_end) {
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

    public ValueObject() {
    }
}
