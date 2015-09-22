package com.xseillier.psnapi.jsonparser;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;

public class PsnApiFieldNamingStrategy implements FieldNamingStrategy {


	public String translateName(Field aField) {
		String aResponse = aField.getName();
		if( aResponse.startsWith("m") )
		{
			if( Character.isUpperCase( aResponse.charAt( 1 ) )  )
			{
				aResponse = aResponse.substring(1);			
				aResponse = Character.toLowerCase( aResponse.charAt( 0 ) ) + aResponse.substring( 1 );
			}
		}	
		return aResponse;
	}
	
}
