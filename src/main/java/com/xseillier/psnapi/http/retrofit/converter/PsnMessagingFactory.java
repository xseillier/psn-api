package com.xseillier.psnapi.http.retrofit.converter;

import com.google.gson.Gson;
import com.xseillier.psnapi.model.messaging.SendMessage;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type aType, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
		if( aType.equals( SendMessage.class ) ) {
			return new SendMessageConverter( mGson );
		}
		return null;
	}
}
