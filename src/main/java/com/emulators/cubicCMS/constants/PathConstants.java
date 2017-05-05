package com.emulators.cubicCMS.constants;


public interface PathConstants {
	String PROJECT_RESOURCES_PATH = "C:\\CubicWorkSpace\\cubicCMS\\src\\main\\resources\\";
	static String generateResourceFilePath(String filename){
		return PROJECT_RESOURCES_PATH+filename;
	}

}
