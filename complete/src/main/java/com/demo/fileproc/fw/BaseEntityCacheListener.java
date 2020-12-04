package com.demo.fileproc.fw;

import java.util.List;

import org.springframework.core.task.TaskExecutor;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.service.cache.CacheServiceFactory;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileChunkDataDTO;
import com.demo.fileproc.service.dto.FileRowDataDTO;
import com.demo.fileproc.service.handler.RowEntityHandler;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public abstract class BaseEntityCacheListener  implements MapListener ,ICacheListener {

	private TaskExecutor tE =	null;

	public BaseEntityCacheListener() {
		tE =	(TaskExecutor)AppContext.getSpringContext().getBean("taskExecutor");
		System.out.println("Initializing EntityCcheListener");
	}	

	
	
	
 
    public void entryInserted(MapEvent me) {
    	
    	String key	=	(String)me.getKey();
    	
    	FileChunkDataDTO obj	=	(FileChunkDataDTO)me.getMap().get(key);
    	
    	System.out.println("GOT IT 			:"+key);
    	
    	System.out.println("Entity GOT DATA AND PUBLISHING NOW :"+obj.getChunkDataList().size());
    	publishData(obj);
    }
 
    public void publishData(DataObjectInterface data) {
    	FileChunkDataDTO fileDTO	=	(FileChunkDataDTO)data;
    	
    	
    	
    	List<FileRowDataDTO> dataStr	=	fileDTO.getChunkDataList();
    	for (FileRowDataDTO fileRowData : dataStr) {
    		RowEntityHandler handler	=	new RowEntityHandler();
//    		handler.setData(fileRowData);					
//    		tE.execute(handler);    		
		}

    }
	
    private ICacheService getCache() {
		return CacheServiceFactory.getCache("entitycoherenace");
	}


}
