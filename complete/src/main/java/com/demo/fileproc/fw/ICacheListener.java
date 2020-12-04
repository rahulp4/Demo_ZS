package com.demo.fileproc.fw;

import com.demo.fileproc.service.dto.DataObjectInterface;

public interface ICacheListener {

	public void handleDateReceived(DataObjectInterface data);
	
	public void validatedataReceived(DataObjectInterface data);
	
	public void postProseesor(DataObjectInterface data);
	
	public void publishForNextStep(DataObjectInterface data);
}
