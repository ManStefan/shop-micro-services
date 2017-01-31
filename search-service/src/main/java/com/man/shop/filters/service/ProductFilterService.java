package com.man.shop.filters.service;

import com.man.shop.filters.model.ProductFilterRequest;
import com.man.shop.filters.model.ProductFilterResponse;
import com.man.shop.model.solr.SolrProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by smanolache on 1/8/2015.
 */
@Service
public class ProductFilterService {

    public static final String FACETED_CAT_OF_PRODUCT_LABEL = "categoryOfProduct";
    public static final String FACETED_PRODUCER_LABEL = "producer";
    public static final String FACETED_ATTRIBUTES_HEADER_LABEL = "cat_of_attr_";
    public static final String FACETED_ATTRIBUTES_TRAILER_LABEL = "_ls";

    public FacetQuery createFacetQuery(ProductFilterRequest productFilter) {
        FacetQuery facetQuery = new SimpleFacetQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD));

        FacetOptions facetOptions = new FacetOptions();

        //add the filter for the active/inactive products
        addFilterForActiveProducts(productFilter, facetQuery);

        //add filter for the search string
        if (productFilter.getSearchWord() != null && !productFilter.getSearchWord().trim().isEmpty()){
            String [] searchWordsString = getTokensFromSearchWord(productFilter.getSearchWord().trim());

            Criteria searchWord = new Criteria("name").contains(searchWordsString[0]);
            searchWord = searchWord.or(new Criteria("description").contains(searchWordsString[0]));
            for (int i = 1; i < searchWordsString.length; i++ ) {
                String searchWordString = searchWordsString[i];
                if (!searchWordString.isEmpty()) {
                    searchWord = searchWord.or(new Criteria("name").contains(searchWordString));
                    searchWord = searchWord.or(new Criteria("description").contains(searchWordString));
                }
            }

            FilterQuery searchWordFilter = new SimpleFilterQuery();
            searchWordFilter.addCriteria(searchWord);

            facetQuery.addFilterQuery(searchWordFilter);
        }

        //for the category of product
        FilterQuery filterQueryProductCat = new SimpleFilterQuery();
        addFacetsAndFilters(facetOptions, filterQueryProductCat, productFilter.getCategoriesOfProduct(), FACETED_CAT_OF_PRODUCT_LABEL, "cat");

        //for the producers
        FilterQuery filterQueryProducer = new SimpleFilterQuery();
        addFacetsAndFilters(facetOptions, filterQueryProducer, productFilter.getProducers(), FACETED_PRODUCER_LABEL, "pr");

        for (Long categoryOfAttributeId : productFilter.getFacetedCategoriesOfAttributes()){

            FilterQuery filterQueryAttrs = new SimpleFilterQuery();
            addFacetsAndFilters(facetOptions, filterQueryAttrs, productFilter.getAttributes().get(categoryOfAttributeId), FACETED_ATTRIBUTES_HEADER_LABEL + categoryOfAttributeId + FACETED_ATTRIBUTES_TRAILER_LABEL, "attrs" + categoryOfAttributeId);

            if (filterQueryAttrs.getCriteria() != null){
                facetQuery.addFilterQuery(filterQueryAttrs);
            }
        }

        facetQuery.setFacetOptions(facetOptions);

        if (filterQueryProductCat.getCriteria() != null) {
            facetQuery.addFilterQuery(filterQueryProductCat);
        }
        if (filterQueryProducer.getCriteria() != null) {
            facetQuery.addFilterQuery(filterQueryProducer);
        }

        int page = productFilter.getPageNumber() != null ? productFilter.getPageNumber() : 0;
        int size = productFilter.getPageSize() != null ? productFilter.getPageSize() : 10;
        Pageable pageable = new PageRequest(page, size);
        facetQuery.setPageRequest(pageable);

        //add the sort - if present
        if (productFilter.getSort() != null && !productFilter.getSort().isEmpty()){
            for (String sortTerm : productFilter.getSort().keySet()){
                String sortOrder = productFilter.getSort().get(sortTerm);
                facetQuery.addSort(new Sort(Sort.Direction.fromString(sortOrder), sortTerm));
            }
        }

        //TODO - add filter for price, etc

        return facetQuery;
    }

    public ProductFilterResponse extractResponseFromFacets(FacetPage<SolrProduct> facets, ProductFilterRequest productFilterRequest){
        ProductFilterResponse filterResponse = new ProductFilterResponse();
        filterResponse.setFilterRequest(productFilterRequest);

        //add the filtered products
        List<Long> products = new ArrayList<>();
        filterResponse.setProducts(products);
        for (SolrProduct solrProduct : facets.getContent()){
            products.add(Long.valueOf(solrProduct.getId()));
        }

        //for producers
        filterResponse.setFacetedProducers(getFacetsCount(facets.getFacetResultPage(FACETED_PRODUCER_LABEL)));

        //for product category
        filterResponse.setFacetedProductCategories(getFacetsCount(facets.getFacetResultPage(FACETED_CAT_OF_PRODUCT_LABEL)));

        //for attributes
        Map<Long, Map<Long, Long>> atributesFacetsCount = new HashMap<>();
        filterResponse.setFacetedAttributes(atributesFacetsCount);
        for (Field facetField : facets.getFacetFields()) {
            String facetFieldName = facetField.getName();
            if (facetFieldName.startsWith(FACETED_ATTRIBUTES_HEADER_LABEL)){
                Page<FacetFieldEntry> attributesFacets = facets.getFacetResultPage(facetField);

                long attrCatId = Long.valueOf(facetFieldName.substring(FACETED_ATTRIBUTES_HEADER_LABEL.length(), facetFieldName.length() - FACETED_ATTRIBUTES_TRAILER_LABEL.length()));
                atributesFacetsCount.put(attrCatId, getFacetsCount(attributesFacets));
            }
        }

        return filterResponse;
    }

    private Map<Long, Long> getFacetsCount(Page<FacetFieldEntry> facetsPage){
        Map<Long, Long> facets = new HashMap<>();
        for (FacetFieldEntry facetFieldEntry : facetsPage.getContent()){
            facets.put(Long.valueOf(facetFieldEntry.getValue()), facetFieldEntry.getValueCount());
        }

        return facets;
    }

    private void addFilterForActiveProducts(ProductFilterRequest productFilter, FacetQuery facetQuery){
        Date now = new Date();

        //if we want all products, don't add any additional filter
        if (productFilter.getActive() && productFilter.getInactive()){
            return;
        }

        //if we want just the active ones
        if (productFilter.getActive() && !productFilter.getInactive()){

            Criteria activeCriteria = new Criteria("activationDate").lessThanEqual(now);
            activeCriteria = activeCriteria.and(new Criteria("expireDate").greaterThan(now));

            FilterQuery onlyActiveFilter = new SimpleFilterQuery();
            onlyActiveFilter.addCriteria(activeCriteria);

            facetQuery.addFilterQuery(onlyActiveFilter);
        }

        //if we want just the inactive ones
        if (!productFilter.getActive() && productFilter.getInactive()){
            Criteria inactiveCriteria = new Criteria("activationDate").greaterThan(now);
            inactiveCriteria = inactiveCriteria.and(new Criteria("expireDate").lessThanEqual(now));
            FilterQuery onlyInActiveFilter = new SimpleFilterQuery();
            onlyInActiveFilter.addCriteria(inactiveCriteria);

            facetQuery.addFilterQuery(onlyInActiveFilter);
        }
    }

    private void addFacetsAndFilters(FacetOptions facetOptions, FilterQuery filterQuery, List<Long> criteriaIds, String fqName, String tagName){
        if (criteriaIds != null && !criteriaIds.isEmpty()){
            facetOptions.addFacetOnField("{!ex=" + tagName + "}"+fqName);

            Criteria criteria = new Criteria("{!tag=" + tagName + "}" + fqName).is(criteriaIds.get(0));
            if (criteriaIds.size() > 1){
                for (int i = 1; i < criteriaIds.size(); i++){
                    //criteria = criteria.or(new Criteria("{!tag=" + tagName + "}" + fqName).is(criteriaIds.get(i)));
                    criteria = criteria.or(new Criteria(fqName).is(criteriaIds.get(i)));
                }
            }

            filterQuery.addCriteria(criteria);
        } else {
            facetOptions.addFacetOnField(fqName);
            //facetOptions.addFacetOnField("{!ex=" + tagName + "}"+fqName);
        }
    }

    private String[] getTokensFromSearchWord(String searchWord){
        return searchWord.split("\\W");
    }
}
