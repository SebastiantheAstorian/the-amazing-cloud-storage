package com.cc.cloudstoragemanager;

import com.cc.cloudstoragemanager.model.ValueObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ManagerService {

    @Value("${nodes.addresses}")
    private String[] nodeAddresses;



    //add value
    @GetMapping("/values")
    public void addValue(){

    }

    //delete
    @DeleteMapping("/values/{key}")
    public void deleteValue(){

    }

    //query key
    @GetMapping("/values/{key}")
    public ValueObject queryValueByKey(){
        return null;
    }

    //range query
    @GetMapping(value="/values", params = {"from", "to"})
    public List<ValueObject> queryValuesInRange(){
        return null;
    }

    //logs
    @GetMapping("/logs")
    public String getLogs(){
        return null;
    }
}
