package com.selenium.repository;

import com.selenium.domain.PropertyDtlBean;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PropertyDtlBean entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertyDtlBeanRepository extends JpaRepository<PropertyDtlBean, Long> {

}
