package com.demo.fileproc.service.dto;

import java.util.Date;

public class FileInfoDTO implements DataObjectInterface {

	private String fileName	=	null;
	private long id	=	0;
	private Date receivedDate	=	null;
	private int fileProcessingstatus	=	0;
	private long recordCount	=	0;
	private long processedRecordCount	=	0;
	
	public long getProcessedRecordCount() {
		return processedRecordCount;
	}
	public void setProcessedRecordCount(long processedRecordCount) {
		this.processedRecordCount = processedRecordCount;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "FileInfoDTO [fileName=" + fileName + ", id=" + id + ", receivedDate=" + receivedDate
				+ ", fileProcessingstatus=" + fileProcessingstatus + ", recordCount=" + recordCount
				+ ", processedRecordCount=" + processedRecordCount + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public int getFileProcessingstatus() {
		return fileProcessingstatus;
	}
	public void setFileProcessingstatus(int fileProcessingstatus) {
		this.fileProcessingstatus = fileProcessingstatus;
	}
	
}
