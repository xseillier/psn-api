package com.xseillier.psnapi.http.interceptor;

import com.xseillier.psnapi.PsnApi;
import com.xseillier.psnapi.http.cst.HttpHeaderCst;
import com.xseillier.psnapi.utils.UrlUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Inject oauth2 token in httpHeader is header HttpHeaderCst.HEADER_ADD_AUTHORIZATION is present
 * @author xseillier
 * @version 1.0 25 oct. 2015
 */
public class AuthorisationInterceptor  implements Interceptor {
	  
	 
	  private PsnApi mPsnApi;
	  
	  public AuthorisationInterceptor( PsnApi aPsnApi ) {
		  mPsnApi = aPsnApi;
	  }
	
	  @Override 
	  public Response intercept(Chain aChain) throws IOException {
		  
		  	Request oRequest = aChain.request();
		  	
		  	String oAddAuthHeader = oRequest.header( HttpHeaderCst.HEADER_ADD_AUTHORIZATION );
		  	
		  	if(  oAddAuthHeader != null  ) {
		  		oRequest = oRequest.newBuilder().removeHeader( HttpHeaderCst.HEADER_ADD_AUTHORIZATION )
		  							 .addHeader(HttpHeaderCst.HEADER_AUTHORIZATION, UrlUtils.createAuthHeader( mPsnApi.getPsnContext().getAccessToken() ) ).build();
		  	}
		  	
		  	return aChain.proceed( oRequest );
	}
}
