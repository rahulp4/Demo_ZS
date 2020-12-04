package com.demo.fileproc.fw;

import com.demo.fileproc.service.dto.DataObjectInterface;
import com.demo.fileproc.service.dto.ErrorDetailDTO;

public interface IEntityProcessor {

	public DataObjectInterface mapEntity(String dataArray[]);

	public ErrorDetailDTO validateEntity(DataObjectInterface data);

}
