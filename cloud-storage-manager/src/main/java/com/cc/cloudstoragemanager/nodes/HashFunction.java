package com.cc.cloudstoragemanager.nodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HashFunction {

    @Value("${nodes.addresses}")
    private String[] nodeAddresses;

    private int getHashValue(int key){
        int n = nodeAddresses.length;
        int hash = key % n;
        return hash;
    }

    public String getAddressByKey(int key){
        if (key < 0) throw new IllegalArgumentException("Key must not be less than 0");
        
        int hash = getHashValue(key);
        String address = nodeAddresses[hash];
        return address;
    }

    public Set<String> getAddressesInRange(int key1, int key2) {
        if (key2 <= key1) throw new IllegalArgumentException("Invalid range: key2 must be greater than key1");

        //using a set to make sure each address is only contained once
        Set<String> addresses = new HashSet<>();

        //TODO: assumption: both key1 and key2 are included in the range - is that correct? (Assignment does not specify this)
        for (int currentKey = key1; currentKey <= key2; ++currentKey) {
            String currentAddress = getAddressByKey(currentKey);
            addresses.add(currentAddress);
        }

        return addresses;
    }
}
