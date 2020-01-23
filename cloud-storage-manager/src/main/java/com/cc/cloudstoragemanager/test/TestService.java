package com.cc.cloudstoragemanager.test;

import com.cc.cloudstoragemanager.CloudStorageManagerService;
import com.cc.cloudstoragemanager.model.ValueObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Test class which does an example run of the application's functionality
 */
@RestController
@AllArgsConstructor
public class TestService {

    private CloudStorageManagerService cloudStorageManagerService;

    @Value("${nodes.addresses}")
    private String[] nodeAddresses;

    @GetMapping("/test")
    public String getTestLogs() {
        //insert data on nodes
        Set<ValueObject> valuesToInsert = new TreeSet<>(Comparator.comparing(ValueObject::getKey));
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

        // 1. Insert values
        List<Integer> insertedKeys = valuesToInsert.stream()
                .map(val -> cloudStorageManagerService.addValue(val))
                .collect(Collectors.toList());

        String fileStatusLog = getFileStatuses();


        // 2. Access single values
        for (int i = 0; i < 10; i++) {
            cloudStorageManagerService.queryValueByKey(insertedKeys.get(i));
        }

        // 3. Access by range query
        cloudStorageManagerService.queryValuesInRange(insertedKeys.get(3), insertedKeys.get(40));

        // 4. Delete keys
        //delete first 50 keys, and every on a certain node
        List<Integer> deletedKeys = insertedKeys.stream()
                .filter(key -> key < insertedKeys.get(0) + 51 || key % nodeAddresses.length == 1)
                .map(key -> cloudStorageManagerService.deleteValue(key))
                .collect(Collectors.toList());

        String logs = getNodeLogs();

        List<Integer> toCleanKeys = insertedKeys.stream()
                .filter(key -> !deletedKeys.contains(key))
                .map(key -> cloudStorageManagerService.deleteValue(key))
                .collect(Collectors.toList());
        //cleanup(toCleanKeys);
        return fileStatusLog + "\n" + logs;
    }

    private void cleanup(List<Integer> keys) {
        keys.forEach(cloudStorageManagerService::deleteValue);
    }

    private String getFileStatuses() {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder fileStatus = new StringBuilder();
        for (String adress : nodeAddresses) {
            fileStatus.append(restTemplate.getForObject(adress + "/test/file", String.class));
            fileStatus.append("\n\n");
        }

        return fileStatus.toString();
    }

    private String getNodeLogs() {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder logs = new StringBuilder();
        for (String adress : nodeAddresses) {
            logs.append(restTemplate.getForObject(adress + "/logs", String.class));
            logs.append("\n\n");
        }

        return logs.toString();
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
