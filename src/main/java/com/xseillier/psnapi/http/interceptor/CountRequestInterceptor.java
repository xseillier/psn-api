package com.xseillier.psnapi.http.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

/**
 * Interceptor to limit nb requests by seconds
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class CountRequestInterceptor  implements Interceptor {
	  
	
	  private long mCount = 0;
	  
	  public CountRequestInterceptor() {
	  }
	
	  @Override 
	  public Response intercept(Chain aChain) throws IOException {
		  	System.out.println( "" + ( ++mCount ) );
		  	return aChain.proceed( aChain.request() );
	}
}
