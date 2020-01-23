package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.errorhandling.ValueObjectNotAvailableException;
import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import cc.ass5.storagenodes.cloudstoragenode.serialization.Serialization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    public int addValueObject(ValueObject valueObject){
        serialization.addValueObject(valueObject);
        log.info("Added valueobject with key " + valueObject.getTrip_id());
        return valueObject.getTrip_id();
    }

    public int deleteValueObject(int key){
        try {
            serialization.removeValueObject(key);
        }catch (ValueObjectNotAvailableException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Key not found"
            );
        }
        log.info("Deleted valueobject with key " + key);
        return key;
    }

    public ValueObject getValueObject(int key){
        ValueObject valueObject = serialization.getValueObjectByKey(key);
        log.info("valueobject with key " + key + " retrieved");
        return valueObject;
    }

    public List<ValueObject> getValuesInRange(int key1, int key2){
        List<ValueObject> valueObjectListResult = new ArrayList<>();
        List<ValueObject> valueObjectListAll = serialization.getValueObjectList();

        for (ValueObject v : valueObjectListAll){
            if (v.getTrip_id() >= key1 && v.getTrip_id() <= key2){
                valueObjectListResult.add(v);
            }
        }
        return valueObjectListResult;
    }

}
