package com.demo.fileproc.fw;

import java.util.Date;
import java.util.Scanner;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.FileInfoDTO;
import com.demo.fileproc.service.fileproc.FileProcessorImpl;

public class SubmitterFileBase {

	private String filePath	=	null;
	public SubmitterFileBase(String p_FilePatch) {
		filePath	=	p_FilePatch;
	}
	public void startProcessing(IFileProcessor fileProcessor) {
		/**
		 * Processing Date
		 */
		
		
		Date processingDate	=	new Date();
		
		/**
		 * First Store File Reference into DB. 
		 * For COLUMN DETAILS REFERT THE METHOD
		 */
		DataObjectInterface dto 	=	fileProcessor.storeFileRefIntoDB(filePath);
		FileInfoDTO fileDTO	=	(FileInfoDTO)dto;
		
		/**
		 * READ FILE. THIS HANDLED IN CHILD CLASS. 
		 * WE CAN HAVE VARIOUS CHANNELS FOR FILE AND HENCE LEFT TO CHILD CLASS
		 * NOT TO THE BASE CLASS
		 */
		Scanner sc	=	fileProcessor.readFiles(filePath,fileDTO);
		
		/**
		 * THE WAY FILE CONTENT IS TO BE HANDLED IS BOILERPLATE CODE AND HENCE HANDLED IN SUPER 
		 * CLASS OF FileProcessorImpl.java.
		 * IN THIS,CHUNKS OF DATA IS CREATED AND PUT ONTO DISTRIBUTED CACHE.
		 * THESE CHUNKS ARE DISTRIBUTED OVER MULTIPLE JVMS.
		 */
		fileProcessor.scanFile(sc,filePath,processingDate,fileDTO);
	}


	public static void main(String[] file) {
		
		
		/**
		 * Initialize Application.
		 * Make sure only fw classes are referred in this initializer class
		 */
		ApplicationInitilizer.initApp();
		
		String filePatch	=	"D:\\knowledgebase\\submission\\ws\\samplefiles\\test.txt";
		filePatch	=	"D:\\knowledgebase\\submission\\ws\\samplefiles\\indiv18\\by_date\\itcont_2018_20020411_20170529.txt";
		
		SubmitterFileBase process		=	new SubmitterFileBase(filePatch);
		
		/**
		 * Can change actual impl class
		 */
		IFileProcessor fileProcessor	=	new FileProcessorImpl();		
		process.startProcessing(fileProcessor);
		
		
		/**
		 * Close Context Once Done
		 */
		
		AppContext.closeSpringContext();
	
	}

	

}
