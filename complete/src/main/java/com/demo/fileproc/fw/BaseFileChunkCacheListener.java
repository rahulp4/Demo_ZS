package com.demo.fileproc.fw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.fileproc.service.cache.CacheServiceFactory;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileInfoDTO;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public abstract class BaseFileChunkCacheListener  implements MapListener,ICacheListener {

	Logger logger	=	LoggerFactory.getLogger(BaseFileChunkCacheListener.class);
	
	@Override
    public void entryInserted(MapEvent me) {
    	String key	=	(String)me.getKey();
    	
    	//SET_
    	logger.debug("KEY IS "+key);
    	//BATCH_    	
    	if(key.startsWith("SET_")) {
        	DataObjectInterface data	=	(DataObjectInterface)me.getMap().get(key);
        	this.validatedataReceived(data);
        	this.handleDateReceived(data);
        	this.postProseesor(data);
        	this.publishForNextStep(data);
    		
    	} else {
    		//STARTS WITH BATCH_
    		//ITS A BATCH STATUS
    		FileInfoDTO fileDTO	=	(FileInfoDTO)me.getMap().get(key);
    		logger.debug("File information DTO "+fileDTO);
    		
    	}

//    	CacheServiceInterface cache	=	getCache();
//    	cache.putIntoCache(key, me.getMap().get(key));

    }
	
    private ICacheService getCache() {
		return CacheServiceFactory.getCache("entitycoherenace");
	}

}
