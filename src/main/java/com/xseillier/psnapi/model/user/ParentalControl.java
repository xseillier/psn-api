package com.xseillier.psnapi.model.user;
/**
*
* @author xseillier
* @version 1.0 15 sept. 2015
*/
public class ParentalControl {

	
	private boolean mRestrictChat;
	private boolean mRestrictStoreContent;
	private boolean mRestrictUGM;
	
	
//=============================================================================
// Accessor
//=============================================================================
	
	public boolean getRestrictChat() {
		return mRestrictChat;
	}
	public boolean getRestrictStoreContent() {
		return mRestrictStoreContent;
	}
	public boolean getRestrictUGM() {
		return mRestrictUGM;
	}
	
	@Override
	public String toString() {
		return "ParentalControl [mRestrictChat=" + mRestrictChat
				+ ", mRestrictStoreContent=" + mRestrictStoreContent
				+ ", mRestrictUGM=" + mRestrictUGM + "]";
	}
	
	

}
