package com.man.shop.clients;

import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by smanolache on 2/7/2017.
 */
@FeignClient("search-service")
public interface SearchClient {

    @RequestMapping(method = RequestMethod.POST, value = "/product/filter")
    RestProductFilterResponse filter(@RequestBody RestProductFilterRequest productFilter);
}
