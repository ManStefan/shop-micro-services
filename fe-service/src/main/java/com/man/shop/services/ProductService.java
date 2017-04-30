package com.man.shop.services;

import com.man.shop.clients.ResourceClient;
import com.man.shop.rest.entites.RestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by smanolache on 4/30/2017.
 */
@RestController
@RequestMapping("/product")
public class ProductService {

    @Autowired
    private ResourceClient resourceClient;

    @RequestMapping(method = RequestMethod.GET, value = "/newest/{n}")
    List<RestProduct> getNewestProducts(@PathVariable(name = "n") Integer n){
        return resourceClient.getNewestProducts(n);
    }
}
