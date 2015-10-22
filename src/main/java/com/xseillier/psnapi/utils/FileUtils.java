package com.xseillier.psnapi.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author xseillier
 * @version 1.0 22 oct. 2015
 */
public class FileUtils {

	
	public static boolean isImage( File aFile ) throws IOException {
		 String contentType = Files.probeContentType( aFile.toPath() );		 
		 return contentType.startsWith("image");
	}
	
	public static String getContentType( File aFile ) throws IOException {
		 return Files.probeContentType( aFile.toPath() );		 
	}
}
