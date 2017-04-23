package com.man.shop.aop;

import com.man.shop.model.BasicEntity;
import com.man.shop.service.TimeSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smanolache on 4/9/2017.
 */
@Aspect
@ComponentScan
public class AuditEntityAspect {

    @Autowired
    private TimeSource timeSource;

    @Pointcut("execution(* org.springframework.data.repository.*.save(..)) && args(entity)")
    public void saveOperation(BasicEntity entity){}

    @Before("saveOperation(entity)")
    @Transactional
    public void beforeSave(BasicEntity entity) {
        // TODO - add the user when I'll add spring security
//        SecurityProperties.User user = (SecurityProperties.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (entity.getId()  == null){
            entity.setCreationDate(timeSource.now());

        } else {
            entity.setModifiedDate(timeSource.now());

        }
    }

}
