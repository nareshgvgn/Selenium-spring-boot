package com.selenium.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.selenium.domain.PropertyDtlBean;
import com.selenium.service.PropertyDtlBeanService;
import com.selenium.web.rest.errors.BadRequestAlertException;
import com.selenium.web.rest.util.HeaderUtil;
import com.selenium.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PropertyDtlBean.
 */
@RestController
@RequestMapping("/api")
public class PropertyDetailResource {

    private final Logger log = LoggerFactory.getLogger(PropertyDetailResource.class);

    private static final String ENTITY_NAME = "propertyDtlBean";

    private final PropertyDtlBeanService propertyDtlBeanService;

    public PropertyDetailResource(PropertyDtlBeanService propertyDtlBeanService) {
        this.propertyDtlBeanService = propertyDtlBeanService;
    }

    /**
     * POST  /property-dtl-beans : Create a new propertyDtlBean.
     *
     * @param propertyDtlBean the propertyDtlBean to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propertyDtlBean, or with status 400 (Bad Request) if the propertyDtlBean has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/property-dtl-beans")
    @Timed
    public ResponseEntity<PropertyDtlBean> createPropertyDtlBean(@Valid @RequestBody PropertyDtlBean propertyDtlBean) throws URISyntaxException {
        log.debug("REST request to save PropertyDtlBean : {}", propertyDtlBean);
        if (propertyDtlBean.getId() != null) {
            throw new BadRequestAlertException("A new propertyDtlBean cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PropertyDtlBean result = propertyDtlBeanService.save(propertyDtlBean);
        return ResponseEntity.created(new URI("/api/property-dtl-beans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /property-dtl-beans : Updates an existing propertyDtlBean.
     *
     * @param propertyDtlBean the propertyDtlBean to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propertyDtlBean,
     * or with status 400 (Bad Request) if the propertyDtlBean is not valid,
     * or with status 500 (Internal Server Error) if the propertyDtlBean couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/property-dtl-beans")
    @Timed
    public ResponseEntity<PropertyDtlBean> updatePropertyDtlBean(@Valid @RequestBody PropertyDtlBean propertyDtlBean) throws URISyntaxException {
        log.debug("REST request to update PropertyDtlBean : {}", propertyDtlBean);
        if (propertyDtlBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PropertyDtlBean result = propertyDtlBeanService.save(propertyDtlBean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propertyDtlBean.getId().toString()))
            .body(result);
    }

    /**
     * GET  /property-dtl-beans : get all the propertyDtlBeans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of propertyDtlBeans in body
     */
    @GetMapping("/property-dtl-beans")
    @Timed
    public ResponseEntity<List<PropertyDtlBean>> getAllPropertyDtlBeans(Pageable pageable) {
        log.debug("REST request to get a page of PropertyDtlBeans");
        Page<PropertyDtlBean> page = propertyDtlBeanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/property-dtl-beans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /property-dtl-beans/:id : get the "id" propertyDtlBean.
     *
     * @param id the id of the propertyDtlBean to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propertyDtlBean, or with status 404 (Not Found)
     */
    @GetMapping("/property-dtl-beans/{id}")
    @Timed
    public ResponseEntity<PropertyDtlBean> getPropertyDtlBean(@PathVariable Long id) {
        log.debug("REST request to get PropertyDtlBean : {}", id);
        Optional<PropertyDtlBean> propertyDtlBean = propertyDtlBeanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(propertyDtlBean);
    }

    /**
     * DELETE  /property-dtl-beans/:id : delete the "id" propertyDtlBean.
     *
     * @param id the id of the propertyDtlBean to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/property-dtl-beans/{id}")
    @Timed
    public ResponseEntity<Void> deletePropertyDtlBean(@PathVariable Long id) {
        log.debug("REST request to delete PropertyDtlBean : {}", id);
        propertyDtlBeanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
