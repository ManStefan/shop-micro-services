//package com.man.shop.service.solr.impl;
//
//
//import com.man.shop.filters.model.ProductFilterRequest;
//import com.man.shop.model.solr.SolrProduct;
//import com.man.shop.repositories.ProductSolrRepository;
//import com.man.shop.service.solr.SolrProductServiceI;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.solr.core.query.result.FacetPage;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Created by smanolache on 1/4/2015.
// */
//@Service
//public class SolrProductService implements SolrProductServiceI {
//
//    @Autowired
//    private ProductSolrRepository productSolrRepository;
//
//    @Override
//    public Iterable<SolrProduct> findAll() {
//        return productSolrRepository.findAll();
//    }
//
//    @Override
//    public SolrProduct findOne(String id) {
//        return productSolrRepository.findOne(id);
//    }
//
//    @Override
//    public SolrProduct save(SolrProduct solrProduct) {
//        return productSolrRepository.save(solrProduct);
//    }
//
//    @Override
//    public FacetPage<SolrProduct> getFacetsForProduct(ProductFilterRequest productFilter, Pageable page) {
//        return productSolrRepository.getFacetsForProduct(productFilter, page);
//    }
//
//    @Override
//    public Page<SolrProduct> getLatestProducts(int n) {
//        return productSolrRepository.getLatestProducts(n);
//    }
//
//    @Override
//    public Page<SolrProduct> getTopProductsBySales(int n) {
//        return null;
//    }
//
//    @Override
//    public boolean categoryHasProducts(long categoryId) {
//        Pageable page = new PageRequest(0,1);
//
//        List<SolrProduct> solrProducts = productSolrRepository.findByCategoryOfProduct(categoryId, page);
//
//        if (solrProducts.isEmpty()){
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public List<SolrProduct> findProductsForCategory(long categoryId, Pageable page) {
//        return productSolrRepository.findByCategoryOfProduct(categoryId, page);
//    }
//
//    @Override
//    public boolean attributeHasProducts(long attributeId) {
//        Pageable page = new PageRequest(0,1);
//
//        List<SolrProduct> solrProducts = productSolrRepository.findByAttributes(attributeId, page);
//
//        if (solrProducts.isEmpty()){
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean producerHasProducts(long producerId) {
//        Pageable page = new PageRequest(0,1);
//
//        List<SolrProduct> solrProducts = productSolrRepository.findByProducer(producerId, page);
//
//        if (solrProducts.isEmpty()){
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean unitOfMeasurementHasProducts(long quantityStandard) {
//        Pageable page = new PageRequest(0,1);
//
//        List<SolrProduct> solrProducts = productSolrRepository.findByQuantityStandard(quantityStandard, page);
//
//        if (solrProducts.isEmpty()){
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public List<SolrProduct> findByApproxNameAndCategory(String name, List<Long> categoriesOfProduct, Pageable page) {
//        ProductFilterRequest filter = new ProductFilterRequest();
//
//        filter.setSearchWord(name);
//        filter.setCategoriesOfProduct(categoriesOfProduct);
//
//        FacetPage<SolrProduct> facetPage = productSolrRepository.getFacetsForProduct(filter, page);
//
//        return facetPage.getContent();
//    }
//
//    @Override
//    public List<SolrProduct> findByApproxName(String name, Pageable page) {
//        ProductFilterRequest filter = new ProductFilterRequest();
//
//        filter.setSearchWord(name);
//        FacetPage<SolrProduct> facetPage = productSolrRepository.getFacetsForProduct(filter, page);
//
//        return facetPage.getContent();
//    }
//
//    @Override
//    public List<SolrProduct> findByNameIgnoreCase(String name) {
////        return productSolrRepository.findByNameIgnoreCase(name);
//        return null;
//    }
//
//    @Override
//    public List<SolrProduct> getTopProductsByPromoPercentage(int numberOfProducts) {
//        Pageable page = new PageRequest(0, numberOfProducts);
//
//        return productSolrRepository.findByPromoOrderByPromoPercentageDesc(true, page);
//    }
//
//
//}
