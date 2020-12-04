package com.demo.fileproc.service.parser;

import com.demo.fileproc.fw.IParser;
import com.demo.fileproc.service.dto.customer.CustomerEntityDTO;
import com.demo.fileproc.service.dto.customer.SalesPersonEntityDTO;

public class StringDataParser implements IParser{

	
	public String[] parse(String dataStr){
		
		String[] dataArray	=	dataStr.split(",");
		return dataArray;
	}
}
