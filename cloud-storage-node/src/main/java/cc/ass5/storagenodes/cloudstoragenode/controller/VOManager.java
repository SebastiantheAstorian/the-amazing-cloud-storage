package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import cc.ass5.storagenodes.cloudstoragenode.serialization.Serialization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VOManager {

    private static VOManager instance;
    private Serialization serialization;
    /**
     * Classical Singleton implementation
     */
    public static VOManager getInstance () {
        if (VOManager.instance == null) {
            VOManager.instance = new VOManager();
        }
        return VOManager.instance;
    }

    private VOManager(){
        serialization = new Serialization("storage.ser");
    }

    public void addValueObject(ValueObject valueObject){
        serialization.addValueObject(valueObject);
        log.info("Added valueobject with key " + valueObject.getTrip_id());
    }

    public void deleteValueObject(String key){
        serialization.removeValueObject(key);
        log.info("Deleted valueobject with key " + key);
    }

    public ValueObject getValueObject(String key){
        ValueObject valueObject = serialization.getValueObjectByKey(key);
        log.info("valueobject with key " + key + " retrieved");
        return valueObject;
    }

    public List<ValueObject> getValuesInRange(String key1, String key2){
        List<ValueObject> valueObjectListResult = new ArrayList<>();
        List<ValueObject> valueObjectListAll = serialization.getValueObjectList();
        int key1Integer = Integer.parseInt(key1);
        int key2Integer = Integer.parseInt(key2);

        for (ValueObject v : valueObjectListAll){
            int currentKey = Integer.parseInt(v.getTrip_id());
            if (currentKey >= key1Integer && currentKey <= key2Integer){
                valueObjectListResult.add(v);
            }
        }
        return valueObjectListResult;
    }

}
