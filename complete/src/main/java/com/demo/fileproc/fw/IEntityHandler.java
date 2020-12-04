package com.demo.fileproc.fw;

import com.demo.fileproc.service.dto.DataObjectInterface;

public interface IEntityHandler extends Runnable {

	
	
	public void handleEntity(DataObjectInterface data);

	public void cleanup(DataObjectInterface data);
	
}
