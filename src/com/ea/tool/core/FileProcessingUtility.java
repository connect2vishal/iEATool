package com.ea.tool.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Chika
 *
 */
public class FileProcessingUtility {

	public static void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an array of extensions.
	 * 
	 * @param directory - the directory to search in
	 * @param extensions - an array of extensions, ex. {"java","xml"}. If this parameter is null, all files are returned.
	 * @param recursive - if true all subdirectories are searched as well
	 * @return an collection of java.io.File with the matching files
	 */
	public static Collection<File> listFiles(File directory,
	                                         String[] extensions,
	                                         boolean recursive){
												return null;
		
	}

}
