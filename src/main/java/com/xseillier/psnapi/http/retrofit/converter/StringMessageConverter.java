package com.xseillier.psnapi.http.retrofit.converter;

import retrofit.Converter;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.xseillier.psnapi.model.messaging.SendMessage;

/**
 *
 * @author xseillier
 * @version 1.0 5 oct. 2015
 */
public class StringMessageConverter implements Converter< SendMessage, RequestBody > {

	private Gson mGson;
	
	public StringMessageConverter( Gson aGson ) {
		mGson = aGson;
	}

	@Override
	public RequestBody convert(SendMessage aValue) {
		return new MessageRequestBody( aValue, mGson );
	}
	
}
