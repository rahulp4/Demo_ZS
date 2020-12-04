package com.demo.fileproc.fw;

import java.util.List;

import com.demo.fileproc.service.dto.FileChunkDataDTO;

public interface IBatchJobPreperator {

	public void makeWorkUnits(FileChunkDataDTO fileChunkDTO);

}
