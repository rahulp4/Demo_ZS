package com.demo.fileproc.fw;

import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.entityprocessor.CustomerEntityProcessor;
import com.demo.fileproc.service.entityprocessor.SalesEntityProcessor;

public abstract class BaseEntityHandler implements IEntityHandler {

	DataObjectInterface data	=	null;
	
	private long LOWKEY	=	0;
	private long HIGHKEY	=	0;
	
	
	@Override
	public String toString() {
		return "BaseEntityHandler [data=" + data + ", LOWKEY=" + LOWKEY + ", HIGHKEY=" + HIGHKEY + "]";
	}

	public long getLOWKEY() {
		return LOWKEY;
	}

	public void setLOWKEY(long lOWKEY) {
		LOWKEY = lOWKEY;
	}

	public long getHIGHKEY() {
		return HIGHKEY;
	}

	public void setHIGHKEY(long hIGHKEY) {
		HIGHKEY = hIGHKEY;
	}

	public DataObjectInterface getData() {
		return data;
	}

	public void setData(DataObjectInterface data) {
		this.data = data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean success	=	true;
		try {
			this.handleEntity(data);
			success	=	true;
		} catch (Exception e) {
			success	=	false;
		}
		if(success) {
			this.cleanup(data);
		}
	}


	public IEntityProcessor getEntityProcessor(String type) {
		IEntityProcessor processor	=	null;
		
		if(type.equals("CUSTOMER")) {
			processor	=	new CustomerEntityProcessor();
		}
		
		if(type.equals("SALES")) {
			processor	=	new SalesEntityProcessor();
		}
		
		return processor;
		
		
	}
}
