package com.demo.fileproc.service.batchprep;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.fw.BaseBatchJobPreperator;
import com.demo.fileproc.fw.IEntityHandler;
import com.demo.fileproc.service.dto.FileChunkDataDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.handler.LowHightEnityHandler;
import com.demo.fileproc.service.handler.RowEntityHandler;
import com.demo.fileproc.service.repo.RowDataRepo;

public class FileChunkBatchPreperator extends BaseBatchJobPreperator {

	Logger logger	=	LoggerFactory.getLogger(FileChunkBatchPreperator.class);
	

	@Override
	public void makeWorkUnitsForSINGLEWITHCOMMITS(FileChunkDataDTO fileChunkDTO) {
		// TODO Auto-generated method stub
		
		TaskExecutor taskExecutor =	(TaskExecutor)AppContext.getSpringContext().getBean("taskExecutor");

		List<FileRowDataDTO> list	=	fileChunkDTO.getChunkDataList();
		long LOW_PK	=	fileChunkDTO.getStartKeyIndexForPK();
		long HIGH_PK	=	fileChunkDTO.getEndKeyIndexForPK();
		logger.debug("Processing CHUNK BLOCK "+fileChunkDTO.getChunkId());
		for (FileRowDataDTO rowData : list) {
			//IEntityHandler handler	=	new RowEntityHandler();
			RowEntityHandler handler	=	new RowEntityHandler();
			handler.setData(rowData);
			taskExecutor.execute(handler);
		}
		

	}

	@Override
	public void makeWorkUnitsForLOWHIGH(FileChunkDataDTO fileChunkDTO) {
		// TODO Auto-generated method stub
		TaskExecutor taskExecutor =	(TaskExecutor)AppContext.getSpringContext().getBean("taskExecutor");

		List<FileRowDataDTO> list	=	fileChunkDTO.getChunkDataList();
		long LOW_PK	=	fileChunkDTO.getStartKeyIndexForPK();
		long HIGH_PK	=	fileChunkDTO.getEndKeyIndexForPK();
		logger.debug("Processing CHUNK BLOCK "+fileChunkDTO.getChunkId());
		LowHightEnityHandler handler	=	new LowHightEnityHandler();
		handler.setLOWKEY(fileChunkDTO.getStartKeyIndexForPK());
		handler.setHIGHKEY(fileChunkDTO.getEndKeyIndexForPK());
		taskExecutor.execute(handler);
	}

	@Override
	public void makeWorkUnitsForDISTRUBTETOCACHE(FileChunkDataDTO fileChunkDTO) {
		// TODO Auto-generated method stub
		
	}
}
