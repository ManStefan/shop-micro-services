package com.man.shop.repositories;

import com.man.shop.model.Producer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by smanolache on 12/19/2014.
 */
@Repository
public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {

    public List<Producer> findByNameIgnoreCaseLike(@Param("name") String name, Pageable page);

    public List<Producer> findByNameIgnoreCase(@Param("name") String name);
}
