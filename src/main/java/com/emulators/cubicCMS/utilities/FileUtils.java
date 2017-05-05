package com.emulators.cubicCMS.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileUtils {
	public static synchronized String readFile(String path, Charset encoding) 
			  throws IOException 
		{
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding!=null ? encoding : Charset.defaultCharset());
		}
	
	public static synchronized String getQuery(String query, Map<String, String> queryElements) {
		
		String sqlQuery = query;
		for (String key : queryElements.keySet()) {
			sqlQuery = sqlQuery.replace(key, queryElements.get(key));
		}
		return sqlQuery;
	}
	public static void writeToFile(String fileWithPath, String content) throws IOException{
		FileWriter fw = new FileWriter(fileWithPath);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(content);
		pw.close();	    	
    }
}
