package com.selenium.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.selenium.enumeration.SeleniumAutomationStatus;

@Entity
@Table(name = "test_run_result")
public class TestRunResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testRunID;
	
	@NotNull
	@Column(name = "test_run_by", nullable = false)
	private String testRunBy;
	
	@NotNull
	@Column(name = "start_time", nullable = false)
	private Date startTime;
	
	@NotNull
	@Column(name = "end_time", nullable = false)
	private Date endTime;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private SeleniumAutomationStatus status;
	
	@NotNull
	@Column(name = "title", nullable = false)
	private String title;
	
	@NotNull
	@Column(name = "machine_name", nullable = false)
	private String machineName;
	
	@NotNull
	@Column(name = "browser", nullable = false)
	private String browser;
	
	@NotNull
	@Column(name = "test_case_id", nullable = false)
	private String testCaseId;

	@NotNull
	@Column(name = "property_set_id", nullable = false)
	private String propertySet;

	public String getPropertySet() {
		return propertySet;
	}

	public void setPropertySet(String propertySet) {
		this.propertySet = propertySet;
	}

	public SeleniumAutomationStatus getStatus() {
		return status;
	}

	public void setStatus(SeleniumAutomationStatus status) {
		this.status = status;
	}

	public Long getTestRunID() {
		return testRunID;
	}

	public void setTestRunID(Long testRunID) {
		this.testRunID = testRunID;
	}

	public String getTestRunBy() {
		return testRunBy;
	}

	public void setTestRunBy(String testRunBy) {
		this.testRunBy = testRunBy;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

}
