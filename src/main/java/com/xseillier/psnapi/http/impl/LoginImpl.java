package com.xseillier.psnapi.http.impl;

import java.io.IOException;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xseillier.psnapi.http.Login;
import com.xseillier.psnapi.http.cst.HttpHeaderCst;
import com.xseillier.psnapi.http.cst.UrlParamCst;
import com.xseillier.psnapi.http.exception.LoginException;
import com.xseillier.psnapi.utils.StringUtils;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public class LoginImpl implements Login {

		
	private static String PATTERN_LOGIN_KO = "authentication_error=true";
	
	private OkHttpClient mOkHttpClient;
	
	public LoginImpl( OkHttpClient aOkHttpClient ) {
		mOkHttpClient = aOkHttpClient;
	}
	
	@Override
	public String getBrandingParamStep1(String aUrl, long aCurrentTimestamp,
			String aRedirectUrl, String aClientId, String aScope) throws LoginException {

		Response oResponse      = null;
		Request  oRequest       = null;
		String   oBrandingParam = null;
		StringBuilder oUrl = new StringBuilder();
		
		oUrl.append( aUrl );
		oUrl.append("?").append( UrlParamCst.URL_PARAM_CLTM         ).append("=").append( String.valueOf( aCurrentTimestamp ) );
		oUrl.append("&").append( UrlParamCst.URL_PARAM_REDIRECT_URL ).append("=").append( String.valueOf( aRedirectUrl ) );
		oUrl.append("&").append( UrlParamCst.URL_PARAM_CLIENT_ID    ).append("=").append( String.valueOf( aClientId ) );
		oUrl.append("&").append( UrlParamCst.URL_PARAM_SCOPE        ).append("=").append( String.valueOf( aScope ) );
		
		oRequest = new Request.Builder()
		   .addHeader("User-Agent", "PSN Hermes Android 5.0 Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322)")
			
	        .url( oUrl.toString() )
	        .build();
		try {
			oResponse = mOkHttpClient.newCall(oRequest).execute();
		    if (!oResponse.isSuccessful()) throw new LoginException("Login Error step 1. Unexpected code " + oResponse);
			    
		    
		    if( oResponse != null  )
			{		
		    	
		    	String oBody = oResponse.body().string();
		    	if( oBody.contains( UrlParamCst.URL_PARAM_BRANDING_PARAM ) ){
		    		oBrandingParam = StringUtils.getTextBetween( oBody, UrlParamCst.URL_PARAM_BRANDING_PARAM , ">");
		    		oBrandingParam = StringUtils.getTextBetween(oBrandingParam, "value=\"", "\"");
		    	}
		    	else {
		    		 throw new LoginException("Login Error step 1. BrandingParams not found in the response " );
		    	}
			}
		}
	    catch(IOException ioe ) {
	    	 throw new LoginException("Login Error step 1.", ioe );
	    }
		return oBrandingParam;
	}

	
	@Override
	public String validLoginStep2(String aUrl, String aBrandingParam,
			String aPSNLogin, String aPSNPassword) throws LoginException {
			
		Response oResponse      = null;
		Request  oRequest       = null;
		String oUrlLocation     = null;
		
		RequestBody oFormBody = new FormEncodingBuilder()
        .add( UrlParamCst.URL_PARAM_USERNAME       ,  String.valueOf( aPSNLogin ) )
        .add( UrlParamCst.URL_PARAM_PASSWORD       ,  String.valueOf( aPSNPassword )  )
        .add( UrlParamCst.URL_PARAM_BRANDING_PARAM ,  String.valueOf( aBrandingParam ) )	   
        .build();
		
		mOkHttpClient.setFollowRedirects(false);
    	oRequest = new Request.Builder()
        .url( aUrl )
        .post(oFormBody)
        .build();
    	
    	try {
    		   	
	    	oResponse = mOkHttpClient.newCall( oRequest ).execute();    	  	 
	    	oUrlLocation = oResponse.header( HttpHeaderCst.HEADER_LOCATION );
    	 
	    	if( oUrlLocation == null || "".equals(oUrlLocation ) ) {
	    		 throw new LoginException("Login Error step 2. Url not found in response.");
	    	}
	    	
	    	if( isLoginError( oUrlLocation ) ) {
	    		 throw new LoginException("Login or password incorrect");
	    	}
	    	
    	} catch(IOException ioe ) {
	    	 throw new LoginException("Login Error step 2.", ioe );
	    }
    	finally {
    		mOkHttpClient.setFollowRedirects( true );	
    	}
    	return oUrlLocation;
	}

	@Override
	public String getLoginCodeStep3(String aUrl) throws LoginException {


		Response oResponse      = null;
		Request  oRequest       = null;
		String oLoginCode       = null;
		
		 
		mOkHttpClient.setFollowRedirects(false);
		oRequest = new Request.Builder()
	        .url( aUrl )
	        .build();
	    	
		try {
			
			oResponse = mOkHttpClient.newCall(oRequest).execute();			
			oLoginCode = oResponse.header( HttpHeaderCst.HEADER_X_NP_GRANT_CODE );
			
			if( oLoginCode == null || "".equals( oLoginCode ) ) {
	    		 throw new LoginException("Login Error step 3. Login Code not found in response.");
	    	}
			
		} catch (IOException ioe) {	
			 throw new LoginException("Login Error step 2.", ioe );

		} 
		finally {
			mOkHttpClient.setFollowRedirects( true );
		}
	    		    	    
		return oLoginCode;
	}
	
	
	
	
	/**
	 * return true if error with PSN login / password
	 * @param aUrlLocation
	 * @return
	 */
	private boolean isLoginError( String aUrlLocation )  {
	
		return String.valueOf( aUrlLocation ).contains( PATTERN_LOGIN_KO );
	}

}
