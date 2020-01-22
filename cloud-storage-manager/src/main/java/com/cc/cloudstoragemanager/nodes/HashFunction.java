package com.cc.cloudstoragemanager.nodes;

import org.springframework.beans.factory.annotation.Value;

public class HashFunction {

    @Value("${nodes.addresses}")
    private String[] nodeAddresses;

    private int getHashValue(int key){
        int n = nodeAddresses.length;
        return key % n;
    }

    public String getAddressByKey(int key){
        int hash = getHashValue(key);
        String address = nodeAddresses[hash];
        return address;
    }
}
