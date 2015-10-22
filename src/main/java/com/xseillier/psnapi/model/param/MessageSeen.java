package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 7 oct. 2015
 * 
 * Internal use
 * Used to mark message as seen
 */
public class MessageSeen {

	private boolean mSeenFlag = true;

	public boolean isSeenFlag() {
		return mSeenFlag;
	}

	public void setSeenFlag(boolean aSeenFlag) {
		mSeenFlag = aSeenFlag;
	}
}
