package com.xseillier.psnapi.http.interceptor;

import java.io.IOException;

import com.squareup.okhttp.Interceptor;

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
	  public com.squareup.okhttp.Response intercept(Chain aChain) throws IOException {		  	
		  	System.out.println( "" + ( ++mCount ) );
		  	return aChain.proceed( aChain.request() );
	}
}
