package com.xseillier.psnapi.model;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class PsnError {

	
	private Error mError;
	
	
	private class Error
	{
		public long mCode;
		public String mMessage;
	}
	
	public PsnError()
	{
		mError = new Error();
		mError.mCode = -1;
		mError.mMessage = "Unknown Error";
	}
	
//=============================================================================
// Accessor
//=============================================================================

	public long getCode() {
		return mError.mCode;
	}
	
	public void setCode( long aCode ) {
		mError.mCode = aCode;
	}
	
	public String getMessage() {
		return mError.mMessage;
	}
	
	public void setMessage( String aMessage ) {
		 mError.mMessage = aMessage;
	}
	
}
