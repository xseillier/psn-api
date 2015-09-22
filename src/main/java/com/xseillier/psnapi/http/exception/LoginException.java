package com.xseillier.psnapi.http.exception;
/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public class LoginException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5547759405021983090L;

	/**
	 * 
	 * @param aMsg
	 */
	public LoginException( String aMsg ){
		super( aMsg );
	}
	
	/**
	 * 
	 * @param aMsg
	 * @param aCause
	 */
	public LoginException( String aMsg, Throwable aCause ){
		super( aMsg, aCause );
	}
}
