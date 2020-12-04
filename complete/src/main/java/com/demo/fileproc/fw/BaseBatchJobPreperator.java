package com.demo.fileproc.fw;

import java.util.List;

import com.demo.fileproc.common.AppProperty;
import com.demo.fileproc.service.dto.FileChunkDataDTO;

public abstract class BaseBatchJobPreperator implements IBatchJobPreperator {

	@Override
	public void makeWorkUnits(FileChunkDataDTO fileChunkDTO) {
		// TODO Auto-generated method stub
		String strategy	=	null;
		try {
			strategy	=	AppProperty.getInstance().getProperty("FILE_CHUNK_PROCESSOR_STRATEGY", "SINGLEWITHCOMMITS");
			System.out.println("\n\n********STRATEGY "+strategy);
			switch (strategy) {
			case "SINGLEWITHCOMMITS":
				this.makeWorkUnitsForSINGLEWITHCOMMITS(fileChunkDTO);
				break;
			case "LOWHIGHN":
				this.makeWorkUnitsForLOWHIGH(fileChunkDTO);
				break;
			case "DISTRUBTETOCACHE":
				this.makeWorkUnitsForDISTRUBTETOCACHE(fileChunkDTO);
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public abstract void makeWorkUnitsForSINGLEWITHCOMMITS(FileChunkDataDTO fileChunkDTO);
	public abstract void makeWorkUnitsForLOWHIGH(FileChunkDataDTO fileChunkDTO);
	public abstract void makeWorkUnitsForDISTRUBTETOCACHE(FileChunkDataDTO fileChunkDTO);

}
