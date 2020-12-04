package com.demo.fileproc.service.cache.cohereance;

import com.demo.fileproc.fw.ICacheService;
import com.demo.fileproc.service.cachelistener.FileChunkCacheChangeListener;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCacheImpl implements ICacheService {

	NamedCache fileChunkcache = null;
	
	private static CoherenceCacheImpl cacheInstance	=	null;
	
	public static CoherenceCacheImpl getIntance() {

		
		if(cacheInstance==null) {
			cacheInstance	=	new CoherenceCacheImpl();
			cacheInstance.initCache("filechunkcoherenace");
		}
		
		return cacheInstance;
	}
	
    

	@Override
	public void initCache(String cacheName) {
	      CacheFactory.ensureCluster();
	      fileChunkcache = CacheFactory.getCache(cacheName);
	 
	      fileChunkcache.addMapListener(new FileChunkCacheChangeListener());	      
	}
	
	public void putIntoCache(String key,Object value) {
		fileChunkcache.put(key, value);
	}
	
	public Object getFromIntoCache(String key) {
	      return fileChunkcache.get(key);
	}
	
	public void shutDownCache() {
		CacheFactory.shutdown();
	}
	
	

	
}
