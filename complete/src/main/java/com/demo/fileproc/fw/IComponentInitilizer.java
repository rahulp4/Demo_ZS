package com.demo.fileproc.fw;

import org.springframework.context.ApplicationContext;

public interface IComponentInitilizer {


	public boolean startComponent(ApplicationContext context);
	
	public boolean stopComponent(ApplicationContext context);
}
