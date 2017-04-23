package com.man.shop.model.adaptors;

import com.man.shop.model.solr.SolrProduct;
import com.man.shop.rest.entites.RestProduct;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smanolache on 1/21/2017.
 */
@Service
public class RestToSolrProductAdapter {



    public SolrProduct transformRestToToSolr(RestProduct restProduct) throws ParseException {

        SolrProduct solrProduct = new SolrProduct();

        solrProduct.setId(restProduct.getId().toString());
        solrProduct.setName(restProduct.getName());
        solrProduct.setDescription(restProduct.getDescription());

        if (restProduct.getDateFormat() != null){
            String dateFormatString = restProduct.getDateFormat();
            DateFormat dateFormat = new SimpleDateFormat(dateFormatString);

            if (restProduct.getActivationDate() != null){
                solrProduct.setActivationDate(dateFormat.parse(restProduct.getActivationDate()));
            }
            if (restProduct.getExpireDate() != null){
                solrProduct.setExpireDate(dateFormat.parse(restProduct.getExpireDate()));
            }
        }

        solrProduct.setCategoryOfProduct(restProduct.getCategoryOfProduct());
        solrProduct.setPriceAmount(restProduct.getPriceAmount());
        solrProduct.setPriceCurrency(restProduct.getPriceCurrency());
        solrProduct.setProducer(restProduct.getProducer());
        solrProduct.setPromo(restProduct.getPromo());
        solrProduct.setPromoPercentage(restProduct.getPromoPercentage());
        solrProduct.setQuantity(restProduct.getQuantity());
        solrProduct.setQuantityStandard(restProduct.getQuantityStandard());
        solrProduct.setUnitsSold(restProduct.getUnitsSold());

        if (restProduct.getAttributes() != null){
            List<Long> allAttributesIds = new ArrayList<>();
            Map<String, List<Long>> attributesByCategoryIds = new HashMap<>();

            for (Map<String, Object> attributes : restProduct.getAttributes()){
                Map<String, String> attrCat = (Map<String, String>)attributes.get(RestProduct.ATTRIBUTES_CATEGORY_LABEL);
                List<Map<String, String>> attrsList = (List<Map<String, String>>)attributes.get(RestProduct.ATTRIBUTES_LABEL);

                List<Long> attrIdsPerCat = new ArrayList<>();
                attributesByCategoryIds.put("cat_of_attr_" + attrCat.get(RestProduct.ID) + "_ls", attrIdsPerCat);

                for (Map<String, String> attr : attrsList){
                    allAttributesIds.add(Long.valueOf(attr.get(RestProduct.ID)));
                    attrIdsPerCat.add(Long.valueOf(attr.get(RestProduct.ID)));
                }
            }
            solrProduct.setAttributes(allAttributesIds);
            solrProduct.setAttributes_t(attributesByCategoryIds);
        }

        return solrProduct;
    }
}
