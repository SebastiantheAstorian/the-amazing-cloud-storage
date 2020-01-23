package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;

@RestController
public class NodeController {


    @Value("${server.port}")
    int myPort;

    VOManager voManager;

    @PostConstruct
    public void logStartup() {
        LogFileWriter.logInfo(Instant.now(), "Started node on port " + myPort);
        voManager = VOManager.getInstance(myPort);
    }

    @PostMapping("/values")
    public int addValue(@RequestBody ValueObject valueObject) {
        return voManager.addValueObject(valueObject);

    }

    @DeleteMapping("/values/{key}")
    public int deleteValue(@PathVariable int key) {
        voManager.deleteValueObject(key);
        return key;
    }

    @GetMapping("/values/{key}")
    public ValueObject getValueByKey(@PathVariable int key) {
        return voManager.getValueObject(key);
    }


    @GetMapping(value = "/values", params = {"from", "to"})
    public List<ValueObject> getValuesInRange(@RequestParam("from") int key1, @RequestParam("to") int key2) {
        return voManager.getValuesInRange(key1, key2);
    }


    @GetMapping("/logs")
    public String getLogs() {
        return LogFileWriter.getLogs();
    }

    @GetMapping("/test/file")
    public String getFileStatus() {
        return voManager.getFileStatus();
    }
}
