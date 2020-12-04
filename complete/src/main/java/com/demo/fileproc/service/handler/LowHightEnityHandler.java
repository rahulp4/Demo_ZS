package com.demo.fileproc.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.fileproc.fw.BaseEntityHandler;
import com.demo.fileproc.fw.IEntityProcessor;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.ErrorDetailDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.dto.customer.CustomerEntityDTO;
import com.demo.fileproc.service.parser.StringDataParser;

public class LowHightEnityHandler extends BaseEntityHandler {

	
	Logger logger	=	LoggerFactory.getLogger(LowHightEnityHandler.class);
	
	@Override
	public void handleEntity(DataObjectInterface data) {
		// TODO Auto-generated method stub
		long LOW	=	getLOWKEY();
		long HIGH	=	getHIGHKEY();
		/**
		 * FROM DATABASE GET ALL RECORDS FOR HIGH AND LOW
		 */
		
		List<FileRowDataDTO> dataList	=	new ArrayList<FileRowDataDTO>();
		List<ErrorDetailDTO> errorList	=	new ArrayList<ErrorDetailDTO>();
		
		for (FileRowDataDTO fileRowDataDTO : dataList) {
			
			CustomerEntityDTO customerData	=	null;
			ErrorDetailDTO error	=	null;
			
			logger.debug("Processing "+fileRowDataDTO.getRecordPositionInFile());
			StringDataParser parser	=	new StringDataParser();
		
			
			String dataArray[]	=	parser.parse(fileRowDataDTO.getRawData());
			String type	=	dataArray[0];
			IEntityProcessor processor	=	getEntityProcessor(type);
			DataObjectInterface dataObj	=	processor.mapEntity(dataArray);
			error	=	processor.validateEntity(dataObj);
			
			if(error==null) {
				//ALL WELL
			} else {
				//SAVE TO DB IN ERROR TABLE
				errorList.add(error);
			}
		

		
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

	@Override
	public void cleanup(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}

}
