package com.man.shop.service.rest;

import com.man.shop.model.CategoryOfProduct;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.CategoryOfProductRepository;
import com.man.shop.rest.entites.RestCategoryOfProduct;
import com.man.shop.rest.exceptions.CategoryOfProductException;
import com.man.shop.rest.resource.ResourceUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smanolache on 4/6/2017.
 */
@Transactional
@RestController
@RequestMapping("/category_of_product")
public class CategoryOfProductService {

    private static final Logger logger = Logger.getLogger(CategoryOfProductService.class);

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @Autowired
    private CategoryOfProductRepository categoryOfProductRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addCategoryOfProduct(@RequestBody RestCategoryOfProduct restCategoryOfProduct) throws CategoryOfProductException {

        if (restCategoryOfProduct.getName() == null || restCategoryOfProduct.getName().isEmpty()){
            String errorMsg = "The name of the category of product can not be blank!";
            logger.error(errorMsg);
            throw new CategoryOfProductException(errorMsg);
        }

        CategoryOfProduct categoryOfProduct = categoryOfProductRepository.save(restToDAOTransformer.transformCategoryOfProductFromRestToDAO(restCategoryOfProduct));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryOfProduct.getId().toString());
    }

    @GetMapping("/name/{name}")
    List<RestCategoryOfProduct> searchCategoryOfProductByName(@PathVariable String name, Pageable pageable){
        return categoryOfProductRepository.findByNameIgnoreCaseLike("%"+ name + "%", pageable)
                .stream()
                .map(categoryOfProduct -> restToDAOTransformer.transformCategoryOfProductFromDAOtoRest(categoryOfProduct))
                .collect(Collectors.toList());

    }

    @GetMapping("/id/{id}")
    RestCategoryOfProduct getCategoryOfProductById(@PathVariable Long id){
        return restToDAOTransformer.transformCategoryOfProductFromDAOtoRest(categoryOfProductRepository.findOne(id));
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void updateCategoryOfProduct(@RequestBody RestCategoryOfProduct restCategoryOfProduct) throws CategoryOfProductException {
        if (restCategoryOfProduct.getId() == null){
            String errorMsg = "You must specify the id of the category that you want to modify!";
            logger.error(errorMsg);
            throw new CategoryOfProductException(errorMsg);
        }

        CategoryOfProduct categoryOfProduct = categoryOfProductRepository.findOne(restCategoryOfProduct.getId());

        if (categoryOfProduct == null){
            String errorMsg = "The product category with id " + restCategoryOfProduct.getId() + " can't be found!";
            logger.error(errorMsg);
            throw new CategoryOfProductException(errorMsg);
        }

        if (restCategoryOfProduct.getName() == null || restCategoryOfProduct.getName().isEmpty()){
            String errorMsg = "The name of the category must not be empty!";
            logger.error(errorMsg);
            throw new CategoryOfProductException(errorMsg);
        }

        if (!restCategoryOfProduct.getName().equals(categoryOfProduct.getName())) {
            if (categoryOfProductRepository.countByNameIgnoreCase(restCategoryOfProduct.getName()) > 0) {
                String errorMsg = "There is already another category with the same name!";
                logger.error(errorMsg);
                throw new CategoryOfProductException(errorMsg);
            }

            categoryOfProduct.setName(restCategoryOfProduct.getName());
        }

        //TODO - update parent category

        categoryOfProduct.setDescription(restCategoryOfProduct.getDescription());

        categoryOfProductRepository.save(categoryOfProduct);
    }
}
