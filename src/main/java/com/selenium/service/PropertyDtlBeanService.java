package com.selenium.service;

import com.selenium.domain.PropertyDtlBean;
import com.selenium.repository.PropertyDtlBeanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PropertyDtlBean.
 */
@Service
@Transactional
public class PropertyDtlBeanService {

    private final Logger log = LoggerFactory.getLogger(PropertyDtlBeanService.class);

    private final PropertyDtlBeanRepository propertyDtlBeanRepository;

    public PropertyDtlBeanService(PropertyDtlBeanRepository propertyDtlBeanRepository) {
        this.propertyDtlBeanRepository = propertyDtlBeanRepository;
    }

    /**
     * Save a propertyDtlBean.
     *
     * @param propertyDtlBean the entity to save
     * @return the persisted entity
     */
    public PropertyDtlBean save(PropertyDtlBean propertyDtlBean) {
        log.debug("Request to save PropertyDtlBean : {}", propertyDtlBean);        return propertyDtlBeanRepository.save(propertyDtlBean);
    }

    /**
     * Get all the propertyDtlBeans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PropertyDtlBean> findAll(Pageable pageable) {
        log.debug("Request to get all PropertyDtlBeans");
        return propertyDtlBeanRepository.findAll(pageable);
    }


    /**
     * Get one propertyDtlBean by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PropertyDtlBean> findOne(Long id) {
        log.debug("Request to get PropertyDtlBean : {}", id);
        return propertyDtlBeanRepository.findById(id);
    }

    /**
     * Delete the propertyDtlBean by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PropertyDtlBean : {}", id);
        propertyDtlBeanRepository.deleteById(id);
    }
}
