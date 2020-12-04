package com.demo.fileproc.service.cachelistener;

import org.springframework.core.task.TaskExecutor;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.fw.BaseEntityCacheListener;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileChunkDataDTO;
import com.tangosol.util.MapEvent;

public class EntityCacheChangeListener extends BaseEntityCacheListener { 
//implements MapListener {
	 

	public EntityCacheChangeListener() {
	}	

	
 
 
    public void entryDeleted(MapEvent me) {
        System.out.println("Deleted Key = " + me.getKey() + ", Value = " + me.getOldValue());
    }
    
    public void entryUpdated(MapEvent me) {
    }




	@Override
	public void handleDateReceived(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
		
	}




	@Override
	public void validatedataReceived(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void postProseesor(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void publishForNextStep(DataObjectInterface data) {
		// TODO Auto-generated method stub
		
	}
}