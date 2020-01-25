package cc.ass5.storagenodes.cloudstoragenode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
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

}
