package com.selenium.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.selenium.enumeration.SeleniumAutomationStatus;

@Entity
@Table(name = "testcase_step_result")
public class TestCaseStepResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "test_step_id", nullable = false)
	private String testStepId;

	@NotNull
	@Column(name = "test_run_id", nullable = false)
	private String testRunID;

	@NotNull
	@Column(name = "status", nullable = false)
	private SeleniumAutomationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(String testStepId) {
		this.testStepId = testStepId;
	}

	public String getTestRunID() {
		return testRunID;
	}

	public void setTestRunID(String testRunID) {
		this.testRunID = testRunID;
	}

	public SeleniumAutomationStatus getStatus() {
		return status;
	}

	public void setStatus(SeleniumAutomationStatus status) {
		this.status = status;
	}

}
