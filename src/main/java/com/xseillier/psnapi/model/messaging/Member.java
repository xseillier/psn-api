package com.xseillier.psnapi.model.messaging;
/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class Member {

	private String mOnlineId;

	public Member( String aOnlineId ) {
		mOnlineId = aOnlineId;
	}
	
	public String getOnlineId() {
		return mOnlineId;
	}

	public void setOnlineId(String aOnlineId) {
		mOnlineId = aOnlineId;
	}
	
}
