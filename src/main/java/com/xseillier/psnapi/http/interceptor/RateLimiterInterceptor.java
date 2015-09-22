package com.xseillier.psnapi.http.interceptor;

import java.io.IOException;

import com.google.common.util.concurrent.RateLimiter;
import com.squareup.okhttp.Interceptor;

/**
 * Interceptor to limit nb requests by seconds
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class RateLimiterInterceptor  implements Interceptor {
	  
	  private RateLimiter mRateLimiter; 
	  public RateLimiterInterceptor( double aRequestBySecond ) {
		  mRateLimiter = RateLimiter.create( aRequestBySecond );
	  }
	
	  @Override 
	  public com.squareup.okhttp.Response intercept(Chain aChain) throws IOException {		  	
		  	mRateLimiter.acquire();
		  	return aChain.proceed( aChain.request() );
	}
}
