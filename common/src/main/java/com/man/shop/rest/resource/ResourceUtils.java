package com.man.shop.rest.resource;

import com.man.shop.rest.exceptions.ResponseEntityPayloadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * Created by smanolache on 4/3/2017.
 */
public class ResourceUtils {
    private static Pattern numericPattern = Pattern.compile("[0-9]+");

    public static URI buildProductResource(String id){
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder
                .fromCurrentRequest();
         builder.removePathExtension();

        return builder.path("/{id}")
                .buildAndExpand(id).toUri();
    }

    public static URI buildProductResource(ResponseEntity<String> entity) throws ResponseEntityPayloadException {
        String content = entity.getBody();

        if (!numericPattern.matcher(content).matches()){
            throw new ResponseEntityPayloadException("The response can not be parsed to Number!");
        }

        return buildProductResource(content);
    }
}
