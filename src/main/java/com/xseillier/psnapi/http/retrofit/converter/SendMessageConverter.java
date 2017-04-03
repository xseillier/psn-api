package com.xseillier.psnapi.http.retrofit.converter;

import com.google.gson.Gson;
import com.xseillier.psnapi.model.messaging.SendMessage;
import com.xseillier.psnapi.utils.FileUtils;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author xseillier
 * @version 1.0 5 oct. 2015
 */
public class SendMessageConverter implements Converter< SendMessage, RequestBody> {

	private Gson mGson;
	
	public SendMessageConverter( Gson aGson ) {
		mGson = aGson;
	}	
	
	@Override
	public RequestBody convert(SendMessage aSendMessage) {
		//return new MessageRequestBody( aValue, mGson );
		
		MultipartBody.Builder  oMultipartBuilder = new MultipartBody.Builder().setType( MultipartBody.MIXED );
		
		
		oMultipartBuilder.addPart(
		          Headers.of("content-Description", "message"),
		          RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mGson.toJson( aSendMessage ) ));
		
		
		
		if( aSendMessage.getImage() != null ) {
			
				File oFile = aSendMessage.getImage();
				
				try {
					
					oMultipartBuilder.addPart(
					          Headers.of("Content-Disposition", "attachment",
					        		  "Content-Description","image-data-0",
					        		  "Content-Transfer-Encoding","binary"),
					          
					          RequestBody.create(MediaType.parse( FileUtils.getContentType( oFile ) ), oFile ));
				
				
				} catch (IOException e) {
					throw new RuntimeException( e );
				}
		}
		
		return oMultipartBuilder.build();
	}
	
	
}
