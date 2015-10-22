package com.xseillier.psnapi.model.messaging;

import java.util.List;

/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class Message extends LastMessage {
    
    private List<String> mContentKeys;
    private boolean mDataUsedFlag;
    private Long mFakeMessageUid;   
    
	public List<String> getContentKeys() {
		return mContentKeys;
	}
	public void setContentKeys(List<String> aContentKeys) {
		mContentKeys = aContentKeys;
	}
	public boolean isDataUsedFlag() {
		return mDataUsedFlag;
	}
	public void setDataUsedFlag(boolean aDataUsedFlag) {
		mDataUsedFlag = aDataUsedFlag;
	}
	public Long getFakeMessageUid() {
		return mFakeMessageUid;
	}
	public void setFakeMessageUid(Long aFakeMessageUid) {
		mFakeMessageUid = aFakeMessageUid;
	}
}
