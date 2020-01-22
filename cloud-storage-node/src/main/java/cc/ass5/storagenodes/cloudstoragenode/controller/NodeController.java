package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
public class NodeController  {

    VOManager voManager = VOManager.getInstance();




    @PostMapping("/values")
    public void addValue(@RequestBody ValueObject valueObject){
        voManager.addValueObject(valueObject);
    }


    @DeleteMapping("/values/{key}")
    public void deleteValue(@PathVariable String key){
        voManager.deleteValueObject(key);

    }

    @GetMapping("/values/{key}")
    public ValueObject getValueByKey(@PathVariable String key){
        return voManager.getValueObject(key);
    }


    @GetMapping(value="/values", params = {"from", "to"})
    public List<ValueObject> getValuesInRange(@RequestParam("from") String key1, @RequestParam("to") String key2){
        return voManager.getValuesInRange(key1, key2);
    }


    @GetMapping("/logs")
    public String getLogs(){
        return null;
    }

}
