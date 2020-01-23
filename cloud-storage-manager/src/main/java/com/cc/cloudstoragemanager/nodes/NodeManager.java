package com.cc.cloudstoragemanager.nodes;

import com.cc.cloudstoragemanager.model.ValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class NodeManager {

    @Autowired
    private HashFunction hashFunction;

    private RestTemplate restTemplate;

    public NodeManager() {
        this.restTemplate = new RestTemplate();
    }


    public void addValue(int key, ValueObject val) {
        String address = hashFunction.getAddressByKey(key);
        address = address + "/values";

        log.info("starting post request to address " + address);
        restTemplate.postForObject(address, val, ValueObject.class);
    }



    public void deleteValue(int key){
        String address = hashFunction.getAddressByKey(key);
        address = address + "/values/" + key;

        log.info("starting delete request to address " + address);
        restTemplate.delete(address);
    }



    public ValueObject getValue(int key) {
        String address = hashFunction.getAddressByKey(key);
        address = address + "/values/" + key;

        log.info("starting get request to address " + address);
        ValueObject val = restTemplate.getForObject(address, ValueObject.class);

        /*ValueObject val = new ValueObject(key, 2000, 1, 3, 22, 14,
                "subscriber", "female", "10:00", "12:00", 120,
                22, "funny events", 2334, "station 2334", 41.939, -87.665, 55,
                3456, "station 3456", 41.547, -87.442, 18);
*/
        return val;
    }



    public List<ValueObject> getValuesInRange(int key1, int key2) {
        //get the addresses of all nodes that might contain values in the specified range
        Set<String> addressesInRange = hashFunction.getAddressesInRange(key1, key2);

        //send a range query to each node, append the result to the overall result
        ArrayList<ValueObject> values = new ArrayList<>();
        for (String address: addressesInRange) {
            address = address + "/values?from=" + key1 + "&to=" + key2;

            log.info("starting get request (range query) to address " + address);


            ValueObject[] currentValues = restTemplate.getForObject(address, ValueObject[].class);

            for (ValueObject val: currentValues) {
                values.add(val);
            }


        }

        return values;
    }
}
