package com.man.shop.rest.resource;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by smanolache on 4/3/2017.
 */
public class ResourceUtils {
    public static URI buildProductResource(String id){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return location;
    }
}
