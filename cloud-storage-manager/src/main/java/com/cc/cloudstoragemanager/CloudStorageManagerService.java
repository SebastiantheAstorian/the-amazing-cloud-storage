package com.cc.cloudstoragemanager;

import com.cc.cloudstoragemanager.model.ValueObject;
import com.cc.cloudstoragemanager.nodes.NodeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CloudStorageManagerService {

    private NodeManager nodeManager;

    public CloudStorageManagerService(NodeManager nodeManager) {
        this.nodeManager = nodeManager;
    }


    //add value
    @PostMapping("/values")
    public int addValue(@RequestBody ValueObject val){
        int key = val.getKey();

        log.info("Adding new value with key " + key);

        nodeManager.addValue(key, val);

        return key;
    }

    //delete
    @DeleteMapping("/values/{key}")
    public int deleteValue(@PathVariable int key){
        log.info("Deleting value with key " + key);

        nodeManager.deleteValue(key);

        return key;
    }

    //query key
    @GetMapping("/values/{key}")
    public ValueObject queryValueByKey(@PathVariable int key){
        log.info("Querying value with key " + key);

        return nodeManager.getValue(key);
    }

    //range query
    @GetMapping(value="/values", params = {"from", "to"})
    public List<ValueObject> queryValuesInRange(@RequestParam int from, @RequestParam int to){
        log.info("Querying values between keys " + from + " and " + to);

        return nodeManager.getValuesInRange(from, to);
    }
}
