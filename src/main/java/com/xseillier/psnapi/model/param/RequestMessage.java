package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 30 sept. 2015
 * 
 * Used when you add friend for add message 
 */

public class RequestMessage {

	private String mRequestMessage;
	
	public RequestMessage() {
		this("");
	}
	
	public RequestMessage( String aRequestMessage ) {
		mRequestMessage = aRequestMessage;
	}

	public String getRequestMessage() {
		return mRequestMessage;
	}

	public void setRequestMessage(String aRequestMessage) {
		mRequestMessage = aRequestMessage;
	}
}
