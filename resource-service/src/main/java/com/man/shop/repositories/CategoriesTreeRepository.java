package com.man.shop.repositories;


import com.man.shop.model.CategoriesTree;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface CategoriesTreeRepository extends PagingAndSortingRepository<CategoriesTree, Long> {

    public List<CategoriesTree> findByParentCategoryId(Long parentCategoryId);
}
