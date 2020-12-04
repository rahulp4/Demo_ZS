package com.demo.fileproc.fw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.common.Utility;
import com.demo.fileproc.service.cache.CacheServiceFactory;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileChunkDataDTO;
import com.demo.fileproc.service.dto.FileInfoDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.handler.FileChunkHandler;

public abstract class BaseFileProcessorImpl implements IFileProcessor{

	ICacheService cache	=	null;
	Logger logger	=	LoggerFactory.getLogger(BaseFileProcessorImpl.class);

	
	public FileInfoDTO storeFileRefIntoDB(String filePath){
		/**
		 * USING HIBERNATE STORE INTO DATABASE 
		 * SeqNo : 
		 * FilePath
		 * DateReceived:
		 * Status :
		 * 
		 */
		long batchId	=	10;
		FileInfoDTO fileDTO	=	new FileInfoDTO();
		fileDTO.setId(batchId);//USE AUTO SEQ
		fileDTO.setFileName(filePath);
		fileDTO.setFileProcessingstatus(1);//1-IN PROCESS
		fileDTO.setReceivedDate(new Date());
		//CALL REPO TO STORE
		return fileDTO;
		 
	}

	/**
	 * IN CASE THIS IS NOT HANLED BY CHILD CLASS. IT WILL BE HANDLED HERE
	 * 
	 */
	@Override
	public Scanner readFiles(String filePath,DataObjectInterface fileDTO) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		int count	=	0;
		int threshHosd	=	10000;
		Scanner sc = null;
		try {
			FileInputStream inputStream = new FileInputStream(file);
			sc = new Scanner(inputStream, "UTF-8");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return sc;
	}


	/**
	 * WRITES TO COHERENCE CACHE WITH KEY AND VALUES
	 * THIS PUTS THE DATA ONTO DISTRIBUTED CACHE SO THAT IT IS RECEIVED BY MULTIPLE JVMS.
	 * 
	 * @param key
	 * @param value
	 */
	private void writeToDtributedCache(String key,DataObjectInterface value) {
		cache.putIntoCache(key, value);
	}
	
	/**
	 * GETS THE CACHE FOR FILECHUNKS
	 * @return
	 */
	private ICacheService getCache() {
		return CacheServiceFactory.getCache("filechunkcoherenace");
	}
	
	


