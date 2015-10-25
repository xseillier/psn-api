package com.xseillier.psnapi.http.retrofit.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.xseillier.psnapi.model.messaging.SendMessage;

/**
 *
 * @author xseillier
 * @version 1.0 5 oct. 2015
 */
public class PsnMessagingFactory extends Converter.Factory{

private Gson mGson;
	
	public PsnMessagingFactory( Gson aGson ) {
		mGson = aGson;
	}


	 public Converter<?, RequestBody> toRequestBody(Type aType, Annotation[] aAnnotations) {
	     
		 if( aType.equals( SendMessage.class ) ) {
			 return new SendMessageConverter( mGson );
		 }
		 return null;
	 }

}
