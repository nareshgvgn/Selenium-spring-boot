package com.selenium.service;

import com.selenium.domain.PropertyMstBean;
import com.selenium.repository.PropertyMstBeanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PropertyMstBean.
 */
@Service
@Transactional
public class PropertyMstBeanService {

    private final Logger log = LoggerFactory.getLogger(PropertyMstBeanService.class);

    private final PropertyMstBeanRepository propertyMstBeanRepository;

    public PropertyMstBeanService(PropertyMstBeanRepository propertyMstBeanRepository) {
        this.propertyMstBeanRepository = propertyMstBeanRepository;
    }

    /**
     * Save a propertyMstBean.
     *
     * @param propertyMstBean the entity to save
     * @return the persisted entity
     */
    public PropertyMstBean save(PropertyMstBean propertyMstBean) {
        log.debug("Request to save PropertyMstBean : {}", propertyMstBean);        return propertyMstBeanRepository.save(propertyMstBean);
    }

    /**
     * Get all the propertyMstBeans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PropertyMstBean> findAll(Pageable pageable) {
        log.debug("Request to get all PropertyMstBeans");
        return propertyMstBeanRepository.findAll(pageable);
    }


    /**
     * Get one propertyMstBean by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PropertyMstBean> findOne(Long id) {
        log.debug("Request to get PropertyMstBean : {}", id);
        return propertyMstBeanRepository.findById(id);
    }

    /**
     * Delete the propertyMstBean by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PropertyMstBean : {}", id);
        propertyMstBeanRepository.deleteById(id);
    }
}
