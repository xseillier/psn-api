package com.xseillier.psnapi.http.retrofit.converter;

import java.io.File;
import java.io.IOException;

import okio.BufferedSink;
import okio.Okio;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.xseillier.psnapi.model.messaging.SendMessage;
import com.xseillier.psnapi.utils.FileUtils;

/**
 *
 * @author xseillier
 * @version 1.0 22 oct. 2015
 */
class MessageRequestBody extends RequestBody{

	
	private static final byte[] COLONSPACE = { ':', ' ' };
	private static final byte[] CRLF       = { '\r', '\n' };
	private static final byte[] DASHDASH   = { '-', '-' };
	private static final String BOUNDARY_PATTERN  =  "abcdefghijklmnopqrstuvwxyz";
	private static final MediaType MEDIATYPE      =  MediaType.parse("multipart/mixed; boundary=" + BOUNDARY_PATTERN);

	SendMessage mStringMessage;
	Gson mGson;
	
	
	public MessageRequestBody( SendMessage aStringMessage, Gson aGson ){
		mStringMessage = aStringMessage;
		mGson = aGson;
	}
	
	@Override
	public MediaType contentType() {
		return MEDIATYPE;
	}

	@Override
	public void writeTo(BufferedSink aSink) throws IOException {
		aSink.write( DASHDASH );
		aSink.writeUtf8( BOUNDARY_PATTERN );
		aSink.write( CRLF );
		aSink.writeUtf8( "Content-Type: application/json; charset=utf-8" );
		aSink.write( CRLF );
		aSink.writeUtf8( "Content-Description: message" );
		aSink.write( CRLF );
		aSink.write( CRLF );
		aSink.writeUtf8( mGson.toJson( mStringMessage ) );
		aSink.write( CRLF );
		
		if( mStringMessage.getImage() != null ) {
			
				File oFile = mStringMessage.getImage();
				
				aSink.write( DASHDASH );
				aSink.writeUtf8( BOUNDARY_PATTERN );
				aSink.write( CRLF );
				
				aSink.writeUtf8( "Content-Type" );
				aSink.write( COLONSPACE );	
				aSink.writeUtf8( FileUtils.getContentType( oFile ) );
				aSink.write( CRLF );
				
				aSink.writeUtf8( "Content-Disposition: attachment" );
				aSink.write( CRLF );
				
				aSink.writeUtf8( "Content-Description");
				aSink.write( COLONSPACE );
				aSink.writeUtf8( "image-data-0");
				aSink.write( CRLF );
				
				aSink.writeUtf8( "Content-Transfer-Encoding: binary" );
				aSink.write( CRLF );
				
				aSink.writeUtf8( "Content-Length" );
				aSink.write( COLONSPACE );	
				aSink.writeDecimalLong(  oFile.length() );	
				aSink.write( CRLF );
				aSink.write( CRLF );				
				aSink.writeAll( Okio.source( oFile ) );
				aSink.write( CRLF );
		}
		
		
		aSink.write( DASHDASH );
		aSink.writeUtf8( BOUNDARY_PATTERN );
		aSink.write( DASHDASH );
		
	}
	
	
}