package com.demo.fileproc.common;

import com.demo.fileproc.service.dto.FileChunkDataDTO;

public class Utility {
	/**
	 * WRTIE AN APPROPRIATE LOGIC FOR PARTITION OF DATA
	 * 
	 * @param fileChunkDataObj
	 * @param CHUNK_BLOCK_NO
	 */
	public static void setPKHighAndLow(FileChunkDataDTO  fileChunkDataObj,int CHUNK_BLOCK_NO) {
		/**
		 * READ FROM CONFIGURATION
		 * 
		 */
		int PARTITION_SIZE	=	10000;
		int BLOCK			=	9999;
		
		int LOW_KEY	=	PARTITION_SIZE*CHUNK_BLOCK_NO;
		int HIGH_KEY	=	LOW_KEY+BLOCK;
		
		fileChunkDataObj.setStartKeyIndexForPK(LOW_KEY);
		fileChunkDataObj.setEndKeyIndexForPK(HIGH_KEY);
		
	}
	
	static String getPathToTargetFile(String[] args) {
        if (args.length >= 1) {
            return args[0];
        }
        return "src/main/resources/config/test.txt";
    }
}
