package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;


/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class BadRequestException extends PsnErrorException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4184083554961696019L;
	
	public static final long CODE = 2121729;
	
	public BadRequestException(PsnError aPsnError) {
		super(aPsnError);
	}

	public BadRequestException(String aMsg, PsnError aPsnError) {
		super(aMsg, aPsnError);
	}



}
