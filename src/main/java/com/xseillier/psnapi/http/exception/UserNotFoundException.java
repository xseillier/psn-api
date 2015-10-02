package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;


/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class UserNotFoundException extends PsnErrorException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4184083554961696019L;
	
	public static final long CODE = 2105356L;
	
	public UserNotFoundException(PsnError aPsnError) {
		super(aPsnError);
	}

	public UserNotFoundException(String aMsg, PsnError aPsnError) {
		super(aMsg, aPsnError);
	}



}
