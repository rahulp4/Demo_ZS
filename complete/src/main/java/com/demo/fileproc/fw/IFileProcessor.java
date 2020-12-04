package com.demo.fileproc.fw;

import java.util.Date;
import java.util.Scanner;

import com.demo.fileproc.service.dto.DataObjectInterface;

public interface IFileProcessor {

	public DataObjectInterface storeFileRefIntoDB(String filePath);
	
	public Scanner readFiles(String filePatch,DataObjectInterface fileInfoDTO);
	
	public void scanFile(Scanner scanner,String fileName,Date proceDate,DataObjectInterface fileDTO );
}
