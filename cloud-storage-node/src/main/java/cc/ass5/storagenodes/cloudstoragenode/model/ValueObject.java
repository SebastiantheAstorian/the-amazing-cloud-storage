package cc.ass5.storagenodes.cloudstoragenode.model;

import lombok.Getter;
import java.io.Serializable;


public class ValueObject implements Serializable {

    @Getter private int trip_id;

    @Getter private int year;
    @Getter private int month;
    @Getter private int week;
    @Getter private int day;
    @Getter private int hour;

    @Getter private String usertype;
    @Getter private String gender;

    @Getter private String starttime;
    @Getter private String stoptime;
    @Getter private double tripduration;

    @Getter private double temperature;
    @Getter private String events;

    @Getter private int     from_station_id;
    @Getter private String  from_station_name;
    @Getter private double  latitude_start;
    @Getter private double  longitude_start;
    @Getter private double  dpcapacity_start;

    @Getter private int to_station_id;
    @Getter private String to_station_name;
    @Getter private double  latitude_end;
    @Getter private double  longitude_end;
    @Getter private double  dpcapacity_end;

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

    public ValueObject() {
    }
}
