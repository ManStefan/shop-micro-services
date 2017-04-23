package com.man.shop.config;

import com.man.shop.clients.SearchClient;
import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import com.man.shop.service.ObjectsCacheService;
import com.man.shop.service.RealTimeSource;
import com.man.shop.service.TimeSource;
import com.man.shop.utils.PlatformConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by smanolache on 4/8/2017.
 */
@Configuration
public class ResourceServiceConfiguration {

    @Bean
    public ObjectsCacheService objectsCacheService(){
        return new ObjectsCacheService();
    }

    @Bean
    public TimeSource timeSource(){
        return new RealTimeSource();
    }

    //this is to load a dummy client when unit-testing, because we don't want to fire up all the services
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = PlatformConstants.Environments.Constants.TEST_VALUE, matchIfMissing = false)
    @Bean
    public SearchClient testSearchClient(){
        return new SearchClient() {
            @Override
            public ResponseEntity addProductToSolr(@RequestBody RestProduct restProduct) {
                return  ResponseEntity.created(null).build();
            }

            @Override
            public ResponseEntity deleteAll() {
                return null;
            }

            @Override
            public RestProductFilterResponse filter(@RequestBody RestProductFilterRequest productFilter) {
                return null;
            }

            @Override
            public List<Long> getNewestProducts(@PathVariable Integer n) {
                return null;
            }
        };
    }
}
