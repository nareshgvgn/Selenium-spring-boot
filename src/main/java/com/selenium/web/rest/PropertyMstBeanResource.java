package com.selenium.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.selenium.domain.PropertyMstBean;
import com.selenium.service.PropertyMstBeanService;
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
 * REST controller for managing PropertyMstBean.
 */
@RestController
@RequestMapping("/api")
public class PropertyMstBeanResource {

    private final Logger log = LoggerFactory.getLogger(PropertyMstBeanResource.class);

    private static final String ENTITY_NAME = "propertyMstBean";

    private final PropertyMstBeanService propertyMstBeanService;

    public PropertyMstBeanResource(PropertyMstBeanService propertyMstBeanService) {
        this.propertyMstBeanService = propertyMstBeanService;
    }

    /**
     * POST  /property-mst-beans : Create a new propertyMstBean.
     *
     * @param propertyMstBean the propertyMstBean to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propertyMstBean, or with status 400 (Bad Request) if the propertyMstBean has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/property-mst-beans")
    @Timed
    public ResponseEntity<PropertyMstBean> createPropertyMstBean(@Valid @RequestBody PropertyMstBean propertyMstBean) throws URISyntaxException {
        log.debug("REST request to save PropertyMstBean : {}", propertyMstBean);
        if (propertyMstBean.getId() != null) {
            throw new BadRequestAlertException("A new propertyMstBean cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PropertyMstBean result = propertyMstBeanService.save(propertyMstBean);
        return ResponseEntity.created(new URI("/api/property-mst-beans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /property-mst-beans : Updates an existing propertyMstBean.
     *
     * @param propertyMstBean the propertyMstBean to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propertyMstBean,
     * or with status 400 (Bad Request) if the propertyMstBean is not valid,
     * or with status 500 (Internal Server Error) if the propertyMstBean couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/property-mst-beans")
    @Timed
    public ResponseEntity<PropertyMstBean> updatePropertyMstBean(@Valid @RequestBody PropertyMstBean propertyMstBean) throws URISyntaxException {
        log.debug("REST request to update PropertyMstBean : {}", propertyMstBean);
        if (propertyMstBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PropertyMstBean result = propertyMstBeanService.save(propertyMstBean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propertyMstBean.getId().toString()))
            .body(result);
    }

    /**
     * GET  /property-mst-beans : get all the propertyMstBeans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of propertyMstBeans in body
     */
    @GetMapping("/property-mst-beans")
    @Timed
    public ResponseEntity<List<PropertyMstBean>> getAllPropertyMstBeans(Pageable pageable) {
        log.debug("REST request to get a page of PropertyMstBeans");
        Page<PropertyMstBean> page = propertyMstBeanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/property-mst-beans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /property-mst-beans/:id : get the "id" propertyMstBean.
     *
     * @param id the id of the propertyMstBean to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propertyMstBean, or with status 404 (Not Found)
     */
    @GetMapping("/property-mst-beans/{id}")
    @Timed
    public ResponseEntity<PropertyMstBean> getPropertyMstBean(@PathVariable Long id) {
        log.debug("REST request to get PropertyMstBean : {}", id);
        Optional<PropertyMstBean> propertyMstBean = propertyMstBeanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(propertyMstBean);
    }

    /**
     * DELETE  /property-mst-beans/:id : delete the "id" propertyMstBean.
     *
     * @param id the id of the propertyMstBean to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/property-mst-beans/{id}")
    @Timed
    public ResponseEntity<Void> deletePropertyMstBean(@PathVariable Long id) {
        log.debug("REST request to delete PropertyMstBean : {}", id);
        propertyMstBeanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
