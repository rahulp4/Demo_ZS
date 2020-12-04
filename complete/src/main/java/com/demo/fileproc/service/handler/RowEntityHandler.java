package com.demo.fileproc.service.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.fileproc.fw.BaseEntityHandler;
import com.demo.fileproc.fw.IEntityProcessor;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.ErrorDetailDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.dto.customer.CustomerEntityDTO;
import com.demo.fileproc.service.parser.StringDataParser;
import com.demo.fileproc.service.repo.RowDataRepo;

public class RowEntityHandler extends BaseEntityHandler {

	Logger logger	=	LoggerFactory.getLogger(RowEntityHandler.class);
//	
//	private FileRowDataDTO	fileRowDataDTO	=	null;
//	

//	@Override
//	public String toString() {
//		return "RowEntityHandler [fileRowDataDTO=" + fileRowDataDTO + "]";
//	}
//
//
//	public FileRowDataDTO getFileRowDataDTO() {
//		return fileRowDataDTO;
//	}


//	public void setFileRowDataDTO(FileRowDataDTO fileRowDataDTO) {
//		this.fileRowDataDTO = fileRowDataDTO;
//	}
//

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		logger.debug("Processing "+fileRowDataDTO.getRecordPositionInFile());
//	}

	@Override
	public void handleEntity(DataObjectInterface data) {
		// TODO Auto-generated method stub
		FileRowDataDTO fileRowDataDTO	=	(FileRowDataDTO)data;
		CustomerEntityDTO customerData	=	null;
		ErrorDetailDTO error	=	null;
		
		logger.debug("Processing "+fileRowDataDTO.getRecordPositionInFile());
		StringDataParser parser	=	new StringDataParser();
		String dataArray[]	=	parser.parse(fileRowDataDTO.getRawData());
		String type	=	dataArray[0];
		type	=	"CUSTOMER";//FOR TESTING
		IEntityProcessor processor	=	getEntityProcessor(type);
		DataObjectInterface dataObj	=	processor.mapEntity(dataArray);
		logger.debug("Got the Entity Data after mapping as "+dataObj);
		error	=	processor.validateEntity(dataObj);
		if(error==null) {
			//ALL WELL
		} else {
			//SAVE TO DB IN ERROR TABLE
		}
	}


	public ErrorDetailDTO validate(FileRowDataDTO fileRowDataDTO,CustomerEntityDTO customerData) {
		/**
		 * INCASE OF ANY ERROR FILL OUT ERRORDETAILDTO
		 */
		ErrorDetailDTO errorDetail	=	null;
		
		if(true) {
			//ASSUME SOME ERROR
			//ALSO VALIDATE IF CORRESONDING SALES DATE IS AVAILABLE
			errorDetail	=	new ErrorDetailDTO();
			errorDetail.setFieldName("Id");
			errorDetail.setRecordPositionNo(fileRowDataDTO.getRecordPositionInFile());
			
			errorDetail.setProcessingDate(fileRowDataDTO.getProcessingDate());
			errorDetail.setFileName(fileRowDataDTO.getFileRefId());
			
		}
		
		return errorDetail;
	}


	/**
	 * 
	 */
	@Override
	public void cleanup(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}
}
