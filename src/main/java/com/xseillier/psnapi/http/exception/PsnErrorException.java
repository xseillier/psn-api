package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class PsnErrorException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5547759405021983090L;

	
	private PsnError mPsnError;
	/**
	 * 
	 * @param aMsg
	 */
	public PsnErrorException( String aMsg,  PsnError aPsnError ){
		super( aMsg );
		mPsnError = aPsnError;
	}
	
	
	public PsnErrorException(PsnError aPsnError) {
		this( aPsnError.toString(), aPsnError);
	}


	public PsnError getPsnError() {
		return mPsnError;
	}
}
