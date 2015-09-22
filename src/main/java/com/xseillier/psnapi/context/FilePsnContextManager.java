package com.xseillier.psnapi.context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xseillier.psnapi.model.PsnContext;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class FilePsnContextManager implements PsnContextManager {

	
	private String mPath;
	private Gson   mGson;
	
	public FilePsnContextManager( String aPath ) {
		mPath = aPath;
		mPath = aPath;
		GsonBuilder gsonBuilder = new GsonBuilder();
	    mGson = gsonBuilder.create();
	}
	
	
	@Override
	public PsnContext load(String aName) {
		FileReader oFileReader;
		try
		{
			oFileReader = new FileReader( new File( mPath + File.separator + aName ) );
			return   mGson.fromJson( new BufferedReader( oFileReader ),  PsnContext.class );
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(String aName, PsnContext aPsnContext) {
		FileWriter oFileWriter = null;
		try
		{
			oFileWriter = new FileWriter( new File( mPath + File.separator + aName ) );
			oFileWriter.write( mGson.toJson( aPsnContext ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if( oFileWriter != null )
			{
				try {
					oFileWriter.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String aName) {
		File oFile = new File( mPath + File.separator + aName );
		oFile.delete();
	}

}
