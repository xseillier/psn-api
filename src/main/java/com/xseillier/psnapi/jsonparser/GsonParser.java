package com.xseillier.psnapi.jsonparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonParser {

		
	private static Gson oGsonNormal     = new GsonBuilder().setFieldNamingStrategy( new PsnApiFieldNamingStrategy() ).create();

	public static Gson getGsonParserInstance()
	{
		return oGsonNormal;
	}
}
