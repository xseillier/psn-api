package com.xseillier.psnapi.http.retrofit.converter;

import java.io.File;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import retrofit.Converter;

import com.google.gson.Gson;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.xseillier.psnapi.model.messaging.SendMessage;
import com.xseillier.psnapi.utils.FileUtils;

/**
 *
 * @author xseillier
 * @version 1.0 5 oct. 2015
 */
public class SendMessageConverter implements Converter< SendMessage, RequestBody > {

	private Gson mGson;
	
	public SendMessageConverter( Gson aGson ) {
		mGson = aGson;
	}	
	
	@Override
	public RequestBody convert(SendMessage aSendMessage) {
		//return new MessageRequestBody( aValue, mGson );
		
		MultipartBuilder  oMultipartBuilder = new MultipartBuilder().type( MultipartBuilder.MIXED );
		
		
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
