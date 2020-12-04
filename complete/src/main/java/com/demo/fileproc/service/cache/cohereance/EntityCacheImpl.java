package com.demo.fileproc.service.cache.cohereance;

import com.demo.fileproc.fw.ICacheService;
import com.demo.fileproc.service.cachelistener.EntityCacheChangeListener;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class EntityCacheImpl  implements ICacheService {

	NamedCache entityCache = null;
	
	private static EntityCacheImpl cacheInstance	=	null;
	
	public static EntityCacheImpl getIntance() {

		
		if(cacheInstance==null) {
			cacheInstance	=	new EntityCacheImpl();
			cacheInstance.initCache("entitycoherenace");
		}
		
		return cacheInstance;
	}
	
    

	@Override
	public void initCache(String cacheName) {
	      CacheFactory.ensureCluster();
	      entityCache = CacheFactory.getCache(cacheName);
	      entityCache.addMapListener(new EntityCacheChangeListener());	      
	}
	
	public void putIntoCache(String key,Object value) {
		entityCache.put(key, value);
	}
	
	public Object getFromIntoCache(String key) {
	      return entityCache.get(key);
	}
	
	public void shutDownCache() {
		CacheFactory.shutdown();
	}
	
	

	
}
