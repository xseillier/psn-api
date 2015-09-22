package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;


/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class AccessDeniedByPrivacyLevelException extends PsnErrorException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4184083554961696019L;
	
	public static final long CODE = 2105868L;
	
	public AccessDeniedByPrivacyLevelException(PsnError aPsnError) {
		super(aPsnError);
	}

	public AccessDeniedByPrivacyLevelException(String aMsg, PsnError aPsnError) {
		super(aMsg, aPsnError);
	}



}