	/**
	 * The child class FileProcessorImpl reads the file and returns the scanner object.
	 * How to handle is left to this Base Framework Class
	 * NOW THIS METHOD GETS THE SCANNER OBJECT AND STARTS 
	 * READING FILE LINE BY LINE.
	 * CREATES CHUNK OF DATA.
	 * CHUNK SIZE IS DECIDED BASED ON CHUNK_SIZE .
	 * THE CHUNKS ARE PUBLISHED OVER COHERENCE LAYER.
	 * COHERENC IS USING DISTRIBUTED SCHEME
	 * AND IT IS RECEIVED ON MULTIPLE JVMs.
	 */
	@Override
	public void scanFile(Scanner sc,String fileName,Date proceDate,DataObjectInterface dto) {
		FileInfoDTO fileDTO	=	(FileInfoDTO)dto;
		
		// TODO Auto-generated method stub
		int count	=	0;
		
		/**
		 * READ FROM CONFIGURATION FILE
		 */
		int CHUNK_SIZE	=	10000;
		int CHUNK_BLOCK_NO	=	0;
		
		
		try {
			cache	=	getCache();
			
			TaskExecutor tE =	(TaskExecutor)AppContext.getSpringContext().getBean("taskExecutor");
			int lines	=	0;
			List<FileRowDataDTO> chunkData	=	null;
			FileChunkDataDTO fileChunkDTO	=	null;
			int chunkCount	=	0;
			while (sc.hasNextLine()) {
				FileRowDataDTO rowData	=	new FileRowDataDTO();
				rowData.setFileRefId(fileName);
				rowData.setProcessingDate(proceDate);
				
				count++;
				String line = sc.nextLine();
				if(lines==0) {
					chunkData	=	new ArrayList<FileRowDataDTO>();
					fileChunkDTO	=	new FileChunkDataDTO();
					CHUNK_BLOCK_NO	=	CHUNK_BLOCK_NO+1;
					
				}
				rowData.setRawData(line);
				rowData.setRecordPositionInFile(count);
				
				chunkData.add(rowData);
				lines++;
				chunkCount++;
				
				if(lines==CHUNK_SIZE) {
					fileChunkDTO.setChunkId("SET_"+chunkCount);
					fileChunkDTO.setChunkDataList(chunkData);
					/**
					 * This is to identify the record in file at later stage
					 */
					
					/**
					 * To avoid unwanted resource contention between parallel threads 
					 * it can be advantageous 
					 * to partition the database
					 * This will be used when a chunk is being processed in different 
					 * thread and on different JVMS.
					 * 
					 */
					Utility.setPKHighAndLow(fileChunkDTO, CHUNK_BLOCK_NO);
					
					
					fileChunkDTO.setRecordPositionInFile(lines);
					fileChunkDTO.setFileRefId(fileName);
					fileChunkDTO.setProcessingDate(proceDate);
					//setHighAndLow()
					System.out.println("Publishing "+fileChunkDTO.getChunkId());
					/**
					 * PUBLISHING CHUNKS OF DATA.
					 * 
					 */
					writeToDtributedCache(fileChunkDTO.getChunkId(),fileChunkDTO);
					lines	=	0;
					//chunkCount	=	0;
				}

			}
			System.out.println("Total Lines "+count);
			fileDTO.setRecordCount(count);
			
			/**
			 * Update Distributed Cache for Batch Status
			 */
			
			BatchController.startBatch("BATCH_"+fileDTO.getId(), fileDTO);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}



	/**
	 * IGNORE THIS METHOD
	 * @param filePath
	 */
	public void readFiles_back(String filePath) {
        ExecutorService executor = getThreadPoolService();//(ThreadPoolExecutor) Executors.newFixedThreadPool(4);
 
        List<Future<String>> resultList = new ArrayList<>();

		File file = new File(filePath);
		int count	=	0;
		int threshHosd	=	10000;
		
		try {
			
			FileInputStream inputStream = new FileInputStream(file);
			Scanner sc = new Scanner(inputStream, "UTF-8");
			int lines	=	0;
			Map<String,List<String>> chunkDataMap	=	null;
			List<String> chunkDataList	=	null;
			int chunkCount	=	0;
			while (sc.hasNextLine()) {
				count++;
				String line = sc.nextLine();
				if(lines==0) {
					chunkCount	=	0;
					chunkDataList	=	new ArrayList<String>();
					chunkDataMap	=	new HashMap<String, List<String>>();
					
				}
				chunkDataList.add(line);
				lines++;
				chunkCount++;
				
				if(lines==threshHosd) {
					
					chunkDataMap.put("SET"+chunkCount,chunkDataList);
					System.out.println("Publishing "+chunkDataMap);
					
					FileChunkHandler handler = new FileChunkHandler(chunkDataMap);
		            Future<String> result = executor.submit(handler);
		            resultList.add(result);
					lines	=	0;
					chunkCount	=	0;
				}

			}
			System.out.println("Total Lines "+count);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
        executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        for (int i = 0; i < resultList.size(); i++) 
        {
            Future<String> result = resultList.get(i);
            String dataSetKey = null;
            try {
            	dataSetKey = result.get();
            	System.out.println("Completed "+dataSetKey);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            
        }
 
        executor.shutdown();
    }

	/**
	 * IGNORE THIS METHOD
	 * @return
	 */
	public ThreadPoolExecutor getThreadPoolService() {
		ThreadPoolExecutor executor	=	new ThreadPoolExecutor(4, 200, 0L,TimeUnit.MICROSECONDS,
				new LinkedBlockingDeque<Runnable>());
		return executor;
		
	}
}
