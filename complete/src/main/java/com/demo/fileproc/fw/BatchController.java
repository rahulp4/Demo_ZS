package com.demo.fileproc.fw;

import com.demo.fileproc.service.cache.CacheServiceFactory;
import com.demo.fileproc.service.dto.DataObjectInterface;

public class BatchController {

	
	public static void startBatch(String batchRunId,DataObjectInterface fileObj) {
		ICacheService cache	=	CacheServiceFactory.getCache("filechunkcoherenace");
		cache.putIntoCache(batchRunId, fileObj);
	}
	
	
}
