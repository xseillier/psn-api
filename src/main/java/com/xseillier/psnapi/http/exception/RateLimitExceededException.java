package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;


/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class RateLimitExceededException extends PsnErrorException {
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -945438504352170171L;
	
	public static final long CODE = 2097675;
	
	public RateLimitExceededException(PsnError aPsnError) {
		super(aPsnError);
	}

	public RateLimitExceededException(String aMsg, PsnError aPsnError) {
		super(aMsg, aPsnError);
	}



}
