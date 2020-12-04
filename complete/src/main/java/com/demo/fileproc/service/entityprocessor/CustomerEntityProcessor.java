package com.demo.fileproc.service.entityprocessor;

import com.demo.fileproc.fw.IEntityProcessor;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.ErrorDetailDTO;
import com.demo.fileproc.service.dto.customer.CustomerEntityDTO;

public class CustomerEntityProcessor implements IEntityProcessor {

	@Override
	public DataObjectInterface mapEntity(String[] dataArray) {
		// TODO Auto-generated method stub
		CustomerEntityDTO dto	=	new CustomerEntityDTO();
		
		return dto;
	}

	@Override
	public ErrorDetailDTO validateEntity(DataObjectInterface data) {
		// TODO Auto-generated method stub
		return null;
	}

}
