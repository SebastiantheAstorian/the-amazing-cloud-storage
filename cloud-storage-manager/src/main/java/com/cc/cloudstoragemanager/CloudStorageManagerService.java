package com.cc.cloudstoragemanager;

import com.cc.cloudstoragemanager.model.ValueObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ManagerService {





    //add value
    @GetMapping("/values")
    public int addValue(@RequestBody ValueObject val){
        int key = val.getKey();

        //hash the key
        //get the IP address of the node associated with the hash
        //send the value to the node

        return key;
    }

    //delete
    @DeleteMapping("/values/{key}")
    public int deleteValue(@PathVariable int key){
        //hash the key
        //get the IP address of the node associated with the hash
        //send the delete request to the node

        //if the node tells us that it could not find the key, return a 404

        return key;
    }

    //query key
    @GetMapping("/values/{key}")
    public ValueObject queryValueByKey(@PathVariable int key){
        return null;
    }

    //range query
    @GetMapping(value="/values", params = {"from", "to"})
    public List<ValueObject> queryValuesInRange(@RequestParam int from, @RequestParam int to){
        return null;
    }

    //logs
    @GetMapping("/logs")
    public String getLogs(){
        return null;
    }
}
