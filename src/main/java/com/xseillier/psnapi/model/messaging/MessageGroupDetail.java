package com.xseillier.psnapi.model.messaging;

import java.util.List;

/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class MessageGroupDetail {
	
	private List<Member> mMembers;
	private int          mMessageGroupType;
    private int          mTotalMembers;
    
    
	public List<Member> getMembers() {
		return mMembers;
	}
	public void setMembers(List<Member> aMembers) {
		mMembers = aMembers;
	}
	public int getMessageGroupType() {
		return mMessageGroupType;
	}
	public void setMessageGroupType(int aMessageGroupType) {
		mMessageGroupType = aMessageGroupType;
	}
	public int getTotalMembers() {
		return mTotalMembers;
	}
	public void setTotalMembers(int aTotalMembers) {
		mTotalMembers = aTotalMembers;
	}
	
}
