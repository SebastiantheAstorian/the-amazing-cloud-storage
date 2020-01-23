package com.cc.cloudstoragemanager.test;

import com.cc.cloudstoragemanager.model.ValueObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class which does an example run of the application's functionality
 */
@RestController
public class TestService {

    @GetMapping("/test")
    public String getTestLogs() {
        //insert data on nodes
        List<ValueObject> valuesToInsert = new ArrayList<>();
        String testfile = getClass().getClassLoader().getResource("testdata.csv").getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(testfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                valuesToInsert.add(convertToValueObject(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //used to find the relevant Logs
        Instant startTime = Instant.now();


        return "test";
    }

    private ValueObject convertToValueObject(String[] values) {
        return ValueObject.builder()
                .trip_id(Integer.valueOf(values[0]))
                .year(Integer.valueOf(values[1]))
                .month(Integer.valueOf(values[2]))
                .week(Integer.valueOf(values[3]))
                .day(Integer.valueOf(values[4]))
                .hour(Integer.valueOf(values[5]))
                .usertype(values[6])
                .gender(values[7])
                .starttime(values[8])
                .stoptime(values[9])
                .tripduration(Double.valueOf(values[10]))
                .temperature(Double.valueOf(values[11]))
                .events(values[12])
                .from_station_id(Integer.valueOf(values[13]))
                .from_station_name(values[14])
                .latitude_start(Double.valueOf(values[15]))
                .longitude_start(Double.valueOf(values[16]))
                .dpcapacity_start(Double.valueOf(values[17]))
                .to_station_id(Integer.valueOf(values[18]))
                .to_station_name(values[19])
                .latitude_end(Double.valueOf(values[20]))
                .longitude_end(Double.valueOf(values[21]))
                .dpcapacity_end(Double.valueOf(values[22]))
                .build();
    }
}
