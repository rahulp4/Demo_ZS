package com.demo.fileproc.service.dto;

import java.util.Date;

/**
 * This is entity row in FIRST TABLE
 * NAME OF TABLE: DEMO_FILE_ENTITY_DATA
 * 	
 * 	COL : entityId : long
 * 	COL : fileName : varchar(30)
 * 	COL : recor
 * @author RPODDAR
 *
 */
public class FileRowDataDTO implements DataObjectInterface {
	private String fileRefId;
	private Date processingDate;
	
	public Date getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	public String getFileRefId() {
		return fileRefId;
	}
	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
	}
	private String rawData	=	null;
	private long recordPositionInFile	=	0;
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public long getRecordPositionInFile() {
		return recordPositionInFile;
	}
	public void setRecordPositionInFile(long recordPositionInFile) {
		this.recordPositionInFile = recordPositionInFile;
	}
	@Override
	public String toString() {
		return "FileRowDataDTO [fileRefId=" + fileRefId + ", processingDate=" + processingDate + ", rawData=" + rawData
				+ ", recordPositionInFile=" + recordPositionInFile + "]";
	}
	
}
