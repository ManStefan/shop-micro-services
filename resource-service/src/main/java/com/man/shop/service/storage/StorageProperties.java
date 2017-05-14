package com.man.shop.service.storage;

import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Created by smanolache on 5/13/2017.
 */
public class StorageProperties {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
