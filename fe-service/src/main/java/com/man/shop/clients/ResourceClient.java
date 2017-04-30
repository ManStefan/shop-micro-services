package com.man.shop.clients;

import com.man.shop.rest.entites.RestProduct;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by smanolache on 4/30/2017.
 */
@FeignClient("resource-service")
public interface ResourceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/product/newest/{n}")
    List<RestProduct> getNewestProducts(@PathVariable(name = "n") Integer n);
}
