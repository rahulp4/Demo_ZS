package com.demo.fileproc.service.fileproc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.fileproc.fw.BaseFileProcessorImpl;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileInfoDTO;

public class FileProcessorImpl extends BaseFileProcessorImpl {

	Logger logger	=	LoggerFactory.getLogger(FileProcessorImpl.class);

	/**
	 * This gets the File path as input. Reads the file and Creates a Scanner Object.
	 * Scanner is returned from there and in next step scanner will be iterated to 
	 * create chunk of data
	 *  
	 */
	@Override
	public Scanner readFiles(String filePath,DataObjectInterface fileDTO) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		logger.debug("Reading file now from "+filePath);
		Scanner sc = null;
		try {
			
			FileInputStream inputStream = new FileInputStream(file);
			sc = new Scanner(inputStream, "UTF-8");
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return sc;
	}
}
