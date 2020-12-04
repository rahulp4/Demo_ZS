package com.demo.fileproc.service.dto;

import java.util.Date;
import java.util.List;

public class FileChunkDataDTO implements DataObjectInterface {

	private String fileRefId;
	private Date processingDate;
	
	public String getFileRefId() {
		return fileRefId;
	}
	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
	}
	public Date getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
	/**
	 * CHUNK ID TO IDENTIFY AT ALL STAGES
	 */
	private String chunkId;
	
	/**
	 * POSITION OF RECORD IN FILE.THIS WILL HELP IN TROUBLE SHOOTING
	 */
	private long recordPositionInFile;

	/**
	 * To Avoid Any Contention while processing each Block, Primary Key Has to be in
	 * a specific Block. THis is assigned during the CHUNK CREATION IT SELF.
	 */
	private long startKeyIndexForPK	=	-1;
	
	public long getStartKeyIndexForPK() {
		return startKeyIndexForPK;
	}
	public void setStartKeyIndexForPK(long startKeyIndexForPK) {
		this.startKeyIndexForPK = startKeyIndexForPK;
	}
	public long getEndKeyIndexForPK() {
		return endKeyIndexForPK;
	}
	public void setEndKeyIndexForPK(long endKeyIndexForPK) {
		this.endKeyIndexForPK = endKeyIndexForPK;
	}
	/**
	 * To Avoid Any Contention while processing each Block, Primary Key Has to be in
	 * a specific Block. THis is assigned during the CHUNK CREATION IT SELF.
	 */
	private long endKeyIndexForPK	=	-1;
	
	public long getRecordPositionInFile() {
		return recordPositionInFile;
	}
	public void setRecordPositionInFile(long recordPositionInFile) {
		this.recordPositionInFile = recordPositionInFile;
	}
	private List<FileRowDataDTO> chunkDataList;
	public List<FileRowDataDTO> getChunkDataList() {
		return chunkDataList;
	}
	public void setChunkDataList(List<FileRowDataDTO> chunkDataList) {
		this.chunkDataList = chunkDataList;
	}
	boolean processingStatus	=	false;
	@Override
	public String toString() {
		return "FileChunkDataDTO [fileRefId=" + fileRefId + ", chunkId=" + chunkId + ", recordPositionInFile="
				+ recordPositionInFile + ", startKeyIndexForPK=" + startKeyIndexForPK + ", endKeyIndexForPK="
				+ endKeyIndexForPK + ", chunkDataList=" + chunkDataList + ", processingStatus=" + processingStatus
				+ "]";
	}
	public String getChunkId() {
		return chunkId;
	}
	public void setChunkId(String chunkId) {
		this.chunkId = chunkId;
	}
	
	public boolean isProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(boolean processingStatus) {
		this.processingStatus = processingStatus;
	}
}
