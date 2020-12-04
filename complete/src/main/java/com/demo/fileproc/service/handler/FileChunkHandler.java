package com.demo.fileproc.service.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class FileChunkHandler implements Callable<String> {

	private Map dataChunkMap	=	null;
	
	public FileChunkHandler(Map<String, List<String>> p_DataChunkMap) {
		dataChunkMap	=	p_DataChunkMap;
	}
	@Override
    public String call() throws Exception {
		String retKey	=	null;
		System.out.println("Got data as "+dataChunkMap);
		Map retMap	=	new HashMap<String, List<String>>();
		Set keySet	=	retMap.keySet();
		Iterator< String> keyIterator =	keySet.iterator();
		while(keyIterator.hasNext()) {
			retKey	=	keyIterator.next();
			List dataList	=	(List<String>)dataChunkMap.get(retKey);
			
		}
		
		
		return retKey;
	}
}
