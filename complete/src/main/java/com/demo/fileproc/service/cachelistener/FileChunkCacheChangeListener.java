package com.demo.fileproc.service.cachelistener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.fw.BaseFileChunkCacheListener;
import com.demo.fileproc.fw.IBatchJobPreperator;
import com.demo.fileproc.service.batchprep.FileChunkBatchPreperator;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileChunkDataDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.repo.RowDataRepo;
import com.tangosol.util.MapEvent;

public class FileChunkCacheChangeListener extends BaseFileChunkCacheListener { 
//implements MapListener {
	 
	Logger logger	=	LoggerFactory.getLogger(FileChunkCacheChangeListener.class);
	
	RowDataRepo rowDataRepo	=	null;
	
	public FileChunkCacheChangeListener() {
	}

	public void entryDeleted(MapEvent me) {
        System.out.println("Deleted Key = " + me.getKey() + ", Value = " + me.getOldValue());
    }
 
    public void entryUpdated(MapEvent me) {
    }

	@Override
	public void handleDateReceived(DataObjectInterface data) {
		// TODO Auto-generated method stub
		rowDataRepo	=	(RowDataRepo)AppContext.getSpringContext().getBean("rowDataRepo");
    	FileChunkDataDTO fileChunkDTO	=	(FileChunkDataDTO)data;
//    	CacheServiceInterface cache	=	getCache();
//    	cache.putIntoCache(key, me.getMap().get(key));

    	logger.debug("Got the Call from Parent Class to handle "+fileChunkDTO.getChunkDataList().size());
    	logger.debug("*********************************************************************************\n");
    	logger.debug("You SHOULD DETECH FROM HERE FOR FURTHER PROCESSING..KEEP A NOTE YOU CAN DO LATER");
    	logger.debug("Iterate the list received in the data and publish to TaskExecuter");
    	logger.debug("From Here On...Threads will be executed on the JVM where Message is received");
    	logger.debug("*********************************************************************************\n");
    	//processChunk(fileChunkDTO);
	}
	
	
	private void processChunk(FileChunkDataDTO fileChunkDTO) {
		List<FileRowDataDTO> list	=	fileChunkDTO.getChunkDataList();
		long LOW_PK	=	fileChunkDTO.getStartKeyIndexForPK();
		long HIGH_PK	=	fileChunkDTO.getEndKeyIndexForPK();
		
		rowDataRepo	=	(RowDataRepo)AppContext.getSpringContext().getBean("rowDataRepo");
		
		logger.debug("Processing CHUNK BLOCK "+fileChunkDTO.getChunkId());
		
		for (FileRowDataDTO rowData : list) {
		//	logger.debug("Processing now "+fileRowDataDTO.getRecordPositionInFile());
			rowDataRepo.saveFile(rowData);
			/**
			 * OR DO IN BATCH USING JDBC
			 */
		}
		
		
	}

	@Override
	public void validatedataReceived(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postProseesor(DataObjectInterface data) {
		// TODO Auto-generated method stub
		rowDataRepo.removeDuplicate(data);
	}

	@Override
	public void publishForNextStep(DataObjectInterface data) {
		// TODO Auto-generated method stub
		logger.debug("Now time to publish");
		FileChunkDataDTO chunkDTO	=	(FileChunkDataDTO)data;
		IBatchJobPreperator fileChunkBatchPreperator	=	new FileChunkBatchPreperator();
		fileChunkBatchPreperator.makeWorkUnits(chunkDTO);
		
	}
    
    

}
