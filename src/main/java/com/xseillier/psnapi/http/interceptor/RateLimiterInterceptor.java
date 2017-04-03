package com.xseillier.psnapi.http.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

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
	  public Response intercept(Chain aChain) throws IOException {
		  	mRateLimiter.acquire();
		  	return aChain.proceed( aChain.request() );
	}
}
