package com.man.shop.config;

import com.man.shop.clients.SearchClient;
import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import com.man.shop.service.storage.FileSystemStorageService;
import com.man.shop.service.storage.StorageProperties;
import com.man.shop.service.storage.StorageService;
import com.man.shop.utils.PlatformConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @Value("${pictures.storage}")
    private String picturesStorage;

//    @Bean
//    public ObjectsCacheService objectsCacheService(){
//        return new ObjectsCacheService();
//    }
//
//    @Bean
//    public TimeSource timeSource(){
//        return new RealTimeSource();
//    }

    @Bean
    public StorageProperties getStorageProperties(){
        StorageProperties storageProperties = new StorageProperties();
        storageProperties.setLocation(picturesStorage);

        return storageProperties;
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
