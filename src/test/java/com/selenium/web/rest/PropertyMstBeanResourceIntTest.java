package com.selenium.web.rest;

import com.selenium.SelenimAutomationApp;

import com.selenium.domain.PropertyMstBean;
import com.selenium.repository.PropertyMstBeanRepository;
import com.selenium.service.PropertyMstBeanService;
import com.selenium.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.selenium.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PropertyMstBeanResource REST controller.
 *
 * @see PropertyMstBeanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SelenimAutomationApp.class)
public class PropertyMstBeanResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private PropertyMstBeanRepository propertyMstBeanRepository;

    

    @Autowired
    private PropertyMstBeanService propertyMstBeanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertyMstBeanMockMvc;

    private PropertyMstBean propertyMstBean;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertyMstBeanResource propertyMstBeanResource = new PropertyMstBeanResource(propertyMstBeanService);
        this.restPropertyMstBeanMockMvc = MockMvcBuilders.standaloneSetup(propertyMstBeanResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PropertyMstBean createEntity(EntityManager em) {
        PropertyMstBean propertyMstBean = new PropertyMstBean()
            .name(DEFAULT_NAME)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return propertyMstBean;
    }

    @Before
    public void initTest() {
        propertyMstBean = createEntity(em);
    }

    @Test
    @Transactional
    public void createPropertyMstBean() throws Exception {
        int databaseSizeBeforeCreate = propertyMstBeanRepository.findAll().size();

        // Create the PropertyMstBean
        restPropertyMstBeanMockMvc.perform(post("/api/property-mst-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyMstBean)))
            .andExpect(status().isCreated());

        // Validate the PropertyMstBean in the database
        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeCreate + 1);
        PropertyMstBean testPropertyMstBean = propertyMstBeanList.get(propertyMstBeanList.size() - 1);
        assertThat(testPropertyMstBean.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPropertyMstBean.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createPropertyMstBeanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertyMstBeanRepository.findAll().size();

        // Create the PropertyMstBean with an existing ID
        propertyMstBean.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertyMstBeanMockMvc.perform(post("/api/property-mst-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyMstBean)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyMstBean in the database
        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = propertyMstBeanRepository.findAll().size();
        // set the field null
        propertyMstBean.setName(null);

        // Create the PropertyMstBean, which fails.

        restPropertyMstBeanMockMvc.perform(post("/api/property-mst-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyMstBean)))
            .andExpect(status().isBadRequest());

        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPropertyMstBeans() throws Exception {
        // Initialize the database
        propertyMstBeanRepository.saveAndFlush(propertyMstBean);

        // Get all the propertyMstBeanList
        restPropertyMstBeanMockMvc.perform(get("/api/property-mst-beans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propertyMstBean.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.toString())));
    }
    

    @Test
    @Transactional
    public void getPropertyMstBean() throws Exception {
        // Initialize the database
        propertyMstBeanRepository.saveAndFlush(propertyMstBean);

        // Get the propertyMstBean
        restPropertyMstBeanMockMvc.perform(get("/api/property-mst-beans/{id}", propertyMstBean.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propertyMstBean.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPropertyMstBean() throws Exception {
        // Get the propertyMstBean
        restPropertyMstBeanMockMvc.perform(get("/api/property-mst-beans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropertyMstBean() throws Exception {
        // Initialize the database
        propertyMstBeanService.save(propertyMstBean);

        int databaseSizeBeforeUpdate = propertyMstBeanRepository.findAll().size();

        // Update the propertyMstBean
        PropertyMstBean updatedPropertyMstBean = propertyMstBeanRepository.findById(propertyMstBean.getId()).get();
        // Disconnect from session so that the updates on updatedPropertyMstBean are not directly saved in db
        em.detach(updatedPropertyMstBean);
        updatedPropertyMstBean
            .name(UPDATED_NAME)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restPropertyMstBeanMockMvc.perform(put("/api/property-mst-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPropertyMstBean)))
            .andExpect(status().isOk());

        // Validate the PropertyMstBean in the database
        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeUpdate);
        PropertyMstBean testPropertyMstBean = propertyMstBeanList.get(propertyMstBeanList.size() - 1);
        assertThat(testPropertyMstBean.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPropertyMstBean.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingPropertyMstBean() throws Exception {
        int databaseSizeBeforeUpdate = propertyMstBeanRepository.findAll().size();

        // Create the PropertyMstBean

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropertyMstBeanMockMvc.perform(put("/api/property-mst-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyMstBean)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyMstBean in the database
        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePropertyMstBean() throws Exception {
        // Initialize the database
        propertyMstBeanService.save(propertyMstBean);

        int databaseSizeBeforeDelete = propertyMstBeanRepository.findAll().size();

        // Get the propertyMstBean
        restPropertyMstBeanMockMvc.perform(delete("/api/property-mst-beans/{id}", propertyMstBean.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PropertyMstBean> propertyMstBeanList = propertyMstBeanRepository.findAll();
        assertThat(propertyMstBeanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyMstBean.class);
        PropertyMstBean propertyMstBean1 = new PropertyMstBean();
        propertyMstBean1.setId(1L);
        PropertyMstBean propertyMstBean2 = new PropertyMstBean();
        propertyMstBean2.setId(propertyMstBean1.getId());
        assertThat(propertyMstBean1).isEqualTo(propertyMstBean2);
        propertyMstBean2.setId(2L);
        assertThat(propertyMstBean1).isNotEqualTo(propertyMstBean2);
        propertyMstBean1.setId(null);
        assertThat(propertyMstBean1).isNotEqualTo(propertyMstBean2);
    }
}
