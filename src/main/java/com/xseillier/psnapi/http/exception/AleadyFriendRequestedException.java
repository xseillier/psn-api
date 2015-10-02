package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;


/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class AleadyFriendRequestedException extends PsnErrorException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4184083554961696019L;
	
	public static final long CODE = 2107651L;
	
	public AleadyFriendRequestedException(PsnError aPsnError) {
		super(aPsnError);
	}

	public AleadyFriendRequestedException(String aMsg, PsnError aPsnError) {
		super(aMsg, aPsnError);
	}



}
