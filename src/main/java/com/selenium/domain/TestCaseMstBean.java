package com.selenium.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "testcase_mst")
public class TestCaseMstBean implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testCaseId;
	
	@NotNull
    @Column(name = "priority", nullable = false)
	private int priority;
	
	@NotNull
    @Column(name = "module_name", nullable = false)
	private String moduleName;
	
	@NotNull
    @Column(name = "designed_by", nullable = false)
	private String testDesinedBy;
	
	@NotNull
    @Column(name = "title", nullable = false)
	private String testCaseTitle;
	
	@NotNull
    @Column(name = "description", nullable = false)
	private String testcaseDescription;
	
	@NotNull
    @Column(name = "project_id", nullable = false)
	private String projectId;
	
	@NotNull
    @Column(name = "sequence_no", nullable = false)
	private Integer sequenceNo;
	
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public Long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(Long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTestDesinedBy() {
		return testDesinedBy;
	}

	public void setTestDesinedBy(String testDesinedBy) {
		this.testDesinedBy = testDesinedBy;
	}

	public String getTestCaseTitle() {
		return testCaseTitle;
	}

	public void setTestCaseTitle(String testCaseTitle) {
		this.testCaseTitle = testCaseTitle;
	}

	public String getTestcaseDescription() {
		return testcaseDescription;
	}

	public void setTestcaseDescription(String testcaseDescription) {
		this.testcaseDescription = testcaseDescription;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String project) {
		this.projectId = project;
	}
}
