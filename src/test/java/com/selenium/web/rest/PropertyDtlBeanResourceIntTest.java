package com.selenium.web.rest;

import com.selenium.SelenimAutomationApp;

import com.selenium.domain.PropertyDtlBean;
import com.selenium.repository.PropertyDtlBeanRepository;
import com.selenium.service.PropertyDtlBeanService;
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
 * Test class for the PropertyDtlBeanResource REST controller.
 *
 * @see PropertyDetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SelenimAutomationApp.class)
public class PropertyDtlBeanResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_KEY = "BBBBBBBBBB";

    @Autowired
    private PropertyDtlBeanRepository propertyDtlBeanRepository;

    

    @Autowired
    private PropertyDtlBeanService propertyDtlBeanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertyDtlBeanMockMvc;

    private PropertyDtlBean propertyDtlBean;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertyDetailResource propertyDtlBeanResource = new PropertyDetailResource(propertyDtlBeanService);
        this.restPropertyDtlBeanMockMvc = MockMvcBuilders.standaloneSetup(propertyDtlBeanResource)
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
    public static PropertyDtlBean createEntity(EntityManager em) {
        PropertyDtlBean propertyDtlBean = new PropertyDtlBean()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .parentKey(DEFAULT_PARENT_KEY);
        return propertyDtlBean;
    }

    @Before
    public void initTest() {
        propertyDtlBean = createEntity(em);
    }

    @Test
    @Transactional
    public void createPropertyDtlBean() throws Exception {
        int databaseSizeBeforeCreate = propertyDtlBeanRepository.findAll().size();

        // Create the PropertyDtlBean
        restPropertyDtlBeanMockMvc.perform(post("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDtlBean)))
            .andExpect(status().isCreated());

        // Validate the PropertyDtlBean in the database
        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeCreate + 1);
        PropertyDtlBean testPropertyDtlBean = propertyDtlBeanList.get(propertyDtlBeanList.size() - 1);
        assertThat(testPropertyDtlBean.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPropertyDtlBean.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testPropertyDtlBean.getParentKey()).isEqualTo(DEFAULT_PARENT_KEY);
    }

    @Test
    @Transactional
    public void createPropertyDtlBeanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertyDtlBeanRepository.findAll().size();

        // Create the PropertyDtlBean with an existing ID
        propertyDtlBean.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertyDtlBeanMockMvc.perform(post("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDtlBean)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyDtlBean in the database
        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = propertyDtlBeanRepository.findAll().size();
        // set the field null
        propertyDtlBean.setName(null);

        // Create the PropertyDtlBean, which fails.

        restPropertyDtlBeanMockMvc.perform(post("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDtlBean)))
            .andExpect(status().isBadRequest());

        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = propertyDtlBeanRepository.findAll().size();
        // set the field null
        propertyDtlBean.setValue(null);

        // Create the PropertyDtlBean, which fails.

        restPropertyDtlBeanMockMvc.perform(post("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDtlBean)))
            .andExpect(status().isBadRequest());

        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPropertyDtlBeans() throws Exception {
        // Initialize the database
        propertyDtlBeanRepository.saveAndFlush(propertyDtlBean);

        // Get all the propertyDtlBeanList
        restPropertyDtlBeanMockMvc.perform(get("/api/property-dtl-beans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propertyDtlBean.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].parentKey").value(hasItem(DEFAULT_PARENT_KEY.toString())));
    }
    

    @Test
    @Transactional
    public void getPropertyDtlBean() throws Exception {
        // Initialize the database
        propertyDtlBeanRepository.saveAndFlush(propertyDtlBean);

        // Get the propertyDtlBean
        restPropertyDtlBeanMockMvc.perform(get("/api/property-dtl-beans/{id}", propertyDtlBean.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propertyDtlBean.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.parentKey").value(DEFAULT_PARENT_KEY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPropertyDtlBean() throws Exception {
        // Get the propertyDtlBean
        restPropertyDtlBeanMockMvc.perform(get("/api/property-dtl-beans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropertyDtlBean() throws Exception {
        // Initialize the database
        propertyDtlBeanService.save(propertyDtlBean);

        int databaseSizeBeforeUpdate = propertyDtlBeanRepository.findAll().size();

        // Update the propertyDtlBean
        PropertyDtlBean updatedPropertyDtlBean = propertyDtlBeanRepository.findById(propertyDtlBean.getId()).get();
        // Disconnect from session so that the updates on updatedPropertyDtlBean are not directly saved in db
        em.detach(updatedPropertyDtlBean);
        updatedPropertyDtlBean
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .parentKey(UPDATED_PARENT_KEY);

        restPropertyDtlBeanMockMvc.perform(put("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPropertyDtlBean)))
            .andExpect(status().isOk());

        // Validate the PropertyDtlBean in the database
        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeUpdate);
        PropertyDtlBean testPropertyDtlBean = propertyDtlBeanList.get(propertyDtlBeanList.size() - 1);
        assertThat(testPropertyDtlBean.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPropertyDtlBean.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPropertyDtlBean.getParentKey()).isEqualTo(UPDATED_PARENT_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingPropertyDtlBean() throws Exception {
        int databaseSizeBeforeUpdate = propertyDtlBeanRepository.findAll().size();

        // Create the PropertyDtlBean

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropertyDtlBeanMockMvc.perform(put("/api/property-dtl-beans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDtlBean)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyDtlBean in the database
        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePropertyDtlBean() throws Exception {
        // Initialize the database
        propertyDtlBeanService.save(propertyDtlBean);

        int databaseSizeBeforeDelete = propertyDtlBeanRepository.findAll().size();

        // Get the propertyDtlBean
        restPropertyDtlBeanMockMvc.perform(delete("/api/property-dtl-beans/{id}", propertyDtlBean.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PropertyDtlBean> propertyDtlBeanList = propertyDtlBeanRepository.findAll();
        assertThat(propertyDtlBeanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyDtlBean.class);
        PropertyDtlBean propertyDtlBean1 = new PropertyDtlBean();
        propertyDtlBean1.setId(1L);
        PropertyDtlBean propertyDtlBean2 = new PropertyDtlBean();
        propertyDtlBean2.setId(propertyDtlBean1.getId());
        assertThat(propertyDtlBean1).isEqualTo(propertyDtlBean2);
        propertyDtlBean2.setId(2L);
        assertThat(propertyDtlBean1).isNotEqualTo(propertyDtlBean2);
        propertyDtlBean1.setId(null);
        assertThat(propertyDtlBean1).isNotEqualTo(propertyDtlBean2);
    }
}
