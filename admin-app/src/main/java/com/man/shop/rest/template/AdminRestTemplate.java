package com.man.shop.rest.template;

import com.man.shop.config.RestTemplateConfig;
import com.man.shop.rest.entites.RestCategoryOfProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smanolache on 6/5/2017.
 */
@Service
public class AdminRestTemplate {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    protected RestTemplate restTemplate(RestTemplateBuilder builder) {
        this.restTemplate =  builder.build();
        return this.restTemplate;
    }

    public List<RestCategoryOfProduct> callSearchProductCategoryByName(String name, Pageable pageable){

        return Arrays.asList(
                restTemplate.getForObject(
                        restTemplateConfig.getSearchProductCategoryByNameUrl(),
                        RestCategoryOfProduct[].class,
                        name,
                        pageable.getPageNumber(),
                        pageable.getPageSize())
        );
    }

    public void callUpdateProductCategory(RestCategoryOfProduct restCategoryOfProduct){
        restTemplate.put(restTemplateConfig.getUpdateProductCategoryUrl(), restCategoryOfProduct);
    }

    public ResponseEntity<String> callAddProductCategory(RestCategoryOfProduct restCategoryOfProduct){
        return restTemplate.postForEntity(restTemplateConfig.getAddProductCategory(), restCategoryOfProduct, String.class);
    }

    public RestCategoryOfProduct callGetCategoryOfProduct(String id){
        return restTemplate.getForObject(
                restTemplateConfig.getCategoryOfPRoductUrl(),
                RestCategoryOfProduct.class,
                id
        );
    }
}
