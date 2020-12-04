package com.demo.fileproc.fw;

public interface ICacheService {

	public void initCache(String cacheName); 

	public void putIntoCache(String key,Object value);
	
	public Object getFromIntoCache(String key);
	
	public void shutDownCache();
}
