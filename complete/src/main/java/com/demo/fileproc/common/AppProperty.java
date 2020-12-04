package com.demo.fileproc.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;



public class AppProperty {

	private HashMap<String, String>	propertyKeyVal	=	null;
	
	private static AppProperty	property	=	null;
	private static Properties prop =	null;
	
			
	private AppProperty(){
		propertyKeyVal	=	new HashMap<String,String>();
	}

	public String getProperty(String key,String defaultValue) throws Exception{
		String value	=	null;
		String override	=	null;
		
		if(key!=null){
			override	=	System.getProperty(key);
			if(override==null || override.equals("")){
				value	=	prop.getProperty(key);
				if(value==null){
					value	=	defaultValue;
				}
			}
		} else {
			return defaultValue;
			//BaseExceptionManager.throwFatalException(new Exception(), "Not found","EN", 1, "Not fond", 1);
		}
		
		return value;
	}

	public String getUILabel(String key) throws Exception{
		String value	=	null;
		if(key!=null){
			value	=	prop.getProperty(key);
			if(value==null){
				value	=	key;
			}
		} else {
			return key;
		}
		
		return value;
	}

	
	public static AppProperty getInstance(){
		
		if(property==null){
			property	=	new AppProperty();
			load();
		}
		
		return property;
	}
	
	private static void load(){
		prop = new Properties();
    	InputStream input = null;
    	String filename = null;
    	File confFile	=	null;
    	File fielFromSysProperty	=	null;
		try{
			filename	=	"D:\\knowledgebase\\submission\\ws\\gs-maven-master\\complete\\src\\main\\resources\\conf.property";
			
			
		if(filename!=null) {
				
				fielFromSysProperty	=	new File(filename);
				if(fielFromSysProperty!=null) {
					if(fielFromSysProperty.exists()) {
						System.out.println("\n File is existing"+filename);
						input	=	new FileInputStream(fielFromSysProperty);
					} else {
						System.out.println("\n File is not existing"+filename);
						filename	=	"D:\\knowledgebase\\submission\\ws\\gs-maven-master\\complete\\src\\main\\resources\\conf.property";
						confFile	=	new File(filename);
						input	=	new FileInputStream(confFile);
					}
				}

			}
			System.out.println("\n Loading Property file named "+filename);
			prop.load(input);
			/*
			try{
				confFile	=	new File(filename);
				
				input	=	new FileInputStream(confFile);
			} catch (Exception e){
				//e.printStackTrace();
				System.out.println("Could not load from System Property-"+e);
			}
			
			*/
			//input = AppProperty.class.getClassLoader().getResourceAsStream(filename);
			if(input==null){
//				System.out.println("Sorry, unable to find " + filename);
//				filename	=	"D:/Knowledgebase/myiot/workspace/aws/Twilio/Applications/external_properties/platform.properties";
//				confFile	=	new File(filename);
//				System.out.println("Continue to load the default files " );
//				input	=	new FileInputStream(confFile);
			}
	
			
			//load a properties file from class path, inside static method
			//prop.load(input);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	//GRPAH_INVERTER_DCVOLATE
	
	public static void main(String str[]){
		AppProperty p	=	AppProperty.getInstance();
		try{
			String v	=	p.getProperty("STRIPE_SK_VALUE", "Pie");
			System.out.println("Testing "+v);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
	public static final String MAILSMTPAUTH	=	"MAILSMTPAUTH";
	public static final String MAILSMTPSTARTTLSENABLE	=	"MAILSMTPSTARTTLSENABLE";
	public static final String MAILSMTPHOST	=	"MAILSMTPHOST";
	public static final String MAILSMTPPORT	=	"MAILSMTPPORT";
	public static final String MAILSENDEREMAILID	=	"MAILSENDEREMAILID";
	public static final String MAILSENDERPASSWORD	=	"MAILSENDERPASSWORD";
}
