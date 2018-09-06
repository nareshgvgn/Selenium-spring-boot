package com.selenium.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "testcase_step_mst")
public class TestCaseDtlBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testStepId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "test_case_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TestCaseMstBean testCaseMstBean;

	@NotNull
	@Column(name = "sequence_no", nullable = false)
	private Integer sequenceNo;

	@NotNull
	@Column(name = "keyword", nullable = false)
	private String keyword;

	@NotNull
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull
	@Column(name = "input_data", nullable = false)
	private String inputData;

	@NotNull
	@Column(name = "object_name", nullable = false)
	private String objectName;
	@NotNull
	@Column(name = "object_type", nullable = false)
	private String objectType;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(Long testStepId) {
		this.testStepId = testStepId;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public TestCaseMstBean getTestCaseMstBean() {
		return testCaseMstBean;
	}

	public void setTestCaseMstBean(TestCaseMstBean testCaseMstBean) {
		this.testCaseMstBean = testCaseMstBean;
	}
}
