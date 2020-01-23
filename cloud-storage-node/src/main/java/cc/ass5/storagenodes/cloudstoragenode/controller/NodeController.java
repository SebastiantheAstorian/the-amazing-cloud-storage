package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NodeController  {

    VOManager voManager = VOManager.getInstance();

    @PostMapping("/values")
    public int addValue(@RequestBody ValueObject valueObject){
        return voManager.addValueObject(valueObject);

    }

    @DeleteMapping("/values/{key}")
    public int deleteValue(@PathVariable int key){
        voManager.deleteValueObject(key);
        return key;
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
