package com.demo.fileproc.service.dto;

import java.util.Date;

public class ErrorDetailDTO {

	
	private String fileName;
	private Date processingDate;
	private long recordPositionNo;
	private String fieldName;
	private String errorMessage;
	
	@Override
	public String toString() {
		return "ErrorDetailDTO [fileName=" + fileName + ", processingDate=" + processingDate + ", recordPositionNo="
				+ recordPositionNo + ", fieldName=" + fieldName + ", errorMessage=" + errorMessage + "]";
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	public long getRecordPositionNo() {
		return recordPositionNo;
	}
	public void setRecordPositionNo(long recordPositionNo) {
		this.recordPositionNo = recordPositionNo;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
