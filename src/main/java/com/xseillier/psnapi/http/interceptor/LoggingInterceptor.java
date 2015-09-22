package com.xseillier.psnapi.http.interceptor;

import java.io.IOException;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class LoggingInterceptor  implements Interceptor {
	  @Override 
	  public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
		    Request request = chain.request();

		    long t1 = System.nanoTime();
		    
		    System.out.println("===================================================================================================\n");
		    
		    System.out.println( request.method() );
			    
		    System.out.println(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers() ) );

		    com.squareup.okhttp.Response response = chain.proceed(request);

		    long t2 = System.nanoTime();
		    System.out.println( String.format("Received response for %s in %.1fms%n%s",  response.request().url(), (t2 - t1) / 1e6d, response.headers()));
		
		    return response;
	}
}
