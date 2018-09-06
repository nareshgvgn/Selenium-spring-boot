package com.selenium.repository;

import com.selenium.domain.PropertyMstBean;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PropertyMstBean entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertyMstBeanRepository extends JpaRepository<PropertyMstBean, Long> {

}
