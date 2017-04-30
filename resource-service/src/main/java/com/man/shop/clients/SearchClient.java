package com.man.shop.clients;

import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by smanolache on 2/7/2017.
 */
@Conditional(LoadSearchClientCondition.class)
@FeignClient("search-service")
public interface SearchClient {

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    ResponseEntity addProductToSolr(@RequestBody RestProduct restProduct);

    @RequestMapping(method = RequestMethod.DELETE, value = "/product")
    ResponseEntity deleteAll();

    @RequestMapping(method = RequestMethod.POST, value = "/product/filter")
    RestProductFilterResponse filter(@RequestBody RestProductFilterRequest productFilter);

    @RequestMapping(method = RequestMethod.GET, value = "/product/newest/{n}")
    List<Long> getNewestProducts(@PathVariable(name = "n") Integer n);


}
