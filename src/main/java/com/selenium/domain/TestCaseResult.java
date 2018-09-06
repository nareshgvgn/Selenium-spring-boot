package com.selenium.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.selenium.enumeration.SeleniumAutomationStatus;

@Entity
@Table(name = "test_case_result")
public class TestCaseResult {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long testCaseId;
	private Long testRunID;
	private SeleniumAutomationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(Long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Long getTestRunID() {
		return testRunID;
	}

	public void setTestRunID(Long testRunID) {
		this.testRunID = testRunID;
	}

	public SeleniumAutomationStatus getStatus() {
		return status;
	}

	public void setStatus(SeleniumAutomationStatus status) {
		this.status = status;
	}

}
