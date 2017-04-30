package com.man.shop.model;

import com.man.shop.repositories.*;
import com.man.shop.rest.entites.*;
import com.man.shop.service.ObjectsCacheService;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by smanolache on 3/31/2017.
 */
@Service
public class RestToDAOTransformer {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private CategoryOfProductRepository categoryOfProductRepository;

    @Autowired
    private QuantityStandardRepository quantityStandardRepository;

    private PictureRepository pictureRepository;

    @Autowired
    private ObjectsCacheService objectsCacheService;

    public Product transformProductFromRestToDAO(RestProduct restProduct) throws ParseException {
        Product product = new Product();

        product.setId(restProduct.getId());
        product.setPromo(restProduct.getPromo());
        product.setPromoPercentage(restProduct.getPromoPercentage());
        product.setUnitsSold(restProduct.getUnitsSold());
        product.setDescription(restProduct.getDescription());
        product.setName(restProduct.getName());
        product.setQuantity(restProduct.getQuantity());
        product.setPrice(Money.of(restProduct.getPriceAmount(), restProduct.getPriceCurrency()));
        product.setQuantityStandard(quantityStandardRepository.findOne(restProduct.getQuantityStandard()));

        if (restProduct.getActivationDate() != null) {
            product.setActivationDate(objectsCacheService.getDateFormat(restProduct.getDateFormat()).parse(restProduct.getActivationDate()));
        }
        if (restProduct.getExpireDate() != null) {
            product.setExpireDate(objectsCacheService.getDateFormat(restProduct.getDateFormat()).parse(restProduct.getExpireDate()));
        }

        if (restProduct.getAttributes() != null){
            restProduct.getAttributes().forEach(attributeCat -> {
                List<Attribute> attributeList =  ((List<Map<String, String>>)attributeCat.get(RestProduct.ATTRIBUTES_LABEL))
                        .stream().map(attr -> attributeRepository.findOne(Long.valueOf(attr.get(RestProduct.ID))))
                        .collect(Collectors.toList());

                product.setAttributes(attributeList);
            });
        }

        if (restProduct.getProducer() != null){
            product.setProducer(producerRepository.findOne(restProduct.getProducer()));
        }

        if (restProduct.getCategoryOfProduct() != null){
            product.setCategoryOfProduct(categoryOfProductRepository.findOne(restProduct.getCategoryOfProduct()));
        }

        if (restProduct.getPictures() != null){
            product.setPictures(restProduct.getPictures().stream().map(id -> pictureRepository.findOne(id)).collect(Collectors.toList()));
        }

        return product;
    }

    public RestProduct transformProductFromDAOToRest(Product product){
        RestProduct restProduct = new RestProduct();

        restProduct.setId(product.getId());
        restProduct.setName(product.getName());
        restProduct.setDescription(product.getDescription());
        restProduct.setPromo(product.getPromo());
        restProduct.setPromoPercentage(product.getPromoPercentage());
        restProduct.setUnitsSold(product.getUnitsSold());
        restProduct.setDateFormat(RestProduct.DEFAULT_DATE_FORMAT);

        if (product.getActivationDate() != null) {
            restProduct.setActivationDate(objectsCacheService.getDateFormat(restProduct.getDateFormat()).format(product.getActivationDate()));
        }
        if (product.getExpireDate() != null){
            restProduct.setExpireDate(objectsCacheService.getDateFormat(restProduct.getDateFormat()).format(product.getExpireDate()));
        }

        restProduct.setCategoryOfProduct(product.getCategoryOfProduct().getId());
        restProduct.setCategoryOfProductText(product.getCategoryOfProduct().getName());
        restProduct.setPriceAmount(product.getPrice().getNumber().doubleValue());
        restProduct.setPriceCurrency(product.getPrice().getCurrency().getCurrencyCode());
        restProduct.setProducer(product.getProducer().getId());
        restProduct.setProducerText(product.getProducer().getName());
        restProduct.setQuantityStandard(product.getQuantityStandard().getId());
        restProduct.setQuantityStandardText(product.getQuantityStandard().getName());

        List<Map<String, Object>> catToAttrsList = new ArrayList<>();
        restProduct.setAttributes(catToAttrsList);
        product.getAttributes().forEach(attribute -> {
            //TODO - create the attributes by category structure
        });

        //TODO add the pictures

        return restProduct;

    }

    public CategoryOfAttribute transformCategoryOfAttributeFromRestToDAO(RestCategoryOfAttribute restCategoryOfAttribute){
        CategoryOfAttribute categoryOfAttribute = new CategoryOfAttribute();

        categoryOfAttribute.setId(restCategoryOfAttribute.getId());
        categoryOfAttribute.setName(restCategoryOfAttribute.getName());

        return categoryOfAttribute;
    }

    public Attribute transformAttributeFromRestToDAO(RestAttribute restAttribute){
        Attribute attribute = new Attribute();

        attribute.setId(restAttribute.getId());
        attribute.setName(restAttribute.getName());

        return attribute;
    }

    public CategoryOfProduct transformCategoryOfProductToDAO(RestCategoryOfProduct restCategoryOfProduct){
        CategoryOfProduct categoryOfProduct = new CategoryOfProduct();

        categoryOfProduct.setId(restCategoryOfProduct.getId());
        categoryOfProduct.setName(restCategoryOfProduct.getName());
        categoryOfProduct.setLevel(restCategoryOfProduct.getLevel());
        categoryOfProduct.setDescription(restCategoryOfProduct.getDescription());

        return categoryOfProduct;
    }

    public QuantityStandard tranformQuantityStandardToDAO(RestQuantityStandard restQuantityStandard){
        QuantityStandard quantityStandard = new QuantityStandard();

        quantityStandard.setId(restQuantityStandard.getId());
        quantityStandard.setName(restQuantityStandard.getName());

        return quantityStandard;
    }

    public Producer transformProducerFromRestRoDAO(RestProducer restProducer){
        Producer producer = new Producer();

        producer.setId(restProducer.getId());
        producer.setName(restProducer.getName());

        return producer;
    }
}
