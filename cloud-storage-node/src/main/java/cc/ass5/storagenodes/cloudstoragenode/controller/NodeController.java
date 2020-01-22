package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NodeController  {

    VOManager voManager = VOManager.getInstance();

    @PostMapping("/values")
    public void addValue(@RequestBody ValueObject valueObject){
        voManager.addValueObject(valueObject);
    }


    @DeleteMapping("/values/{key}")
    public void deleteValue(@PathVariable int key){
        voManager.deleteValueObject(key);

    }

    @GetMapping("/values/{key}")
    public ValueObject getValueByKey(@PathVariable int key){
        return voManager.getValueObject(key);
    }


    @GetMapping(value="/values", params = {"from", "to"})
    public List<ValueObject> getValuesInRange(@RequestParam("from") int key1, @RequestParam("to") int key2){
        return voManager.getValuesInRange(key1, key2);
    }


    @GetMapping("/logs")
    public String getLogs(){
        return null;
    }

}
