package cc.ass5.storagenodes.cloudstoragenode.controller;

import cc.ass5.storagenodes.cloudstoragenode.errorhandling.ValueObjectAlreadyExistException;
import cc.ass5.storagenodes.cloudstoragenode.errorhandling.ValueObjectNotAvailableException;
import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;
import cc.ass5.storagenodes.cloudstoragenode.serialization.Serialization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VOManager {

    private int myPort;

    private static VOManager instance;
    private Serialization serialization;

    /**
     * Classical Singleton implementation
     */
    public static VOManager getInstance(int myPort) {
        if (VOManager.instance == null) {
            VOManager.instance = new VOManager(myPort);
        }
        return VOManager.instance;
    }

    private VOManager(int myPort) {
        this.myPort = myPort;
        serialization = new Serialization("storage.ser");
    }

    public int addValueObject(ValueObject valueObject) {
        try {
            serialization.addValueObject(valueObject);
            LogFileWriter.logInfo(Instant.now(), "Added valueobject with key " + valueObject.getTrip_id() + " on node on port " + myPort + " in file storage.ser");
            return valueObject.getTrip_id();
        } catch (ValueObjectAlreadyExistException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Key already exists"
            );
        }
    }

    public int deleteValueObject(int key) {
        try {
            serialization.removeValueObject(key);
        } catch (ValueObjectNotAvailableException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Key not found"
            );
        }
        LogFileWriter.logInfo(Instant.now(), "Deleted valueobject with key " + key + " on node on port " + myPort + " in file storage.ser");
        return key;
    }

    public ValueObject getValueObject(int key) {
        ValueObject valueObject = serialization.getValueObjectByKey(key);
        LogFileWriter.logInfo(Instant.now(), "valueobject with key " + key + " retrieved" + " on node on port " + myPort + " in file storage.ser");
        return valueObject;
    }

    public List<ValueObject> getValuesInRange(int key1, int key2) {
        List<ValueObject> valueObjectListResult = new ArrayList<>();
        List<ValueObject> valueObjectListAll = serialization.getValueObjectList();

        for (ValueObject v : valueObjectListAll) {
            if (v.getTrip_id() >= key1 && v.getTrip_id() <= key2) {
                valueObjectListResult.add(v);
            }
        }
        return valueObjectListResult;
    }

    //return String describing current file status
    public String getFileStatus() {
        try {
            return "Node on port " + String.valueOf(myPort) + "; File storage.ser:"+ "xxx\nxxx" + new ObjectMapper().writeValueAsString(serialization.getValueObjectList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
