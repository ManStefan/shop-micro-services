package com.man.shop.repositories;

import com.man.shop.model.QuantityStandard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface QuantityStandardRepository extends PagingAndSortingRepository<QuantityStandard, Long> {

    public List<QuantityStandard> findByNameIgnoreCaseLike(@Param("name") String name, Pageable page);

    public List<QuantityStandard> findByNameIgnoreCase(@Param("name") String name);
}
