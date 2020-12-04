package com.demo.fileproc.service.cache;

import com.demo.fileproc.fw.ICacheService;
import com.demo.fileproc.service.cache.cohereance.CoherenceCacheImpl;
import com.demo.fileproc.service.cache.cohereance.EntityCacheImpl;

public class CacheServiceFactory {

	
	public static ICacheService getCache(String cacheType) {
		ICacheService cacheService	=	null;
		if(cacheType.equals("filechunkcoherenace")) {
			cacheService	=	CoherenceCacheImpl.getIntance();	
		} else if(cacheType.equals("entitycoherenace")) {
			cacheService	=	EntityCacheImpl.getIntance();	
		}
		
		
		return cacheService;
		
	}
}
