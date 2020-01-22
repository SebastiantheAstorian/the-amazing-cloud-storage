package cc.ass5.storagenodes.cloudstoragenode.serialization;


import cc.ass5.storagenodes.cloudstoragenode.errorhandling.ValueObjectNotAvailableException;
import cc.ass5.storagenodes.cloudstoragenode.model.ValueObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization implements Serializable {

    private static final long serialVersionUID = 1L;
    private String file;
    List<ValueObject> valueObjectList;
    ObjectInputStream is;
    ObjectOutputStream os;

    public Serialization(String file){
        this.file = file;
        try{
            is = new ObjectInputStream(new FileInputStream(file));
            valueObjectList = (ArrayList<ValueObject>) is.readObject();
            is.close();
        }catch(Exception fx){
            valueObjectList = new ArrayList<ValueObject>();
            try{
                os = new ObjectOutputStream(new FileOutputStream(file));
                os.writeObject(valueObjectList);
                os.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public ValueObject addValueObject(ValueObject valueObject){
        try{
            os = new ObjectOutputStream(new FileOutputStream(file));
            valueObjectList.add(valueObject);
            os.writeObject(valueObjectList);
            os.close();
            return valueObject;
        }catch(IOException iox){
            iox.printStackTrace();
        }
        return null;

    }

    public List<ValueObject> getValueObjectList(){
        try {
            is = new ObjectInputStream(new FileInputStream(this.file));
            List<ValueObject> valueObjectList = (ArrayList<ValueObject>) is.readObject();
            is.close();
            return valueObjectList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("classcastexeption");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Datei nicht gefunden - Programm beendet");
            return null;
        }
    }

    public ValueObject removeValueObject(int key) {
        try{
            ValueObject valueObject = getValueObjectByKey(key);
            os = new ObjectOutputStream(new FileOutputStream(file));
            for (int i = 0; i < valueObjectList.size(); i++){
                if (valueObjectList.get(i).getTrip_id() == key){
                    valueObject = valueObjectList.get(i);
                    valueObjectList.remove(i);
                }
            }
            os.writeObject(valueObjectList);
            os.close();
            return valueObject;
        }catch(IOException iox){
            iox.printStackTrace();
        }
        return null;
    }

    public ValueObject getValueObjectByKey(int key) {
        List<ValueObject> valueObjectList = getValueObjectList();
        for (ValueObject v : valueObjectList){
            if (v.getTrip_id() == key){
                return v;
            }
        }
        throw new ValueObjectNotAvailableException(key);
    }

}
